package com.macrowing.index.analysis;


import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import com.macrowing.analysis.PinyinConfig;

public class PinyinTokenFilterFactory extends TokenFilterFactory {
	
	private PinyinConfig config;
	
    public PinyinTokenFilterFactory(Map<String, String> args) {
		super(args);
		config = new PinyinConfig(args);
	}
    
	@Override
	public TokenStream create(TokenStream input) {
		return new PinyinTokenFilter(input, config);
	}
    
}
