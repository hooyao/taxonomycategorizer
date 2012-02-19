/**
 * 2011 HuYao
 */
package com.minetool.tax.categorizer.acm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import test.com.minetool.tax.model.data.testContentHandler;

import com.minetool.tax.categorizer.ITaxCategorizer;
import com.minetool.tax.model.TaxNode;
import com.minetool.tax.model.TaxRoot;
import com.minetool.tax.model.data.ACMContentHandler;
import com.minetool.wordnet.categorizer.RETokenizer;
import com.minetool.wordnet.categorizer.WordNetUtil;

/**
 * @author HuYao
 *
 * @version $Revision: 1.0 $
 */
public class ACMTaxCategorizer implements ITaxCategorizer {
    
    private final HashMap<String, TaxNode> _mapping;
    
    private final HashMap<String, TaxNode> _trimMapping;
    
    /**
     * Constructor for ACMTaxCategorizer.
    
    
     * @throws SAXException *
     * @throws IOException 
     * 
     */
    public ACMTaxCategorizer() throws SAXException, IOException{
	InputStream is = testContentHandler.class.getClass()
		.getResourceAsStream(
			"/com/minetool/tax/model/data/acm/acm_tax.xml"); //$NON-NLS-1$
	String s = new Scanner(is).useDelimiter("\\A").next(); //$NON-NLS-1$
	byte[] data = s.getBytes();
	XMLReader parser = XMLReaderFactory.createXMLReader();
	ACMContentHandler contentHandler = new ACMContentHandler();
	parser.setContentHandler(contentHandler);
	ByteArrayInputStream boi = new ByteArrayInputStream(data);
	InputSource source = new InputSource(boi);
	parser.parse(source);
	TaxRoot root = contentHandler.getRootModel();
	this._mapping = root.getMapping();
	
	this._trimMapping = new HashMap<String, TaxNode>();
	
	Set<String> keys = this._mapping.keySet();
	for (String key : keys) {
	    RETokenizer st = new RETokenizer(key, "\\b\\w+\'*\\w*\\b", false);
	    ArrayList<String> tkList = new ArrayList<String>();
	    StringBuilder sb= new StringBuilder();
	    while (st.hasNext()) {
		String tok = st.next();
		String[] baseforms = WordNetUtil.getInstance().getBaseForm(tok);
		if(baseforms!=null && baseforms.length>0){
		    tok = baseforms[0];
		}
		tkList.add(tok);
		sb.append(tok+" ");
	    }
	    this._trimMapping.put(sb.toString(), this._mapping.get(key));
	}
    }

    /* (non-Javadoc)
     * @see com.minetool.tax.categorizer.ITaxCategorizer#categorizeTaxList(java.util.List)
     */
    @Override
    public HashMap<String, List<String>> categorizeTaxList(List<String> taxList,int depth) {
	if(taxList==null) return null;
	HashMap<String,String> buffer = new HashMap<String, String>();
	for (String tax : taxList) {
	    String taxPost = tax.trim().toLowerCase();
	    if (this._mapping.containsKey(taxPost)) {
		TaxNode node = this._mapping.get(taxPost);
		String comPath = node.getCompletePath();
		buffer.put(taxPost, comPath);
	    }
	}
	
	HashMap<String, List<String>> dataMap = new HashMap<String, List<String>>();
	Set<String> taxSet = buffer.keySet();
	for (String tax : taxSet) {
	    int offset = depth * 2 - 1;
	    if (buffer.containsKey(tax)) {
		String path = buffer.get(tax);
		if (path.length() > offset) {
		    String sub = path.substring(0, offset);
		    if (!dataMap.containsKey(sub)) {
			dataMap.put(sub, new ArrayList<String>());
		    }
		    List<String> list = dataMap.get(sub);
		    list.add(tax);
		}
	    }
	}
	return dataMap;
    }

    @Override
    public List<String> find1WordMatch(String word) {
	List<String> result= new ArrayList<String>();
	if(this._trimMapping==null) return null;
	Set<String> keys = this._trimMapping.keySet();
	for (String key : keys) {
	    StringTokenizer st = new StringTokenizer(key);
	    while(st.hasMoreTokens()){
		String tok = st.nextToken();
		if(tok.equalsIgnoreCase(word)){
		    result.add(this._trimMapping.get(key).getCompletePath());
		    break;
		}
	    }
	}
	return result;
    }

}
