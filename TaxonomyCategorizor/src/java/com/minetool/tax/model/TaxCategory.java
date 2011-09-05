package com.minetool.tax.model;

/**
 * @author HuYao
 * @version $Revision: 1.0 $
 */
public class TaxCategory extends TaxNode {
    /**
     * @param text
     * @param parent
     *            TaxNode
     */
    public TaxCategory(String text, TaxNode parent) {
	super(text, parent);
    }

    /**
     * Method getTitle.
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder pathSb = new StringBuilder();
	TaxNode n = this;
	pathSb.insert(0, n.getPath());
	while (n.getParent() != null) {
	    n = n.getParent();
	    if (n.getPath() != null & n.getPath().length() > 0) {
		pathSb.insert(0, "."); //$NON-NLS-1$
		pathSb.insert(0, n.getPath());
	    }
	}

	StringBuilder sb = new StringBuilder();
	sb.append(pathSb + "  "); //$NON-NLS-1$
	sb.append(this.getTitle());
	sb.append("\n"); //$NON-NLS-1$
	for (TaxNode node : this.getChildList()) {
	    sb.append(node.toString());
	    sb.append("\n"); //$NON-NLS-1$
	}
	return sb.toString();
    }

}
