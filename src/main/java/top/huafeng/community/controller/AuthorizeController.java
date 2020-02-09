package top.huafeng.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.huafeng.community.dto.AccessTokenDTO;
import top.huafeng.community.dto.GithubUser;
import top.huafeng.community.mapper.UserMapper;
import top.huafeng.community.model.User;
import top.huafeng.community.provider.GithubProvider;
import top.huafeng.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response,
                           HttpServletRequest request) throws IOException {
        AccessTokenDTO accessTokenDTO = AccessTokenDTO.builder()
                .client_id(clientId)
                .client_secret(clientSecret)
                .code(code)
                .redirect_uri(redirectUri)
                .state(state)
                .build();
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            //GitHub登录成功，查询到的用户信息存到githubUser中， 把有用的信息存到user中
            String token = UUID.randomUUID().toString();
            User user = User.builder()
                    .token(token)
                    .name(githubUser.getName())
                    .accountId(String.valueOf(githubUser.getId()))
                    .gmtCreate(System.currentTimeMillis())
                    .gmtModified(System.currentTimeMillis())
                    .avatarUrl(githubUser.getAvatar_url())
                    .build();
            System.out.println("AutorizeController 57成功在GitHub拿到user信息: user = " + user);

            //数据库中没有该用户的信息，就插入新的用户信息
            //数据库中有该用户信息，就更新用户信息
            userService.createOrUpdateUser(user);


            //登录成功，写cookie和session
            request.getSession().setAttribute("user", user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
