package org.example;

public class Berries extends Decorator{
    public Berries(Stew_instruction stew_instruction) {
        super(stew_instruction);
    }
    @Override
    public int getCost(){
        return super.getCost() + 5;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " + Снежные ягоды";
    }
}
