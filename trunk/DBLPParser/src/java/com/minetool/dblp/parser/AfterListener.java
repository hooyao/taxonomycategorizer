package com.minetool.dblp.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.SAXException;

import com.minetool.dblp.parser.Processor.ProcessListener;
import com.minetool.dblp.parser.model.AbstractDblpObj;
import com.minetool.dblp.parser.model.Dblp;
import com.minetool.tax.categorizer.acm.ACMTaxCategorizer;
import com.minetool.wordnet.categorizer.RETokenizer;
import com.minetool.wordnet.categorizer.WordNetUtil;

public class AfterListener implements ProcessListener {
    private long curid = 0l;

    private final ACMTaxCategorizer acm;

    private final DblpResultWriter writer;
    
    String[] prepositionList = new String[] { "on", "in", "at", "since", "for",
	    "ago", "before", "to", "past", "to", "till", "until", "by", "next",
	    "beside", "under", "below", "over", "above", "across", "through",
	    "into", "towards", "onto", "from", "of", "off", "out", "about" };

    String[] personalPrep = new String[] { "I", "me", "myself", "mine", "my",
	    "we", "us", "ourselves", "ourself", "ours", "our", "you",
	    "yourself", "yours", "your", "thou", "thee", "thyself", "thine",
	    "thy", "yourselves", "he", "him", "himself", "hisself", "his",
	    "she", "her", "herself", "hers", "it", "itself", "its", "one",
	    "oneself", "one's", "they", "themself", "theirself", "their",
	    "them", "themselves", "theirselves", "theirs", "who", "whom",
	    "whose" };

    public AfterListener(DblpResultWriter wtr) throws SAXException, IOException {
	this.acm = new ACMTaxCategorizer();
	this.writer = wtr;
    }

    @Override
    public void run(Object target, Object parent) {
	if (target instanceof AbstractDblpObj && parent instanceof Dblp) {
	    AbstractDblpObj obj = (AbstractDblpObj) target;
	    Dblp dblp = (Dblp) parent;
	    dblp.getArticleOrInproceedingsOrProceedingsOrBookOrIncollectionOrPhdthesisOrMastersthesisOrWww()
		    .clear();
	    String title = obj.getTitle();
	    String bookTitle = obj.getBookTitle();
	    obj = null;

	    HashMap<String, Integer> stat = new HashMap<String, Integer>();
	    stat = processString(title, stat);
	    stat = processString(bookTitle, stat);
	    try {
		this.writer.writeObjectIn(stat);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    
	}
    }

    public HashMap<String, Integer> processString(String s,
	    HashMap<String, Integer> stat) {
	RETokenizer st = new RETokenizer(s,"\\b\\w+\'*\\w*\\b", false);
	ArrayList<String> tkList = new ArrayList<String>();
	while (st.hasNext()) {
	    String tok = st.next();
	    // test if abbreviation
	    if (testIfAbbr(tok)) {
		tkList.add(tok);
		continue;
	    }
	    String[] forms = WordNetUtil.getInstance().getBaseForm(tok);
	    if (forms == null || forms.length == 0) {
		tkList.add(tok.toLowerCase());//base form
	    } else
		tkList.add(forms[0].toLowerCase());
	}
	for (String tk : tkList) {
	    if ((WordNetUtil.getInstance().isProperNoun(tk) || testIfAbbr(tk))
		    && (!tk.equalsIgnoreCase("a")
			    && (!tk.equalsIgnoreCase("an")) && (!isPrep(tk)) && (!isPersonalPrep(tk)))) {
		List<String> tagList = this.acm.find1WordMatch(tk);
		for (String tag : tagList) {
		    if (stat.containsKey(tag)) {
			int count = stat.get(tag).intValue();
			stat.remove(tag);
			stat.put(tag, ++count);
		    } else {
			stat.put(tag, 1);
		    }
		}
		System.out.print(tk + "\n");
	    }
	}
	return stat;
    }
    
    private boolean testIfAbbr(String s) {
	String pattern ="\\b([A-Z]*)s?\\b";
	return s.matches(pattern);
    }

    private boolean isPrep(String in) {
	for (String s : prepositionList) {
	    if (s.equalsIgnoreCase(in))
		return true;
	}
	return false;
    }
    
    private boolean isPersonalPrep(String in) {
	for (String s : personalPrep) {
	    if (s.equalsIgnoreCase(in))
		return true;
	}
	return false;
    }
}
