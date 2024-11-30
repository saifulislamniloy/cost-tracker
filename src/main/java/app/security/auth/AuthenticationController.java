package app.security.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "auth/register";
    }


    @PostMapping("/authenticate")
    public String authenticate(
            String email, String password, HttpServletResponse response,
            HttpServletRequest request
    ) {
        AuthenticationRequest credential = new AuthenticationRequest();
        credential.setEmail(email);
        credential.setPassword(password);

        AuthenticationResponse responseObject = service.authenticate(credential);
        // Set tokens as cookies
        Cookie accessTokenCookie = new Cookie("access_token", responseObject.getAccessToken());
        accessTokenCookie.setHttpOnly(true); // Prevent access via JavaScript
        accessTokenCookie.setPath("/"); // Make it available site-wide
        accessTokenCookie.setMaxAge(3600); // Expire in 1 hour

        Cookie refreshTokenCookie = new Cookie("refresh_token", responseObject.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 3600); // Expire in 7 days

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
        return "redirect:/"; // Redirect to home after login
    }
}
