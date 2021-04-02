package com.admin.project.admin.service.impl;

import java.util.List;
import com.admin.common.utils.DateUtils;
import com.admin.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.project.admin.mapper.TPostMapper;
import com.admin.project.admin.domain.TPost;
import com.admin.project.admin.service.ITPostService;

/**
 * 通知公告Service业务层处理
 * 
 * @author admin
 * @date 2021-03-06
 */
@Service
public class TPostServiceImpl implements ITPostService 
{
    @Autowired
    private TPostMapper tPostMapper;

    /**
     * 查询通知公告
     * 
     * @param id 通知公告ID
     * @return 通知公告
     */
    @Override
    public TPost selectTPostById(Long id)
    {
        return tPostMapper.selectTPostById(id);
    }

    /**
     * 查询通知公告列表
     * 
     * @param tPost 通知公告
     * @return 通知公告
     */
    @Override
    public List<TPost> selectTPostList(TPost tPost)
    {
        return tPostMapper.selectTPostList(tPost);
    }

    /**
     * 新增通知公告
     * 
     * @param tPost 通知公告
     * @return 结果
     */
    @Override
    public int insertTPost(TPost tPost)
    {
    	tPost.setCreateBy(SecurityUtils.getUsername());
        tPost.setCreateTime(DateUtils.getNowDate());
        return tPostMapper.insertTPost(tPost);
    }

    /**
     * 修改通知公告
     * 
     * @param tPost 通知公告
     * @return 结果
     */
    @Override
    public int updateTPost(TPost tPost)
    {
        return tPostMapper.updateTPost(tPost);
    }

    /**
     * 批量删除通知公告
     * 
     * @param ids 需要删除的通知公告ID
     * @return 结果
     */
    @Override
    public int deleteTPostByIds(Long[] ids)
    {
        return tPostMapper.deleteTPostByIds(ids);
    }

    /**
     * 删除通知公告信息
     * 
     * @param id 通知公告ID
     * @return 结果
     */
    @Override
    public int deleteTPostById(Long id)
    {
        return tPostMapper.deleteTPostById(id);
    }
}
