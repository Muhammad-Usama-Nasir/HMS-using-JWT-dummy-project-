package com.example.AliBaba.ABbackend.Controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AliBaba.ABbackend.ORM.JwtRequest;
import com.example.AliBaba.ABbackend.ORM.JwtResponse;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.UserRepo;
import com.example.AliBaba.ABbackend.Security.JwtHelper;
import com.example.AliBaba.ABbackend.Service.HomeService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtHelper helper;
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	
	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

	
	
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
	
    
    
    
    @PostMapping("/signup")
	public @ResponseBody ResponseEntity<ResponseStatus> createAdmin(@RequestBody ORMSaveUser saveUser){
		
		ResponseStatus resp = homeService.createAdmin(saveUser);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	
    @PostMapping("/verify")
	public @ResponseBody ResponseEntity<ResponseStatus> verifyAccount(@RequestParam("code") String code){
		
		ResponseStatus resp = homeService.verifyAccount(code);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
    
    
    @PostMapping("confirm-verification")
    public String confirmVerification(@RequestBody JwtRequest verifyCredentials) {
        String email = verifyCredentials.getEmail();
        String password = verifyCredentials.getPassword();
        
        Optional<ORMSaveUser> employeeEmailId = userRepo.findByEmail(email);

        if (!employeeEmailId.isPresent()) {
            return "Invalid email!";
        }
        
        ORMSaveUser employee = employeeEmailId.get();
        
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            return "Invalid email or password!";
        }

        if (!employee.getIsVerified()) {
            return "Please verify your email before logging in.";
        }

        return "Account Verified successfully...!!!";
    }

	
	
    @PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
        								  .jwtToken(token)
        								  .username(userDetails.getUsername()).build();
        
        
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
