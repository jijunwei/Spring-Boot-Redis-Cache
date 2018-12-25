package com.springboot.util.xls;

import java.util.HashMap;
import java.util.Map;

public class Title {

    private String name;

    private String fieldName;

    private ToType toType;

    private Style style;

    private Map<Rule, Style> ruleStyleMapper;
    //是否需要统计
    private String prefix;
    private Formula formula;
    private String suffix;

    public Title(String name, String fieldName, ToType toType, Style style, Map<Rule, Style> ruleStyleMapper, Formula formula) {
        this.name = name;
        this.fieldName = fieldName;
        this.toType = toType != null ? toType : ToType.String;
        this.style = style;
        this.ruleStyleMapper = ruleStyleMapper != null ? ruleStyleMapper : new HashMap<Rule, Style>();
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ToType getToType() {
        return toType;
    }

    public Map<Rule, Style> getRuleStyleMapper() {
        return ruleStyleMapper;
    }

    public Formula getFormula() {
        return formula;
    }

    public Style getStyle() {
        return style;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public enum ToType {
        Number,
        String,
        Currency,
        Date
    }
}
