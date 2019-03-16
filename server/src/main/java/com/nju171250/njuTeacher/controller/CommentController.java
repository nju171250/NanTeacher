package com.nju171250.njuTeacher.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nju171250.njuTeacher.mapper.CommentMapper;
import com.nju171250.njuTeacher.mapper.RecordDoFavouriteMapper;
import com.nju171250.njuTeacher.model.Comment;
import com.nju171250.njuTeacher.model.CommentExample;
import com.nju171250.njuTeacher.model.RecordDoFavourite;
import com.nju171250.njuTeacher.model.RecordDoFavouriteExample;
import com.nju171250.njuTeacher.service.TeacherService;
import com.nju171250.njuTeacher.utils.UUIDUtils;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UUIDUtils uuidUtils;
    @Autowired
    RecordDoFavouriteMapper recordDoFavouriteMapper;
    @Autowired
    TeacherService teacherService;

    @PostMapping(value = "/getUserDoFavouriteSituation")
    public JSONArray getUserDoFavouriteSituation(@RequestBody JSONObject object){
        String userId = object.getString("openid");
        List<String> commentIds = (List<String>)object.get("commentIds");
        JSONArray jsonArray = new JSONArray();
        for(String commentId : commentIds){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("commentId", commentId);
            try {
                jsonObject.put("favouriteSituation", recordDoFavouriteMapper.selectFavouriteSituationByUserIdAndCommentId(userId, commentId));
            }catch (BindingException exception){
                jsonObject.put("favouriteSituation", null);
            }
            jsonArray.add(jsonObject);
        }
        return  jsonArray;
    }

    @GetMapping(value = "/thumbsUp")
    public void doFavourite(String openid, String commentId, int likeIt){
        boolean isFavourited;
        if(likeIt == 1)
            isFavourited = true;
        else
            isFavourited = false;

        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if(recordDoFavouriteMapper.countByUserIdAndCommentId(openid, commentId) == 0) {
            RecordDoFavourite recordDoFavourite = new RecordDoFavourite();
            recordDoFavourite.setUserId(openid);
            recordDoFavourite.setCommentId(commentId);
            recordDoFavourite.setIsfavourited(isFavourited);
            recordDoFavouriteMapper.insert(recordDoFavourite);

            // 进行加减赞数
            if(isFavourited)
                comment.setLikeNum(comment.getLikeNum() + 1);
            else
                comment.setLikeNum(comment.getLikeNum() - 1);
            commentMapper.updateByPrimaryKey(comment);
        }
        else {
            System.out.println("该人已点过赞");
            // 判断是否需要调整点赞情况
            if(recordDoFavouriteMapper.selectFavouriteSituationByUserIdAndCommentId(openid, commentId) != isFavourited) {
                // 需要调整,判断如何调整并更新
                RecordDoFavouriteExample example = new RecordDoFavouriteExample();
                RecordDoFavouriteExample.Criteria criteria = example.createCriteria();
                criteria.andCommentIdEqualTo(commentId);
                criteria.andUserIdEqualTo(openid);
                RecordDoFavourite recordDoFavourite = recordDoFavouriteMapper.selectByExample(example).get(0);
                recordDoFavourite.setIsfavourited(isFavourited);
                recordDoFavouriteMapper.updateByExample(recordDoFavourite, example);
                if(isFavourited)
                    // 原来为减，总数需加2
                    comment.setLikeNum(comment.getLikeNum() + 2);
                else
                    // 原来为加，总数需减2
                    comment.setLikeNum(comment.getLikeNum() - 2);
                commentMapper.updateByPrimaryKey(comment);
            }
        }
    }

    @PostMapping(value = "/makeComment")
    public void makeComment(@RequestBody JSONObject jsonObject){
        Comment comment = new Comment();
        comment.setCommentId(uuidUtils.getUUID32());
        comment.setCommentContent(jsonObject.getString("content"));
        comment.setCommentStars(jsonObject.getInteger("starNum"));
        comment.setUserId(jsonObject.getString("openid"));
        comment.setCommentTime(new Date());
        comment.setCourseId(jsonObject.getString("courseId"));
        comment.setLikeNum(0);
        commentMapper.insert(comment);

        // 老师分数实时更新
        teacherService.computeTeacherScore(jsonObject.getString("courseId"));
    }

    @GetMapping(value = "/getCommentInfo")
    public List<Comment> getCommentInfo(String teacherId, String courseId){
        // 若courseId为空，则返回老师所有的评价，否则，返回courseId相关的评价
        if(courseId == null || courseId.equals("")){
            return commentMapper.getCommentInfoByTeacherId(teacherId);
        }
        else {
            return commentMapper.getCommentInfoByCourseId(courseId);
        }
    }
}
