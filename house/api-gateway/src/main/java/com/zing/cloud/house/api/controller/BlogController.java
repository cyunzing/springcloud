package com.zing.cloud.house.api.controller;

import com.zing.cloud.house.api.common.CommonConstants;
import com.zing.cloud.house.api.common.PageData;
import com.zing.cloud.house.api.common.PageParams;
import com.zing.cloud.house.api.model.Blog;
import com.zing.cloud.house.api.model.Comment;
import com.zing.cloud.house.api.model.House;
import com.zing.cloud.house.api.service.CommentService;
import com.zing.cloud.house.api.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HouseService houseService;


    @RequestMapping(value = "blog/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(Integer pageSize, Integer pageNum, Blog query, ModelMap modelMap) {
        PageData<Blog> ps = commentService.queryBlogs(query, PageParams.build(pageSize, pageNum));
        List<House> houses = houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("ps", ps);
        return "/blog/listing";
    }

    @RequestMapping(value = "blog/detail", method = {RequestMethod.POST, RequestMethod.GET})
    public String blogDetail(int id, ModelMap modelMap) {
        Blog blog = commentService.queryOneBlog(id);
        List<Comment> comments = commentService.getBlogComments(id);
        List<House> houses = houseService.getHotHouse(CommonConstants.RECOM_SIZE);
        modelMap.put("recomHouses", houses);
        modelMap.put("blog", blog);
        modelMap.put("commentList", comments);
        return "/blog/detail";
    }
}
