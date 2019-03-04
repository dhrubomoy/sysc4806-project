package com.sysc4806.project.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.sysc4806.project.message.request.*;
import com.sysc4806.project.message.response.*;
import com.sysc4806.project.model.*;
import com.sysc4806.project.repository.*;
import com.sysc4806.project.security.jwt.JwtProvider;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewerRepository reviewerRepository;
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private SubmitterRepository submitterRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        String name = signUpRequest.getName();
        String username = signUpRequest.getUsername();
        String password = encoder.encode(signUpRequest.getPassword());
        String strRole = signUpRequest.getRole();
        String errorMsg = "Fail! -> Cause: Could not find user role: " + strRole;
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Error: Username '" + username + "' is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        switch (strRole) {
            case "editor":
                Editor editor = new Editor(name, username, password);
                editorRepository.save(editor);
                break;
            case "reviewer":
                Reviewer reviewer = new Reviewer(name, username, password);
                reviewerRepository.save(reviewer);
                break;
            case "submitter":
                Submitter submitter = new Submitter(name, username, password);
                submitterRepository.save(submitter);
                break;
            default:
                return new ResponseEntity<>(new ResponseMessage(errorMsg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseMessage("User '"+ username +"' registered successfully!"), HttpStatus.OK);
    }
}