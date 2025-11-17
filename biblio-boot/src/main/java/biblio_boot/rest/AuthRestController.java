package biblio_boot.rest;

import biblio_boot.config.JwtUtils;
import biblio_boot.dto.request.AuthUserRequest;
import biblio_boot.dto.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthUserRequest request){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        this.authenticationManager.authenticate(auth);

        return new AuthResponse(JwtUtils.generate(auth));
    }
}
