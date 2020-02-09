package top.huafeng.community.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import top.huafeng.community.model.User;

@Mapper
@Component
public interface UserMapper {
    @Insert("INSERT INTO user (name, account_id, token, gmt_create, gmt_modified, avatar_url) " +
            "VALUES (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from USER where  account_id= #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Select("select * from USER where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Update("update USER set NAME = #{name}, TOKEN = #{token}, GMT_MODIFIED = #{gmtModified}, AVATAR_URL = #{avatarUrl}")
    void updateInfo(User user);
}
