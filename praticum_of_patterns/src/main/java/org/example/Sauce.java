package org.example;

public class Sauce extends Decorator{

    public Sauce(Stew_instruction stew_instruction) {
        super(stew_instruction);
    }
    @Override
    public int getCost(){
        return super.getCost() + 10;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " + Огненный соус";
    }
}
