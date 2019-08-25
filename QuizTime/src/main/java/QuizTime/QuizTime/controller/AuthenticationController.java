package QuizTime.QuizTime.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import QuizTime.QuizTime.beans.UserBean;
import QuizTime.QuizTime.model.Authority;
import QuizTime.QuizTime.model.User;
import QuizTime.QuizTime.model.UserTokenState;
import QuizTime.QuizTime.security.TokenUtils;
import QuizTime.QuizTime.service.AuthorityService;
import QuizTime.QuizTime.service.UserService;
import QuizTime.QuizTime.service.impl.CustomUserDetailsService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService autService;

	
	@CrossOrigin()
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserBean authenticationRequest, HttpServletResponse response) throws AuthenticationException, IOException {
		
		System.out.println("Logging: " + authenticationRequest.getUsername() + "    " + authenticationRequest.getPassword());
		
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
		
		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Kreiraj token
		
		User user = (User) authentication.getPrincipal();
		System.out.println(user.getFirstName());

		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn();
		response.setHeader("Authorization", "Bearer jwt_token");
		// Vrati token kao odgovor na uspesno autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}
	
	

	@CrossOrigin()
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public HttpStatus register(@RequestBody UserBean bean, HttpServletResponse response) throws AuthenticationException, IOException {
		
		System.out.println("Registration: " + bean.getUsername() + "    " + bean.getPassword());
		User u = userService.findOneByUsername(bean.getUsername());
		if (u != null) {
			return HttpStatus.CONFLICT;
		}
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		User user = new User(bean.getUsername(), bc.encode(bean.getPassword()), bean.getFirstName(),
				bean.getLastName(), bean.getEmail());
		Authority aut = autService.findOne(1l);
		ArrayList<Authority> authorities = new ArrayList<>();
		authorities.add(aut);
		user.setAuthorities(authorities);
		userService.save(user);
		return HttpStatus.OK;
	}
	

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);

		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	

}
