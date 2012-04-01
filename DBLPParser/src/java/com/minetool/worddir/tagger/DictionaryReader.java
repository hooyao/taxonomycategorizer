package com.minetool.worddir.tagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DictionaryReader {

    public static HashMap<Integer, String> readFromFile(String path)
	    throws IOException {
	File file = new File(path);
	HashMap<Integer, String> map = new HashMap<Integer, String>();
	try {
	    int idx = 1;
	    int lineIdx = 0;
	    BufferedReader input = new BufferedReader(new FileReader(file));
	    boolean legelFile = true;
	    try {
		String line = null;
		int lineCount = 0;
		while (lineIdx < 2 && (line = input.readLine()) != null) {
		    if (!line.trim().equalsIgnoreCase("bow_int4str")
			    && lineIdx == 0) {
			legelFile = false;
		    }
		    if (lineIdx == 1 && line.trim().matches("\\b\\d*\\b")
			    && lineIdx == 1) {
			lineCount = Integer.valueOf(line.trim()).intValue();
		    }
		   
		    lineIdx++;
		}
		if(!legelFile)
		    throw new IOException("file aint legel");
		while ((line = input.readLine()) != null && idx <= lineCount) {
		    if (lineIdx >= 2) {
			if (line.matches("\\b(\\w*)\\b")) { // match if it is
							    // word
			    String trimmed = line.trim();
			    map.put(new Integer(idx), trimmed);
			}
			idx++;
		    }
		    lineIdx++;
		}
	    } finally {
		input.close();
	    }
	} catch (IOException ex) {
	    throw ex;
	}
	return map;
    }
}
