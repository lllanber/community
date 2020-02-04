package top.huafeng.community.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

  private Integer id;
  private String accountId;
  private String name;
  private String token;
  private Long gmtCreate;
  private Long gmtModified;
  private String avatarUrl;
}
