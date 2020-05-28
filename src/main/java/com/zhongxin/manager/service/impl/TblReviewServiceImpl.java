package com.zhongxin.manager.service.impl;

import com.zhongxin.manager.entity.TblReview;
import com.zhongxin.manager.mapper.TblReviewMapper;
import com.zhongxin.manager.service.ITblReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * <p>
 * 评价表  服务实现类
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */


@Service
public class TblReviewServiceImpl{
    @Autowired
    private TblReviewMapper mapper;

    //插入一条评论
    public boolean insert(TblReview review){
        try {
            mapper.insert(review);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //删除某条评论
    public boolean delete(int review_id){
        try {
            mapper.delete(review_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //删除某用户的全部评论
    public boolean deleteByuser_id(int user_id){
        try {
            mapper.deleteByuser_id(user_id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //更新某条评论
    public boolean update(TblReview review){
        try {
            mapper.update(review);
            return true;
        }catch (Exception e){
            return false;
        }
        //return mapper.update(review);
    }

    //查询全部评论
    public List<TblReview> selectall(){
        return mapper.selectAll();
    }

    //查询某个app的全部评论
    public List<TblReview> selectByapp_id(int app_id){
        return mapper.selectByapp_id(app_id);
    }

    //查询某个用户的全部评论
    public List<TblReview> selectByuser_id(int user_id){
        return mapper.selectByuser_id(user_id);
    }

    //校验评论内容是否有效
    public boolean isvalid(TblReview review){
        String content = review.getContent();
        if (content==null || content.trim().equals("")){
            return false;
        }
        return true;
    }
}
