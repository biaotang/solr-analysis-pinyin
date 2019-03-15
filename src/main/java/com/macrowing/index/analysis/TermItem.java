package com.macrowing.index.analysis;

/**
 * @author	biao.tang
 * 2019年3月12日
 */
public class TermItem implements Comparable<TermItem>{
    String term;
    int startOffset;
    int endOffset;
    int position;
    public TermItem(String term,int startOffset,int endOffset,int position){
        this.term=term;
        this.startOffset=startOffset;
        this.endOffset=endOffset;
        this.position=position;
    }

    @Override
    public String toString() {
        return term;
    }

    @Override
    public int compareTo(TermItem o) {
        return this.position-o.position;
    }
}
