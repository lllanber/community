package top.huafeng.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.huafeng.community.dto.QuestionDTO;
import top.huafeng.community.service.QuestionService;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String Index(Model model){
        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questionList", questionList);
        return "index";
    }
}
