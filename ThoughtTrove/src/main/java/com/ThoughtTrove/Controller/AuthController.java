package com.ThoughtTrove.Controller;

import com.ThoughtTrove.Dto.Request.AuthRequest;
import com.ThoughtTrove.Dto.Response.JwtAuthReponse;
import com.ThoughtTrove.securityPak.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/auth")
public class AuthController {

    @Autowired
 private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;
@Autowired
private AuthenticationManager authenticationManager;

@PostMapping("/login")
    public ResponseEntity loginRequest(@RequestBody AuthRequest authRequest){
    this.authonticiate(authRequest.getUsername() , authRequest.getPassword());
    UserDetails userDetails =userDetailsService.loadUserByUsername(authRequest.getUsername());

    String tolkan = jwtTokenHelper.generateToken(userDetails);
    JwtAuthReponse jwtAuthReponse = new JwtAuthReponse();
    jwtAuthReponse.setToken(tolkan);
    //jwtAuthRepons
    return new ResponseEntity<>(jwtAuthReponse , HttpStatus.OK);
}

    private void authonticiate(String username, String password) {
        UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(username , password);
        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }

    }

}
