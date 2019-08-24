package QuizTime.QuizTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import QuizTime.QuizTime.beans.UserBean;
import QuizTime.QuizTime.model.User;
import QuizTime.QuizTime.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Boolean register(@RequestBody UserBean bean) {
		
		User user = userService.findOneByUsername(bean.getUsername());
		if (user == null) {
			user = new User(bean.getUsername(), bean.getPassword(), bean.getFirstName(), bean.getLastName(), bean.getEmail());
			userService.save(user);
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Boolean login(@RequestBody UserBean bean) {
		
		User user = userService.findOneByUsername(bean.getUsername());
		if (user != null) {
			if (user.getPassword().equals(bean.getPassword())) {
				return true;
			}
			return false;
		}
		return false;
	}

}
