package com.minetool.dblp.parser.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.minetool.worddir.tagger.DictionaryReader;
import com.minetool.worddir.tagger.Tagger;

public class TestDirTagger {

    @Test
    public void TestTagger() {

	try {
	    HashMap<Integer, String> words = DictionaryReader
		    .readFromFile("Z:\\rainbow\\vocabulary"); //$NON-NLS-1$
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
				output.write(applyRule(classMap.get(i)));
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
			output.write(String.valueOf(getClassCount()));
			output.newLine();
			for (String value : clsLabels) {
			    if (value == null || value.length() == 0) {
				output.write("NA");
			    } else {
				output.write(applyRule(value));
				output.newLine();
			    }
			}
		    }

		} finally {
		    output.close();
		}
	    }

	    {
		BufferedReader input = new BufferedReader(new FileReader(
			"z:\\classmap.txt"));
		int classCount = 0;
		HashMap<String, List<String>> csLabelmap = new HashMap<String, List<String>>();
		try {
		    String cs = input.readLine();
		    classCount = Integer.parseInt(cs);
		    String newLine = "";
		    while ((newLine = input.readLine()) != null) {
			csLabelmap.put(newLine.trim(), new ArrayList<String>());
		    }
		    input.close();
		} catch (Exception e) {
		    // TODO: handle exception
		}
		File filein = new File("z:\\wordclass.txt"); //$NON-NLS-1$

		int i = 1;
		input = new BufferedReader(new FileReader(filein));
		try {
		    String newLine = "";
		    while ((newLine = input.readLine()) != null) {
			if (csLabelmap.containsKey(newLine.trim())) {
			    csLabelmap.get(newLine.trim()).add(
				    String.valueOf(i));
			}
			i++;
		    }
		} finally {
		    input.close();
		}

		Comparator<String> numStrComp = new Comparator<String>() {
		    
		    @Override
		    public int compare(String o1, String o2) {
			int i1=0,i2=0;
			try {
			    i1 = Integer.parseInt(o1);
			    i2 = Integer.parseInt(o2);
			} catch (Exception e) {
			    i1=i2=0;
			}
			return i1-i2;
		    }
		};
		File file = new File("z:\\wordclass_gt.txt"); //$NON-NLS-1$
		if (!file.exists())
		    file.createNewFile();
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		try {
		    String[] keys = csLabelmap.keySet().toArray(new String[0]);
		    Arrays.sort(keys);
		    for (String s : keys) {
			output.write(s);
			output.newLine();
			List<String> values = csLabelmap.get(s);
			Collections.sort(values, numStrComp);
			for (String s1 : values) {
			    output.write(s1);
			    output.newLine();
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

    private String applyRule(String in) {
	if (in.equalsIgnoreCase("B") || in.equalsIgnoreCase("C"))
	    return "C0";
	if (in.equalsIgnoreCase("D") || in.equalsIgnoreCase("F")
		|| in.equalsIgnoreCase("J") || in.equalsIgnoreCase("G"))
	    return "C1";
	if (in.equalsIgnoreCase("H") || in.equalsIgnoreCase("I"))
	    return "C2";
	if (in.equalsIgnoreCase("K"))
	    return "C3";
	return "C4";
    }

    private int getClassCount() {
	return 4;
    }
}
