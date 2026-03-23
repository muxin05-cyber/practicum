package org.example;

public class Meat extends Decorator{
    public Meat(Stew_instruction stew_instruction) {
        super(stew_instruction);
    }
    @Override
    public int getCost(){
        return super.getCost() + 20;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " + Двойная порция оленины";
    }
}
