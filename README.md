Pinyin Analysis for Solr
==================================

This Pinyin Analysis plugin is used to do conversion between Chinese characters and Pinyin, integrates NLP tools (https://github.com/NLPchina/nlp-lang).

    --------------------------------------------------
    | Pinyin   Analysis Plugin      | Solr           |
    --------------------------------------------------
    | 6.6.2                         | 6.6.2          |
    --------------------------------------------------

The plugin includes analyzer: `pinyin` ,  tokenizer: `pinyin` and  token-filter:  `pinyin`.

** Optional Parameters ** 

* `keep_first_letter` when this option enabled,  eg: `刘德华`>`ldh`, default: true
* `keep_separate_first_letter` when this option enabled, will keep first letters separately,  eg: `刘德华`>`l`,`d`,`h`, default: false, NOTE: query result maybe too fuzziness due to term too frequency
* `limit_first_letter_length` set max length of the first_letter result, default: 16
* `keep_full_pinyin` when this option enabled, eg: `刘德华`> [`liu`,`de`,`hua`], default: true
* `keep_joined_full_pinyin` when this option enabled, eg: `刘德华`> [`liudehua`], default: false
* `keep_none_chinese` keep non chinese letter or number in result, default: true
* `keep_none_chinese_together` keep non chinese letter together, default: true, eg: `DJ音乐家` -> `DJ`,`yin`,`yue`,`jia`, when set to `false`, eg: `DJ音乐家` -> `D`,`J`,`yin`,`yue`,`jia`, NOTE: `keep_none_chinese` should be enabled first
* `keep_none_chinese_in_first_letter` keep non Chinese letters in first letter, eg: `刘德华AT2016`->`ldhat2016`, default: true
* `keep_none_chinese_in_joined_full_pinyin` keep non Chinese letters in joined full pinyin, eg: `刘德华2016`->`liudehua2016`, default: false
* `none_chinese_pinyin_tokenize` break non chinese letters into separate pinyin term if they are pinyin, default: true, eg: `liudehuaalibaba13zhuanghan` -> `liu`,`de`,`hua`,`a`,`li`,`ba`,`ba`,`13`,`zhuang`,`han`, NOTE:  `keep_none_chinese` and `keep_none_chinese_together` should be enabled first
* `keep_original` when this option enabled, will keep original input as well, default: false
* `lowercase`  lowercase non Chinese letters, default: true
* `trim_whitespace` default: true
* `remove_duplicated_term` when this option enabled, duplicated term will be removed to save index, eg: `de的`>`de`, default: false,  NOTE: position related query maybe influenced
* `ignore_pinyin_offset` after 6.0, offset is strictly constrained, overlapped tokens are not allowed, with this parameter, overlapped token will allowed by ignore offset, please note, all position related query or highlight will become incorrect, you should use multi fields and specify different settings for different query purpose. if you need offset, please set it to false. default: true.



1. using tokenizer

```
<fieldType name="text_py" class="solr.TextField">
	<analyzer type="index">
	    <tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" />
    	<tokenizer class="com.macrowing.index.analysis.PinyinTokenizerFactory" keep_original="true" keep_joined_full_pinyin="true" />
 	</analyzer>
	<analyzer type="query">
    	<tokenizer class="com.macrowing.index.analysis.PinyinTokenizerFactory" keep_original="true" keep_joined_full_pinyin="true" />
	</analyzer>
</fieldType>
```

2. using token filter

```
<fieldType name="text_py" class="solr.TextField">
	<analyzer type="index">
	    <tokenizer class="org.wltea.analyzer.lucene.IKTokenizerFactory" />
    	<filter class="com.macrowing.index.analysis.PinyinTokenFilterFactory" keep_original="true" keep_joined_full_pinyin="true" />
 	</analyzer>
	<analyzer type="query">
    	<filter class="com.macrowing.index.analysis.PinyinTokenFilterFactory" keep_original="true" keep_joined_full_pinyin="true" />
	</analyzer>
</fieldType>
```



