package com.minetool.tax.model;

/**
 * @author HuYao
 * @version $Revision: 1.0 $
 */
public class TaxCategory extends TaxNode {
    /**
     * @param text
     */
    public TaxCategory(String text, TaxNode parent) {
	super(text, parent);
    }

    /**
     * Method getTitle.
     * 
     * @return String
     */
    public String getTitle() {
	return getText();
    }

    /**
     * Method setTitle.
     * 
     * @param title
     *            String
     */
    public void setTitle(String title) {
	setText(title);
    }
}
