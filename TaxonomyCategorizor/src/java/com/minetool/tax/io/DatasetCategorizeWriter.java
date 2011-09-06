/**
 * 2011 HuYao
 */
package com.minetool.tax.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author HuYao
 * 
 * @version $Revision: 1.0 $
 */
public class DatasetCategorizeWriter {

    /**
     * Method writeToFile.
     * 
     * @param path
     *            String
     * @param data
     *            HashMap<String,List<String>>
     * 
     * @throws IOException
     */
    public static void writeToFile(String path,
	    HashMap<String, List<String>> data) throws IOException {
	if (data == null || data.size() == 0)
	    return;
	Set<String> keys = data.keySet();
	for (String key : keys) {
	    File file = new File(path + File.separator + key + ".txt"); //$NON-NLS-1$
	    if (!file.exists())
		file.createNewFile();
	    BufferedWriter output = new BufferedWriter(new FileWriter(file));
	    try {
		List<String> list = data.get(key);
		if (list != null && list.size() > 0) {
		    for (String value : list) {
			output.write(value);
			output.newLine();
		    }
		}

	    } finally {
		output.close();
	    }

	}

    }
}
