package com.goit.feature.mvc.security;

import com.goit.feature.exceptions.NotValidPasswordException;
import com.goit.feature.exceptions.NotValidUserNameException;
import com.goit.feature.exceptions.UserAlreadyExistException;
import com.goit.feature.mvc.user.User;
import com.goit.feature.mvc.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class SecurityController {
    private final UserService userService;

    @GetMapping()
    RedirectView getNoteList() {
        return new RedirectView("note/list");
    }

    @GetMapping("/login")
    ModelAndView getLogin(ModelMap model) {
        model.addAttribute("message");

        return new ModelAndView("auth/login");
    }

    @GetMapping("/register")
    ModelAndView getRegister() {
        return new ModelAndView("auth/register");
    }

    @PostMapping("/register")
    public ModelAndView registerUserAccount(User user, RedirectAttributes attributes) {
        ModelAndView result = new ModelAndView("auth/register");

        try {
            User registered = userService.save(user);
        } catch (UserAlreadyExistException ex) {
            result.addObject("message",
                    "An account for that username already exists.");
            return result;
        } catch (NotValidUserNameException ex) {
            result.addObject("message",
                    "Username must be alphanumeric and must be between 5 and 50 in length!");
            return result;
        } catch (NotValidPasswordException ex) {
            result.addObject("message",
                    "Password length must be at least 8 and not more than 100 symbols!");
            return result;
        }

        attributes.addFlashAttribute("message", "Successfully registered, please Log In!");
        return new ModelAndView("redirect:/login");
    }
}
