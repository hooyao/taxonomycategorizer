package com.minetool.worddir.tagger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.xml.sax.SAXException;

import com.minetool.tax.categorizer.acm.ACMTaxCategorizer;
import com.minetool.wordnet.categorizer.WordNetUtil;

public class Tagger {
    private ACMTaxCategorizer acm;
    
    public Tagger() throws SAXException, IOException{
	this.acm = new ACMTaxCategorizer();
    }

    public HashMap<Integer, String> tagMap(HashMap<Integer, String> inMap) {
	Set<Integer> ks = inMap.keySet();
	List<Integer> kl = new ArrayList<Integer>(ks);
	Collections.sort(kl);
	HashMap<Integer, String> outMap = new HashMap<Integer, String>();
	for (Integer ing : kl) {
	    String word = inMap.get(ing);
	    String wordAfWN = word;
	    // test if abbreviation
	    if (testIfAbbr(word)) {
		wordAfWN = word;
	    } else {
		String[] forms = WordNetUtil.getInstance().getBaseForm(word);
		if (forms == null || forms.length == 0) {
		    wordAfWN = word.toLowerCase();// base form
		} else
		    wordAfWN = forms[0].toLowerCase();
	    }
	    List<String> matchClsList =this.acm.find1WordMatch(wordAfWN);
	    HashMap<String, Integer> stat = new HashMap<String, Integer>();
	    for (String s : matchClsList) {
		if (s.trim().length() >= 1) {
		    String topCls = s.trim().substring(0, 1);
		    if (stat.containsKey(topCls)) {
			stat.put(topCls, new Integer(stat.get(topCls)
				.intValue() + 1));
		    } else {
			stat.put(topCls, new Integer(0));
		    }
		}
	    }
    	    String maxClsLabel="";
    	    int maxCount = 0;
    	    for (String s:stat.keySet()) {
		if(stat.get(s).intValue()>maxCount){
		    maxClsLabel = s;
		    maxCount = stat.get(s).intValue();
		}
	    }
    	    outMap.put(ing, maxClsLabel);
	}
	
	return outMap;
    }
    
    private boolean testIfAbbr(String s) {
	String pattern ="\\b([A-Z]*)s?\\b";
	return s.matches(pattern);
    }
}
