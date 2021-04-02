package com.admin.project.admin.mapper;

import java.util.List;
import com.admin.project.admin.domain.TPost;

/**
 * 通知公告Mapper接口
 * 
 * @author admin
 * @date 2021-03-06
 */
public interface TPostMapper 
{
    /**
     * 查询通知公告
     * 
     * @param id 通知公告ID
     * @return 通知公告
     */
    public TPost selectTPostById(Long id);

    /**
     * 查询通知公告列表
     * 
     * @param tPost 通知公告
     * @return 通知公告集合
     */
    public List<TPost> selectTPostList(TPost tPost);

    /**
     * 新增通知公告
     * 
     * @param tPost 通知公告
     * @return 结果
     */
    public int insertTPost(TPost tPost);

    /**
     * 修改通知公告
     * 
     * @param tPost 通知公告
     * @return 结果
     */
    public int updateTPost(TPost tPost);

    /**
     * 删除通知公告
     * 
     * @param id 通知公告ID
     * @return 结果
     */
    public int deleteTPostById(Long id);

    /**
     * 批量删除通知公告
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTPostByIds(Long[] ids);
}
