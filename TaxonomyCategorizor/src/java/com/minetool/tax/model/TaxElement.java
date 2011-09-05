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
     * @param parent
     *            TaxNode
     */
    public TaxElement(String text, TaxNode parent) {
	super(text, parent);
    }

    /**
     * Method getEleEntry.
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder pathSb = new StringBuilder();
	TaxNode node = this;
	pathSb.insert(0, node.getPath());
	while (node.getParent() != null) {
	    node = node.getParent();
	    if (node.getPath() != null & node.getPath().length() > 0) {
		pathSb.insert(0, "."); //$NON-NLS-1$
		pathSb.insert(0, node.getPath());
	    }
	}
	return pathSb + "  " + this.getEleEntry(); //$NON-NLS-1$
    }

}
