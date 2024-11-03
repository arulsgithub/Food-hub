package com.arulJD.controller;

import com.arulJD.config.JWTProvider;
import com.arulJD.entity.User;
import com.arulJD.repository.UserRepository;
import com.arulJD.request.LoginRequest;
import com.arulJD.response.AuthResponse;
import com.arulJD.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception{

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getName();

        User isExistEmail = userRepository.findByEmail(email);
        if(isExistEmail!=null){

            throw new Exception("User already Exist! Use different email");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setName(fullName);

        userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMsg("User created successfully");

        return authResponse;
    }

    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest){

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMsg("Successfully signed in");

        return authResponse;
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("User not found with email: "+username);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password!!");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
