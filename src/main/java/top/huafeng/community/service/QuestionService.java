package top.huafeng.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.huafeng.community.dto.QuestionDTO;
import top.huafeng.community.mapper.QuestionMapper;
import top.huafeng.community.mapper.UserMapper;
import top.huafeng.community.model.Question;
import top.huafeng.community.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list(){
        List<Question> questions = questionMapper.list();
//        System.out.println("QuestionService25: questions = " + questions);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
//        System.out.println("QuestionService34: questionDTOList = " + questionDTOList);
        return questionDTOList;
    }
}
