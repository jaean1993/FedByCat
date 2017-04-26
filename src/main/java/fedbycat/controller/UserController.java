package fedbycat.controller;

import fedbycat.model.UserModel;
import fedbycat.service.UserService;
import fedbycat.web.FedByCatApiResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by anjin on 4/23/17.
 */

@Controller
public class UserController {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/sighup", method = RequestMethod.POST)

    public void sighUp(@RequestParam String username, @RequestParam String password, @RequestParam int gender, @RequestParam String email, HttpServletRequest request, HttpServletResponse response) {

        String res = validate(username, password, gender, email);
        UserModel userModel = new UserModel(username, password, gender, email, System.currentTimeMillis(), "", System.currentTimeMillis(), true);
        try {
            if (!res.equals("success") || !userService.putUser(userModel)) {
                FedByCatApiResult.writeResponseFail(request, response, res);
            } else {
                FedByCatApiResult.writeResponseOk(request, response, "signup success");
            }
        } catch (IOException e) {
            FedByCatApiResult.writeResponseException(request, response, e);
        }

    }

    private String validate(String userName, String password, int gender, String email) {
        if (!validateEmail(email)) {
            return "invalid email address";
        }
        if (!validateUserName(userName)) {
            return "invalid user name";
        }
        if (!validGender(gender)) {
            return "invalid gender";
        }
        if (!(validPassword(password))) {
            return "invalid password";
        }
        return "success";
    }

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private boolean validateUserName(String userName) {
        return userName.length() > 4;
    }

    private boolean validPassword(String password) {
        return password.length() > 5;
    }
    private boolean validGender(int gender) {
        return gender >= 0 && gender <= 1;
    }
}
