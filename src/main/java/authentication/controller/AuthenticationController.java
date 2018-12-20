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
import stater.ResponseMsg;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseMsg register(String id, String userName, String password) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.data = "";
        if (registerService.register(id, userName, password, User.Role.student)) {
            responseMsg.state = 200;
            responseMsg.description = "ok";
        } else {
            responseMsg.state = 500;
            responseMsg.description = "invalid user info";
        }

        return responseMsg;
    }


    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMsg loginFailed(){
        ResponseMsg responseMsg = new ResponseMsg();

        responseMsg.state = 404;
        responseMsg.description = "invalid username or password";
        responseMsg.data = "";

        return responseMsg;
    }


    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMsg loginSuccess(HttpServletRequest request){
        ResponseMsg responseMsg = new ResponseMsg();

        responseMsg.state = 200;
        responseMsg.description = "login success";
        responseMsg.data = request.getSession().getId();

        return responseMsg;
    }
}
