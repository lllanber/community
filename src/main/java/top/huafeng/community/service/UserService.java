package top.huafeng.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.huafeng.community.mapper.UserMapper;
import top.huafeng.community.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void  createOrUpdateUser(User user){
        User temp = userMapper.findByAccountId(user.getAccountId());
        if(temp == null){
            userMapper.insert(user);
        }else {
            userMapper.updateInfo(user);
        }

    }
}
