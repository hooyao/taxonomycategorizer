package com.minetool.dblp.parser.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.minetool.worddir.tagger.DictionaryReader;
import com.minetool.worddir.tagger.Tagger;

public class TestDirTagger {
    
    @Test
    public void TestTagger(){
	
	try {
	    HashMap<Integer, String> words =  DictionaryReader.readFromFile("Z:\\rainbow\\vocabulary"); //$NON-NLS-1$
	    Tagger tagger = new Tagger();
	    HashMap<Integer, String> classMap = tagger.tagMap(words);
	    Set<Integer> ks = classMap.keySet();
	    ArrayList<Integer> ls = new ArrayList<Integer>(ks);
	    Collections.sort(ls);
	    {
		File file = new File("z:\\wordclass.txt"); //$NON-NLS-1$
		if (!file.exists())
		    file.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		try {
		    if (ls.size() > 0) {
			for (Integer i : ls) {
			    if (classMap.get(i) == null
				    || classMap.get(i).length() == 0) {
				output.write("NA");
				output.newLine();
			    } else {
				output.write(classMap.get(i));
				output.newLine();
			    }
			}
		    }
		} finally {
		    output.close();
		}
	    }
	    
	    
	    {
		List<String> clsLabels = new ArrayList<String>();
		for (String s : classMap.values()) {
		    if (s.trim().length() > 0 && !clsLabels.contains(s.trim())) {
			clsLabels.add(s.trim());
		    }
		}
		Collections.sort(clsLabels);
		File file = new File("z:\\classmap.txt"); //$NON-NLS-1$
		if (!file.exists())
		    file.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		try {
		    if (clsLabels != null && clsLabels.size() > 0) {
			output.write(String.valueOf(clsLabels.size()));
			output.newLine();
			for (String value : clsLabels) {
			    if (value == null || value.length() == 0) {
				output.write("NA");
			    } else {
				output.write(value);
				output.newLine();
			    }
			}
		    }

		} finally {
		    output.close();
		}
	    }

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
    }
}
