package com.app.gland.common;


public enum CommentSortEnum {

    NAME0(0,"血检"),
    NAME1(1,"X光"),
    NAME2(2,"超声"),
    NAME3(3,"CT"),
    NAME4(4,"手术");

    private final Integer value;
    private final String desc;

    CommentSortEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
    
    public static CommentSortEnum getTargetName(Integer value) {
        for (CommentSortEnum enums : CommentSortEnum.values()) {
            if (enums.getValue().equals(value)) {
                return enums;
            }
        }
        return null;
    }
    
    
    
}
