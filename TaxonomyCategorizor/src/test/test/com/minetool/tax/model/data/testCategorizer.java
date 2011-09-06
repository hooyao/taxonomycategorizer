/**
 * 2011 HuYao
 */
package test.com.minetool.tax.model.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.minetool.tax.categorizer.ITaxCategorizer;
import com.minetool.tax.categorizer.factory.TaxCategorizerFactory;
import com.minetool.tax.io.DatasetCategorizeWriter;
import com.minetool.tax.io.DatasetFileReader;

/**
 * @author HuYao
 *
 * @version $Revision: 1.0 $
 */
public class testCategorizer {
    
    @SuppressWarnings({ "static-method", "javadoc" })
    @Test
    public void testACMCategorize(){
	 try {
	    List<String> list = DatasetFileReader.readFromFile("z:\\data.txt"); //$NON-NLS-1$
	    ITaxCategorizer taxCat= TaxCategorizerFactory.createTaxCategorizer(TaxCategorizerFactory.TAX_ACM);
	    HashMap<String, List<String>> cats = taxCat.categorizeTaxList(list, 2);
	    DatasetCategorizeWriter.writeToFile("z:\\", cats); //$NON-NLS-1$
	    
	} catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
