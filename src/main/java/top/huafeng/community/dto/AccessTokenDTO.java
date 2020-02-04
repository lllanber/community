package top.huafeng.community.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessTokenDTO {
    String client_id;
    String client_secret;
    String code;
    String redirect_uri;
    String state;

}
