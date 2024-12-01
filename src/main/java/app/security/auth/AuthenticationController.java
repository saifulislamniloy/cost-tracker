package app.security.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @Value("${application.security.jwt.expiration}")
    private Integer accessTokenTimeOut;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private Integer refreshTokenTimeOut;

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
            String email, String password, HttpServletResponse response
    ) {
        AuthenticationRequest credential = new AuthenticationRequest();
        credential.setEmail(email);
        credential.setPassword(password);

        AuthenticationResponse responseObject = service.authenticate(credential);

        Cookie accessTokenCookie = new Cookie("access_token", responseObject.getAccessToken());
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(accessTokenTimeOut);

        Cookie refreshTokenCookie = new Cookie("refresh_token", responseObject.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(refreshTokenTimeOut);

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
        return "redirect:/";
    }
}
