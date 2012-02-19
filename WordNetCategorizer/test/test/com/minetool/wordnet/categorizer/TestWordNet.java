package test.com.minetool.wordnet.categorizer;

import org.junit.Test;

import com.minetool.wordnet.categorizer.WordNetUtil;

public class TestWordNet
{

    @Test
    public void testGetBaseForm(){
	String[] ss = WordNetUtil.getInstance().getBaseForm("bikes");
	System.out.println(ss[0]);
    }
}
