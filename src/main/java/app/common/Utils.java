package app.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class Utils {
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
