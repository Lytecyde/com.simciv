package com.simciv;

import static com.simciv.GameStats.idCount;

public class ID {
    int code;
    String name;

    public ID(String n){
        setName(n);
        code = setCodeValue();
    }

    private int setCodeValue(){
        GameStats.idCount++;
        return idCount;
    }

    public int getCode() {
        return code;
    }

    private void setName(String n) {
        this.name =  n;
    }

    public String getName() {
        return name;
    }
}
