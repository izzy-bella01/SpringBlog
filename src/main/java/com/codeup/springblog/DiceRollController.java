package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@Controller
public class DiceRollController {

    @GetMapping("/roll-dice")
    public String diceRoll() {
        return "diceRoll";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRollGuess(@PathVariable int n, Model model) {
        model.addAttribute("guessedNum", n);

        int number = (int) (Math.floor(Math.random() * ((6 - 1) + 1)) + 1);
        model.addAttribute("randomNum", number);

        return "diceRollGuess";
    }
}
