package com.macrowing.index.analysis;

import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import com.macrowing.analysis.PinyinConfig;

public class PinyinAbbreviationsTokenizerFactory extends TokenizerFactory {

    public PinyinAbbreviationsTokenizerFactory(Map<String, String> args) {
		super(args);
	}
    
	@Override
	public Tokenizer create(AttributeFactory factory) {
		PinyinConfig config=new PinyinConfig();
        config.keepFirstLetter=true;
        config.keepFullPinyin=false;
        config.keepNoneChinese=false;
        config.keepNoneChineseTogether=true;
        config.noneChinesePinyinTokenize=false;
        config.keepOriginal=false;
        config.lowercase=true;
        config.trimWhitespace=true;
        config.keepNoneChineseInFirstLetter=true;
        return new PinyinTokenizer(config);
	}
}
