package top.huafeng.community.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {

  private Integer id;
  private String title;
  private String description;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer creator;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
  private String tag;
}
