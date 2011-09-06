/**
 * 2011 HuYao
 */
package com.minetool.tax.categorizer.factory;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.minetool.tax.categorizer.ITaxCategorizer;
import com.minetool.tax.categorizer.acm.ACMTaxCategorizer;

/**
 * @author HuYao
 * 
 * @version $Revision: 1.0 $
 */
public class TaxCategorizerFactory {

    /**
     * 
     */
    public static final int TAX_ACM = 0x01;

    /**
     * Method createTaxCategorizer.
     * @param type int
     * @return ITaxCategorizer
     * @throws SAXException 
     * @throws IOException 
    */
    public static ITaxCategorizer createTaxCategorizer(int type) throws SAXException, IOException {
	switch (type) {
	case 0x01:
	    return new ACMTaxCategorizer();
	}
	return null;
    }

}
