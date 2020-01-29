package top.huafeng.community.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenDTO {
    @Getter
    @Setter
    String client_id;

    @Getter
    @Setter
    String client_secret;

    @Getter
    @Setter
    String code;

    @Getter
    @Setter
    String redirect_uri;

    @Getter
    @Setter
    String state;

}
