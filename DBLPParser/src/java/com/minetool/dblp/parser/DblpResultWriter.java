package com.minetool.dblp.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class DblpResultWriter {
    private File f;
    private BufferedWriter bw;

    public DblpResultWriter(String path) {
	this.f = new File(path);
    }

    public void open() throws IOException {
	if (this.f != null) {
	    if (!this.f.exists())
		this.f.createNewFile();
	    this.bw = new BufferedWriter(new FileWriter(f));
	}
    }

    public void close() throws IOException {
	if (this.bw != null) {
	    this.bw.flush();
	    this.bw.close();
	}
    }

    public void writeObjectIn(HashMap<String, Integer> in) throws IOException {
	if (in != null && this.bw != null) {
	    Set<String> keys = in.keySet();
	    for (String s : keys) {
		this.bw.append(s + " " + in.get(s).toString());
		this.bw.newLine();
	    }
	}
    }

    public void writeStringIn(String s) throws IOException {
	if (s != null && this.bw != null) {
	    this.bw.write(s);
	}
    }
}