package app.security.auth;

import lombok.*;
import app.security.user.Role;

@Setter
@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
}
