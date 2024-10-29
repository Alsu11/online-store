package simbirsoft.com.authservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserSignInResponse {
    private String token;
}
