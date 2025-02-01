package in.dash.blog_platform.controllers;

import in.dash.blog_platform.dtos.AuthResponse;
import in.dash.blog_platform.dtos.LoginRequest;
import in.dash.blog_platform.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService service){
        this.authenticationService=service;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        UserDetails userDetails=this.authenticationService.authenticate(request.getEmail(), request.getPassword());
  String token=this.authenticationService.generateToken(userDetails);
  AuthResponse response=new AuthResponse(token,86400);
  return  ResponseEntity.ok(response);
    }



}
