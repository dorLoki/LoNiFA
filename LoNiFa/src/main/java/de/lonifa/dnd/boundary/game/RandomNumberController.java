package de.lonifa.dnd.boundary.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RandomNumberController {
    @GetMapping("/random-6")
    public int getRandom6() {
        return (int) (Math.random() * 6) + 1;
    }
    @GetMapping("/random-4")
    public int getRandom4() {
        return (int) (Math.random() * 4) + 1;
    }
    @GetMapping("/random-20")
    public int getRandom20() {
        if(rand == 21){
            rand = 1;
        }
        return rand++;
    }
    private static int rand = 1;
}
