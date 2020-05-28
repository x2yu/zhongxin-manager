package com.zhongxin.manager.mapper;

import com.zhongxin.manager.entity.TblReview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 * 评价表  Mapper 接口
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
import java.util.List;

//在app_id和user_id上建立索引

public interface TblReviewMapper {
    //查询所有
    @Select("select * from tbl_review")
    public List<TblReview> selectAll();

    //查询某一app的全部评论
    @Select("select * from tbl_review where app_id=#{app_id}")
    public List<TblReview> selectByapp_id(@Param("app_id") int app_id);

    //查询某一用户的全部评论
    @Select("select * from tbl_review where user_id=#{user_id}")
    public List<TblReview> selectByuser_id(@Param("user_id") int user_id);

    //插入评论
    @Insert("insert into tbl_review(app_id,content,user_id) values(#{appId},#{content},#{userId})")
    public void insert(TblReview riview);

    //删除某条评论
    @Delete("delete from tbl_review where review_id = #{review_id}")
    public void delete(@Param("review_id") int review_id);

    //删除某用户的全部评价
    @Delete("delete from tbl_review where user_id = #{user_id};")
    public void deleteByuser_id(@Param("user_id") int user_id);

    //更新评论
    @Update("update tbl_review set content = #{content} where review_id = #{reviewId}")
    public boolean update(TblReview review);
}



