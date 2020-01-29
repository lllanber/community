package top.huafeng.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.huafeng.community.dto.AccessTokenDTO;
import top.huafeng.community.dto.GithubUser;
import top.huafeng.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @GetMapping("callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            //登录成功，写cookie和session
            request.getSession().setAttribute("githubUser",githubUser);
            return "redirect:/";
        } else {
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}