Pinyin Analysis for Solr
==================================

This Pinyin Analysis plugin is used to do conversion between Chinese characters and Pinyin, integrates NLP tools (https://github.com/NLPchina/nlp-lang).

    --------------------------------------------------
    | Pinyin   Analysis Plugin     | Lucene-Solr    |
    --------------------------------------------------
    | master                       | 8.0.0 -> master|
    --------------------------------------------------
    | v7.7.1                       | 7.7.1          |
    --------------------------------------------------
    | v7.7.0                       | 7.7.0          |
    --------------------------------------------------
    | v7.6.0                       | 7.6.0          |
    --------------------------------------------------
    | v7.5.0                       | 7.5.0          |
    --------------------------------------------------
    | v7.4.0                       | 7.4.0          |
    --------------------------------------------------
    | v7.3.1                       | 7.3.1          |
    --------------------------------------------------
    | v7.3.0                       | 7.3.0          |
    --------------------------------------------------
    | v7.2.0                       | 7.2.0          |
    --------------------------------------------------
    | v7.1.0                       | 7.1.0          |
    --------------------------------------------------
    | v7.0.1                       | 7.0.1          |
    --------------------------------------------------
    | v7.0.0                       | 7.0.0          |
    --------------------------------------------------
    | v6.6.5                       | 6.6.5          |
    --------------------------------------------------
    | v6.6.4                       | 6.6.4          |
    --------------------------------------------------
    | v6.6.3                       | 6.6.3          |
    --------------------------------------------------
    | v6.6.2                       | 6.6.2          |
    --------------------------------------------------
    | v6.6.1                       | 6.6.1          |
    --------------------------------------------------
    | v6.6.0                       | 6.6.0          |
    --------------------------------------------------
    | v6.5.1                       | 6.5.1          |
    --------------------------------------------------
    | v6.5.0                       | 6.5.0          |
    --------------------------------------------------
    | v6.4.2                       | 6.4.2          |
    --------------------------------------------------
    | v6.4.1                       | 6.4.1          |
    --------------------------------------------------
    | v6.4.0                       | 6.4.0          |
    --------------------------------------------------
    | v6.3.0                       | 6.3.0          |
    --------------------------------------------------
    | v6.2.1                       | 6.2.1          |
    --------------------------------------------------
    | v6.2.0                       | 6.2.0          |
    --------------------------------------------------
    | v6.1.0                       | 6.1.0          |
    --------------------------------------------------
    | v6.0.1                       | 6.0.1          |
    --------------------------------------------------
    | v6.0.0                       | 6.0.0          |
    --------------------------------------------------
    | v5.5.5                       | 5.5.5          |
    --------------------------------------------------
    | v5.5.4                       | 5.5.4          |
    --------------------------------------------------
    | v5.5.3                       | 5.5.3          |
    --------------------------------------------------
    | v5.5.2                       | 5.5.2          |
    --------------------------------------------------
    | v5.5.1                       | 5.5.1          |
    --------------------------------------------------
    | v5.5.0                       | 5.5.0          |
    --------------------------------------------------
    | v5.4.1                       | 5.4.1          |
    --------------------------------------------------
    | v5.4.0                       | 5.4.0          |
    --------------------------------------------------
    | v5.3.2                       | 5.3.2          |
    --------------------------------------------------
    | v5.3.1                       | 5.3.1          |
    --------------------------------------------------
    | v5.3.0                       | 5.3.0          |
    --------------------------------------------------
    | v5.2.1                       | 5.2.1          |
    --------------------------------------------------
    | v5.2.0                       | 5.2.0          |
    --------------------------------------------------
    | v5.1.0                       | 5.1.0          |
    --------------------------------------------------
    | v5.0.0                       | 5.0.0          |
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



