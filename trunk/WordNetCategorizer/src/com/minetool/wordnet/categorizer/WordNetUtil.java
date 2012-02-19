/**
 * 2011 HuYao
 */
package com.minetool.wordnet.categorizer;

import org.junit.Test.None;

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
    
    private final static String ThisClassName = "WordNetUtil.class";
    
    private final static String ThisClassPath = "/categorizer/WordNetUtil.class";
    
    private final static String ResourceDict = "/resource/dict";
    
    private final static String PropString = "wordnet.database.dir";
    
    private WordNetDatabase _database;

    private WordNetUtil() {
	super();
	String propertyDir = WordNetUtil.class.getResource(ThisClassName)
		.getPath().replace(ThisClassPath, ResourceDict);
	System.setProperty(PropString, propertyDir);

	NounSynset nounSynset;
	NounSynset[] hyponyms;

	this._database = WordNetDatabase.getFileInstance();

	Synset[] synsets = _database.getSynsets("DBMS", SynsetType.NOUN);
	for (int i = 0; i < synsets.length; i++) {
	    nounSynset = (NounSynset) (synsets[i]);
	    hyponyms = nounSynset.getHyponyms();
	    System.err.println(nounSynset.getWordForms()[0] + ": "
		    + nounSynset.getDefinition() + ") has " + hyponyms.length
		    + "  ");
	}

	String[] forms = _database
		.getBaseFormCandidates("fly", SynsetType.NOUN);
	for (String string : forms) {
	    System.out.println(string + "\n");
	}
    }

    public static WordNetUtil getInstance() {
	return _instance;
    }

    public boolean isBaseForm(String in) {
	if (_database == null)
	    throw new RuntimeException("WordNet Database Error");
	String[] forms = _database.getBaseFormCandidates(in, SynsetType.NOUN);
	if (forms != null && forms.length > 0) {
	    return false;
	}
	return true;
    }

    public String[] getBaseForm(String in) {
	if (_database == null)
	    throw new RuntimeException("WordNet Database Error");
	String forms[] = _database.getBaseFormCandidates(in, SynsetType.NOUN);
	if(forms.length==0 && in.toUpperCase().equals(in)){
	    Synset[] synsets = _database.getSynsets(in,SynsetType.NOUN);
	    for (Synset synset : synsets) {
		if(synset instanceof NounSynset){
		    NounSynset ns = (NounSynset) synset;
		    String[] fs = ns.getWordForms();
		    if(fs!=null && fs.length>0){
			for (String s : fs) {
			    if(!s.equalsIgnoreCase(in))
				return new String[]{s};
			}
		    }
		}
	    }
	}
	return forms;
    }
}
