package lin.mvc.controller;

import lin.mvc.entity.Admin;
import lin.mvc.repository.AdminRepository;
import lin.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", "/login"})
    public String loginView(Map<String, Object> map) {
        return "login";
    }

    @RequestMapping(value = {"addUser"})
    public String addUserView(Map<String, Object> map) {
        return "addUser";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, @RequestParam("textName") String name, @RequestParam("textPwd") String password) {

        Admin admin = adminRepository.findOneByNameAndPassword(name, password);
        System.out.println(admin);
        if (admin==null)
            return "login";

        HttpSession session=request.getSession();
        session.setAttribute("name", admin.getName());
        model.addAttribute("userList", userRepository.findAll());
        return "management";
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session=request.getSession();
        session.setAttribute("name", null);
        return "login";
    }
}
