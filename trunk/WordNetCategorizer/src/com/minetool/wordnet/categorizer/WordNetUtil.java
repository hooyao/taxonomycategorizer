/**
 * 2011 HuYao
 */
package com.minetool.wordnet.categorizer;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;


/**
 * @author Hu Yao
 * 
 */
public class WordNetUtil {

    private final static WordNetUtil _instance = new WordNetUtil();

    private WordNetUtil() {
	super();
	try {
	    String propertyDir = WordNetUtil.class
		    .getResource("WordNetUtil.class").getPath()
		    .replace("/categorizer/WordNetUtil.class", "/resource/dict");
	    System.setProperty("wordnet.database.dir", propertyDir);
	    NounSynset nounSynset;
	    NounSynset[] hyponyms;

	    WordNetDatabase database = WordNetDatabase.getFileInstance();
	    Synset[] synsets = database.getSynsets("fly", SynsetType.NOUN);
	    for (int i = 0; i < synsets.length; i++) {
		nounSynset = (NounSynset) (synsets[i]);
		hyponyms = nounSynset.getHyponyms();
		System.err.println(nounSynset.getWordForms()[0] + ": "
			+ nounSynset.getDefinition() + ") has "
			+ hyponyms.length + " hyponyms");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static WordNetUtil getInstance() {
	return _instance;
    }

    public static void main(String[] args) {

	WordNetUtil wnu = WordNetUtil.getInstance();
    }
}
