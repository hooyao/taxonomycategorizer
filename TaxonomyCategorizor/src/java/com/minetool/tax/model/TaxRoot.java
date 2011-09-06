/**
 * 2011 HuYao
 */
package com.minetool.tax.model;

import java.util.HashMap;

/**
 * @author HuYao
 *
 * @version $Revision: 1.0 $
 */
public class TaxRoot extends TaxCategory {

    private HashMap<String, TaxNode> mapping = new HashMap<String, TaxNode>();
    /**
    
     * @return the mapping */
    public HashMap<String, TaxNode> getMapping() {
        return this.mapping;
    }

    /**
     * @param text
    
     */
    public TaxRoot(String text) {
	super(text, null);
    }

}
