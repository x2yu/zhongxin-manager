package com.zhongxin.manager.mapper;

import com.zhongxin.manager.entity.TblDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongxin.manager.entity.TblReview;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 应用文档表  Mapper 接口
 * </p>
 *
 * @author zhongxin
 * @since 2020-05-27
 */
public interface TblDocMapper{

    //查询所有文档
    @Select("select * from tbl_doc")
    public List<TblDoc> selectAll();

    //根据文档ID查询相应文档内容
    @Select("select * from tbl_doc where doc_id = #{doc_id}")
    public TblDoc selectBy_doc_id(int doc_id);

    //根据应用ID查询相应文档内容
    @Select("select * from tbl_doc where app_id = #{app_id}")
    public List<TblDoc> selectBy_app_id(int app_id);

    //根据文档ID更新对应文档内容
    @Update("update tbl_doc set content = #{content} where doc_id = #{docId}")
    public boolean update(TblDoc tblDoc);

    //根据文档ID删除相应文档
    @Delete("delete from tbl_doc where doc_id = #{doc_id}")
    public boolean deleteBy_doc_id(int doc_id);

    //增加文档
    @Insert("insert into tbl_doc(app_id,content) values(#{appId},#{content})")
    public boolean add(TblDoc tblDoc);

}
