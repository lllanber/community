package top.huafeng.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//上一页 按钮
    private boolean showFirstPage;//首页 按钮
    private boolean showNext;//下一页 按钮
    private boolean showEndPage;//尾页 按钮
    private Integer totalPage;
    private Integer page;//当前页面

    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.page = page;
        if ((totalCount % size == 0)) {
            this.totalPage = totalCount / size;
        } else {
            this.totalPage = totalCount / size + 1;
        }

        pages.add(page);//2
        for (int i = 1; i < 4; i++) {
            if(page - i > 0){//page - i=1
                pages.add(0, page - i);
            }
            if (page + i <= totalPage){
                pages.add(page+i);
            }
        }

        //是否展示 上一页按钮
        if(page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示 下一页按钮
        if(page == totalPage){
            showNext= false;
        }else {
            showNext = true;
        }

        //是否展示 首页按钮
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //是否展示 尾页按钮
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
