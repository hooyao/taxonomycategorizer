package com.minetool.dblp.parser;

import java.io.File;
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
	RETokenizer st = new RETokenizer(s, "\\b\\w+\'*\\w*\\b", false);
	ArrayList<String> tkList = new ArrayList<String>();
	while (st.hasNext()) {
	    String tok = st.next();
	    String[] forms = WordNetUtil.getInstance().getBaseForm(tok);
	    if (forms != null || forms.length == 0) {
		tkList.add(tok.toLowerCase());
	    } else
		tkList.add(forms[0].toLowerCase());
	}
	for (String tk : tkList) {
	    if (tk.equalsIgnoreCase("of"))
		System.out.println(tk);
	    List<String> tagList = this.acm.find1WordMatch(tk);
	    for (String tag : tagList) {
		if (stat.containsKey(tag)) {
		    int count = stat.get(tag).intValue();
		    stat.remove(tag);
		    stat.put(tag, count++);
		} else {
		    stat.put(tag, 1);
		}
	    }
	}
	return stat;
    }
}
