package org.example;

public class Bread extends Decorator{
    public Bread(Stew_instruction stew_instruction) {
        super(stew_instruction);
    }
    @Override
    public int getCost(){
        return super.getCost() + 10;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " + Нордская лепёшка";
    }
}
