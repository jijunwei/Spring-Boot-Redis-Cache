package com.springboot.util.xls;


public class Style {

    private short fontColor;

    private short backgroundColor;

    public Style(short fontColor, short backgroundColor) {
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
    }

    public short getFontColor() {
        return fontColor;
    }

    public short getBackgroundColor() {
        return backgroundColor;
    }
}
