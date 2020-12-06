package com.example.internalfilehandling;

public class ExampleItem {

    private String line1,line2;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public ExampleItem(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    public ExampleItem() {
    }
}
