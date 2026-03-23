package org.example;


public abstract class Decorator implements Stew_instruction {
    protected Stew_instruction decoratedStew_instruction;

    public Decorator(Stew_instruction stew_instruction) {
        this.decoratedStew_instruction = stew_instruction;
    }

    @Override
    public String getDescription() {
        return decoratedStew_instruction.getDescription();
    }

    @Override
    public int getCost() {
        return decoratedStew_instruction.getCost();
    }
}