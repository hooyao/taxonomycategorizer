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
     * Method categorizeTaxList.
     * @param taxList List<String>
     * @param depth int the depth of label. ex. "B.3.2.b" with depth=1, will be categorized to "B", depth=2 to "B.3",depth=3 to "B.3.2"
     * depth=5 will not be categorized
     * @return HashMap<String,List<String>> * @see com.minetool.tax.categorizer.ITaxCategorizer#categorizeTaxList(List<String>, int) */
    HashMap<String, List<String>> categorizeTaxList(List<String> taxList,
	    int depth);

}
