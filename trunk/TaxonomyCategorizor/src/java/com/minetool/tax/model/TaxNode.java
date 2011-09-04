package com.minetool.tax.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuYao
 * @version $Revision: 1.0 $
 */
public abstract class TaxNode {
    protected String _text = ""; //$NON-NLS-1$
    protected List<TaxNode> _taxList = new ArrayList<TaxNode>();
    protected TaxNode _parent;
    public TaxNode getParent() {
        return _parent;
    }

    /**
     * Constructor for TaxNode.
     * @param text String
     */
    public TaxNode(String text,TaxNode parent) {
	this._text = text;
	this._parent = parent;
    }

    /**
     * Method getText.
     * 
    
     * @return String */
    protected String getText() {
	return this._text;
    }

    /**
     * Method setText.
     * 
     * @param text
     *            String
     */
    protected void setText(String text) {
	this._text = text;
    }

    /**
     * Method getChildList.
     * 
    
     * @return List<TaxNode> */
    public List<TaxNode> getChildList() {
	return this._taxList;
    }

    /**
     * Method setChildList.
     * 
     * @param inList
     *            List<TaxNode>
     */
    public void setChildList(List<TaxNode> inList) {
	this._taxList = inList;
    }
}
