package com.macrowing.index.analysis;

import org.apache.lucene.analysis.Analyzer;

import com.macrowing.analysis.PinyinConfig;

/**
 * @author	biao.tang
 * 2019年3月12日
 */
public final class PinyinAnalyzer extends Analyzer {

    private PinyinConfig config;

    public PinyinAnalyzer(PinyinConfig config) {
        this.config=config;
    }
    
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
    	return new TokenStreamComponents(new PinyinTokenizer(config));
    }

}
