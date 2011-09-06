/**
 * 2011 HuYao
 */
package com.minetool.tax.categorizer;

import java.util.HashMap;
import java.util.List;

/**
 * @author HuYao
 *
 * @version $Revision: 1.0 $
 */
public interface ITaxCategorizer {

    /**
     * @param taxList
     * @param depth
    
     * @return HashMap<String,List<String>>
     */
    HashMap<String, List<String>> categorizeTaxList(List<String> taxList,
	    int depth);

}
