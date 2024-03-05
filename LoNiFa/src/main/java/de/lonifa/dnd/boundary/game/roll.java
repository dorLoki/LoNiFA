package de.lonifa.dnd.boundary.game;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class roll {

    @GetMapping("/roll")
    public String rollDice() {
        return "roll";
    }
}
