package com.macrowing;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import com.macrowing.analysis.PinyinConfig;
import com.macrowing.index.analysis.PinyinAnalyzer;
import com.macrowing.index.analysis.PinyinTokenFilter;

public class TestAnalysis {
	
	public static void pinyinToken(String value) throws Exception {
		PinyinConfig config = new PinyinConfig();
		config.keepOriginal = true;
		config.keepJoinedFullPinyin = true;
		config.keepFullPinyin = false;
		Analyzer analyzer = new PinyinAnalyzer(config);
		TokenStream ts = analyzer.tokenStream(null, new StringReader(value));
		
		ts.reset();
		CharTermAttribute ta = ts.addAttribute(CharTermAttribute.class);
		
		String chinese = "";
		while (ts.incrementToken()) {
			chinese = chinese + ta.toString() + " ";
	    }
	    System.out.println(chinese);
		analyzer.close();
	}

	@Test
	public void testPinyinToken() throws Exception {
		System.out.println("========Tokenizer=========");
//		pinyinToken("bushu");
		pinyinToken("bu署");
		pinyinToken("部署");
//		pinyinToken("wendang");
		pinyinToken("文dang");
//		pinyinToken("文档");
		pinyinToken("bswd");
		System.out.println("========Tokenizer=========");
		
		System.out.println();
		
		System.out.println("========Token Filter=========");
		pinyinTokenFilter("刘德华");
		System.out.println("========Token Filter=========");
	}
	
	public static void pinyinTokenFilter(String value) throws Exception {
		PinyinConfig config = new PinyinConfig();
		config.keepOriginal = true;
		config.keepJoinedFullPinyin = true;
		config.keepFullPinyin = false;
		Analyzer analyzer = new StandardAnalyzer();
		PinyinTokenFilter tf = new PinyinTokenFilter(analyzer.tokenStream(null, new StringReader(value)), config);
		
		tf.reset();
		String chinese = "";
		while (tf.incrementToken()) {
			CharTermAttribute ta = tf.getAttribute(CharTermAttribute.class);
			chinese = chinese + ta.toString() + " ";
	    }
	    System.out.println(chinese);
	    tf.close();
		analyzer.close();
	}
	
}
