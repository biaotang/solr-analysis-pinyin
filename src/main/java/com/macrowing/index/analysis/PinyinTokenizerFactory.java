package com.macrowing.index.analysis;

import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import com.macrowing.analysis.PinyinConfig;

public class PinyinTokenizerFactory extends TokenizerFactory {
	
	private PinyinConfig config;

	public PinyinTokenizerFactory(Map<String, String> args) {
		super(args);
		config=new PinyinConfig(args);
	}
    
	@Override
	public Tokenizer create(AttributeFactory factory) {
		return new PinyinTokenizer(config);
	}
    
}

