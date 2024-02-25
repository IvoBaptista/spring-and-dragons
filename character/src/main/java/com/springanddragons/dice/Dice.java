package com.springanddragons.dice;

import lombok.AllArgsConstructor;

import java.util.Random;

@AllArgsConstructor
public class Dice {
    private final int faces;

    private final Random randomGenerator = new Random();

    public int roll() {
       return randomGenerator.nextInt(faces) + 1;
    }
}
