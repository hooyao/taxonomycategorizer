package com.minetool.tax.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author HuYao
 * @version $Revision: 1.0 $
 */
public class TaxElement extends TaxNode {

    /**
     * @param text
     */
    public TaxElement(String text, TaxNode parent) {
	super(text, parent);
    }

    /**
     * Method getEleEntry.
     * 
     * 
     * @return String
     */
    public String getEleEntry() {
	return getText();
    }

    /**
     * Method setEleEntry.
     * 
     * @param text
     *            String
     */
    public void setEleEntry(String text) {
	setText(text);
    }

    /**
     * Method getLabelList.
     * 
     * @return List<String>
     */
    public List<String> getLabelList() {
	StringTokenizer st = new StringTokenizer(this._text);
	ArrayList<String> list = new ArrayList<String>();
	while (st.hasMoreTokens()) {
	    String s = st.nextToken();
	    list.add(s);
	}
	return list;
    }
}
