package authentication.controller;

import authentication.service.RegisterService;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {

    private final RegisterService registerService;

    @Autowired
    public AuthenticationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam(required = false) String msg) {
        System.out.println(msg);
        if (msg != null && msg.equals("error")) {
            model.addAttribute("errorMsg", true);
        }

        return "authentication/login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(String id, String userName, String password) {
        if (registerService.register(id, userName, password, User.Role.student)) {
            return "ok";
        } else {
            return "failed";
        }

    }

}
