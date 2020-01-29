package top.huafeng.community.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GithubUser {
    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    long id;

    @Getter
    @Setter
    String bio;

}
