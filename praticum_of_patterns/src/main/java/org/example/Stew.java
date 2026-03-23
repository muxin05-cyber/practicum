package org.example;

public class Stew implements Stew_instruction{
    @Override
    public String getDescription(){
        return "Нордское рагу";
    }

    @Override
    public int getCost() {
        return 50;
    }

}
