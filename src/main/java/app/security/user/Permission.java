package app.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CONSUMER_READ("user:read"),
    CONSUMER_UPDATE("user:update"),
    CONSUMER_CREATE("user:create"),
    CONSUMER_DELETE("user:delete")

    ;

    @Getter
    private final String permission;
}
