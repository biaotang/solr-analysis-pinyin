package com.macrowing.analysis;

import java.util.Map;

/**
 * @author biao.tang
 * 2019年3月12日
 */
public class PinyinConfig {

    public boolean lowercase=true;
    public boolean trimWhitespace=true;
    public boolean keepNoneChinese=true;
    public boolean keepNoneChineseInFirstLetter =true;
    public boolean keepNoneChineseInJoinedFullPinyin =false;
    public boolean keepOriginal=false;
    public boolean keepFirstLetter=true;
    public boolean keepSeparateFirstLetter=false;
    public boolean keepNoneChineseTogether=true;
    public boolean noneChinesePinyinTokenize =true;
    public int     LimitFirstLetterLength=16;
    public boolean keepFullPinyin=true;
    public boolean keepJoinedFullPinyin =false;
    public boolean removeDuplicateTerm=false;
    public boolean fixedPinyinOffset =false;
    //  after 6.0, offset is strictly constrained, overlapped tokens are not allowed, with this parameter, overlapped token will allowed by ignore offset, please note, all position related query or highlight will become incorrect, you should use multi fields and specify different settings for different query purpose. if you need offset, please set it to false. default: true.
    public boolean ignorePinyinOffset =true;

    public PinyinConfig() {}
    public PinyinConfig(Map<String, String> args) {
        this.keepFirstLetter 							= getAsBoolean(args.get("keep_first_letter"),true);
        this.keepSeparateFirstLetter 					= getAsBoolean(args.get("keep_separate_first_letter"),false);
        this.keepFullPinyin 							= getAsBoolean(args.get("keep_full_pinyin"), true);
        this.keepJoinedFullPinyin 						= getAsBoolean(args.get("keep_joined_full_pinyin"), false);
        this.keepNoneChinese 							= getAsBoolean(args.get("keep_none_chinese"),true);
        this.keepNoneChineseTogether 					= getAsBoolean(args.get("keep_none_chinese_together"),true);
        this.noneChinesePinyinTokenize 					= getAsBoolean(args.get("none_chinese_pinyin_tokenize"),true);
        this.keepOriginal 								= getAsBoolean(args.get("keep_original"), false);
        this.LimitFirstLetterLength 					= getAsInt(args.get("limit_first_letter_length"), 16);
        this.lowercase 									= getAsBoolean(args.get("lowercase"), true);
        this.trimWhitespace 							= getAsBoolean(args.get("trim_whitespace"), true);
        this.keepNoneChineseInFirstLetter 				= getAsBoolean(args.get("keep_none_chinese_in_first_letter"), true);
        this.keepNoneChineseInJoinedFullPinyin 			= getAsBoolean(args.get("keep_none_chinese_in_joined_full_pinyin"), false);
        this.removeDuplicateTerm 						= getAsBoolean(args.get("remove_duplicated_term"), false);
        this.fixedPinyinOffset 							= getAsBoolean(args.get("fixed_pinyin_offset"), false);
        this.ignorePinyinOffset 						= getAsBoolean(args.get("ignore_pinyin_offset"), true);
    }
    
    boolean getAsBoolean(String value, boolean defaultValue) {
    	if (value == null || value.length() == 0) {
    		return defaultValue;
        }
    	if ("false".equals(value)) {
            return false;
        }
        if ("true".equals(value)) {
            return true;
        }
        throw new IllegalArgumentException("Failed to parse value [" + defaultValue + "] as only [true] or [false] are allowed.");
    }
    
    int getAsInt(String value, int defaultValue) {
    	if (value == null) {
            return defaultValue;
        }
    	try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse value [" + value + "]", e);
        }
    }
}
