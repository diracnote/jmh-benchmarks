package com.diracnote.enums;

import java.util.HashMap;
import java.util.Map;

public enum Month {

    JANUARY("JANUARY"),

    FEBRUARY("FEBRUARY"),

    MARCH("MARCH"),

    APRIL("APRIL"),

    MAY("MAY"),

    JUNE("JUNE"),

    JULY("JULY"),

    AUGUST("AUGUST"),

    SEPTEMBER("SEPTEMBER"),

    OCTOBER("OCTOBER"),

    NOVEMBER("NOVEMBER"),

    DECEMBER("DECEMBER");

    private String code;

    Month(String code) {
        this.code = code;
    }

    public static Month getByArrayCode(String code) {
        for (Month month : values()) {
            if (code.equals(month)) {
                return month;
            }
        }
        return null;
    }

    private static final Map<String, Month> map = new HashMap<>();

    static {
        for (Month month : values()) {
            map.put(month.getCode(), month);
        }
    }

    public static Month getByMapCode(String code) {
        return map.get(code);
    }

    public String getCode() {
        return code;
    }

    public Month setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "Month{" +
                "code='" + code + '\'' +
                '}';
    }
}
