package lin.mvc.controller;

import lin.mvc.entity.User;
import lin.mvc.repository.InsertLogRepository;
import lin.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@org.springframework.stereotype.Controller
public class AddController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/add"})
    public String addView(Map<String, Object> map) {
        return "add";
    }

    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public String insert(Model model, @RequestParam("textName") String name, @RequestParam("textGender") String gender, @RequestParam("textCompany") String company) {

        User user= new User();
        user.setName(name);
        user.setGender(gender);
        user.setCompany(company);

        userRepository.save(user);

        model.addAttribute("userList", userRepository.findAll());
        return "management";
    }

}
