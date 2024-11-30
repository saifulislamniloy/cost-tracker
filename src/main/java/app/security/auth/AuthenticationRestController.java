package app.security.auth;

import app.security.user.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth_rest")
@RequiredArgsConstructor
public class AuthenticationRestController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public AuthenticationResponse register(
            String firstname, String lastname, String email, String password
    ) {
        RegisterRequest user = new RegisterRequest();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.USER);

        return service.register(user);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
