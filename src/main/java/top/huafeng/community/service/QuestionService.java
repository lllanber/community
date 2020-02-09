package top.huafeng.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.huafeng.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size){

        //计算分页偏移量
        Integer offset = (page-1) * size;

        //查出问题列表
        List<Question> questions = questionMapper.list(offset, size);
        //问题信息+创建者信息
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        //问题信息+创建者信息+分页信息
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }
}
