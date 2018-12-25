package com.springboot.util.xls;


public enum Formula {
    SUM("SUM"),
    AVERAGE("AVERAGE"),
    MAX("MAX");

    private final static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private String formula;

    private Formula(String formula) {
        this.formula = formula;
    }

    public static char getCharacter(int index) {
        return alphabet[index];
    }

    public String getFormula(int fromColumn, int fromRow, int toColumn, int toRow, String prefix, String suffix) {
        StringBuilder builder = new StringBuilder("");
        if (null != prefix) {
            builder.append('"').append(prefix).append('"').append('&');
        }
        builder.append(formula).append('(').append(getCharacter(fromColumn)).append(fromRow).append(':').append(getCharacter(toColumn)).append(toRow).append(')');
        if (null != suffix) {
            builder.append('&').append('"').append(suffix).append('"');
        }
        return builder.toString();
    }
}
