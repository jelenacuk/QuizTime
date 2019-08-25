package QuizTime.QuizTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import QuizTime.QuizTime.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	

}
