package com.springanddragons.dice;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roll")
@RequiredArgsConstructor
public class DiceController {
    private final Dice d6;
    private final Dice d8;

    @GetMapping("/d6")
    public int rollD6() {
        return d6.roll();
    }

    @GetMapping("/d8")
    public int rollD8() {
        return d8.roll();
    }
}
