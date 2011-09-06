package com.minetool.tax.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuYao
 * @version $Revision: 1.0 $
 */
public abstract class TaxNode {
    protected String _path = ""; //$NON-NLS-1$

    /**
     * 
    
     * @return the _path */
    public String getPath() {
	return this._path;
    }

    /**
     * @param path
     *            the _path to set
     */
    public void setPath(String path) {
	this._path = path;
    }

    protected String _text = ""; //$NON-NLS-1$
    protected List<TaxNode> _taxList = new ArrayList<TaxNode>();
    protected TaxNode _parent;

    /**
     * Method getParent.
     * 
    
     * @return TaxNode */
    public TaxNode getParent() {
	return this._parent;
    }

    /**
     * Constructor for TaxNode.
     * 
     * @param text
     *            String
     * @param parent
     *            TaxNode
     */
    public TaxNode(String text, TaxNode parent) {
	this._text = text;
	this._parent = parent;
    }

    /**
     * Method getText.
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return getText();
    }

    /**
     * Method getCompletePath.
     * @return String
     */
    public abstract String getCompletePath();
}
