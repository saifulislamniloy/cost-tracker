package app.common;

import app.security.user.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

public class Utils {
    public static class CurrentUser {
        private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

        public static void set(UserDetails user) {
            currentUser.set((User) user);
        }

        public static Long getId() {
            return currentUser.get() == null ? -1L : currentUser.get().getId();
        }

    }
    public static class Security {
        public static final String ACCESS_TOKEN_KEY = "access_token";

        public static final String REFRESH_TOKEN_KEY = "refresh_token";

        public static String getRefreshToken(Cookie[] cookies) {
            return getCookieValue(cookies, REFRESH_TOKEN_KEY);
        }

        public static String getAccessToken(HttpServletRequest request) {
            String accessToken = getCookieValue(request.getCookies(), ACCESS_TOKEN_KEY);

            return "Bearer " + accessToken;
        }

        public static String getCookieValue(Cookie[] cookies, String key) {
            if (cookies == null || cookies.length == 0) return null;

            return Arrays.stream(cookies)
                    .filter(cookie -> key.equals(cookie.getName()))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
    }
}
