/**
 * 2011 HuYao
 */
package com.minetool.tax.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HuYao
 * 
 * @version $Revision: 1.0 $
 */
public class DatasetFileReader {
    /**
     * Method readFromFile.
     * 
     * @param path
     *            String
     * @return List<String>
     * @throws IOException
     */
    public static List<String> readFromFile(String path) throws IOException {
	File file = new File(path);
	List<String> list = new ArrayList<String>();
	try {
	    BufferedReader input = new BufferedReader(new FileReader(file));
	    try {
		String line = null;
		while ((line = input.readLine()) != null) {
		    list.add(line);
		}
	    } finally {
		input.close();
	    }
	} catch (IOException ex) {
	    throw ex;
	}
	return list;
    }

}
