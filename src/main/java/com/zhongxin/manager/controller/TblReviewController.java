package com.zhongxin.manager.controller;


import com.zhongxin.manager.entity.TblReview;
import com.zhongxin.manager.service.impl.TblReviewServiceImpl;
import com.zhongxin.manager.service.impl.TblReviewServiceImpl;
import com.zhongxin.manager.utils.Result;
import com.zhongxin.manager.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 * 评价表  前端控制器
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
@RestController
@RequestMapping("api/review")
public class TblReviewController {
    @Autowired
    private TblReviewServiceImpl reviewservice;

    //查询所有评论
    @RequestMapping("/selectAll")
    public List<TblReview> selectAll(){
        return reviewservice.selectall();
    }

    //查询某app的全部评论
    @RequestMapping("/selectByapp_id/{app_id}")
    public List<TblReview> selectByapp_id(@PathVariable("app_id") int app_id){
        return reviewservice.selectByapp_id(app_id);
    }

    //查询某用户的全部评论
    @RequestMapping("/selectByuser_id/{user_id}")
    public List<TblReview> selectByuser_id(@PathVariable("user_id") int user_id){
        return reviewservice.selectByuser_id(user_id);
    }

    //删除某条评论
    @RequestMapping("/delete/{review_id}")
    public Result delete(@PathVariable("review_id") int review_id){
        boolean bo = reviewservice.delete(review_id);
        if (bo==true){
            return ResultUtil.success();
        }else return ResultUtil.submitError();
    }

    //删除某用户的全部评论
    @RequestMapping("/deleteByuser_id/{user_id}")
    public Result deleteByuser_id(@PathVariable("user_id") int user_id){
        boolean bo = reviewservice.deleteByuser_id(user_id);
        if (bo==true){
            return ResultUtil.success();
        }else return ResultUtil.submitError();
    }

    //插入一条评论
    @RequestMapping("/insert")
    public Result insert(@RequestBody TblReview review){

        if (!reviewservice.isvalid(review)){
            return ResultUtil.submitError();
        }
        boolean bo = reviewservice.insert(review);
        if (bo==true){
            return ResultUtil.success();
        }else return ResultUtil.submitError();
    }

    //更新
    @RequestMapping("/update")
    public Result update(@RequestBody TblReview review){
        if (!reviewservice.isvalid(review)){
            return ResultUtil.submitError();
        }
        boolean bo = reviewservice.update(review);
        if (bo==true){
            return ResultUtil.success();
        }else return ResultUtil.submitError();
    }
}

