package de.lonifa.user.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.lonifa.user.domain.User;
import de.lonifa.user.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            if (error.isEmpty()) {
                model.addAttribute("loginError", "Benutzername oder Passwort ungültig.");
            } else {
                model.addAttribute("loginError", error);
            }
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("registerError", error);
        return "register";
    }

    @PostMapping("/register")
    public String register(@NonNull User user, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(user);
        } catch (IllegalArgumentException e) {
            // Verwenden Sie RedirectAttributes, um eine Fehlermeldung zu übergeben
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("user", user); // Eingegebene Daten zurück an die Ansicht senden
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
