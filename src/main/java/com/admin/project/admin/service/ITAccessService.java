package com.admin.project.admin.service;

import java.util.List;
import com.admin.project.admin.domain.TAccess;

/**
 * 图书借阅Service接口
 * 
 * @author admin
 * @date 2021-03-06
 */
public interface ITAccessService 
{
    /**
     * 查询图书借阅
     * 
     * @param id 图书借阅ID
     * @return 图书借阅
     */
    public TAccess selectTAccessById(Long id);

    /**
     * 查询图书借阅列表
     * 
     * @param tAccess 图书借阅
     * @return 图书借阅集合
     */
    public List<TAccess> selectTAccessList(TAccess tAccess);

    /**
     * 新增图书借阅
     * 
     * @param tAccess 图书借阅
     * @return 结果
     */
    public int insertTAccess(TAccess tAccess);

    /**
     * 修改图书借阅
     * 
     * @param tAccess 图书借阅
     * @return 结果
     */
    public int updateTAccess(TAccess tAccess);

    /**
     * 批量删除图书借阅
     * 
     * @param ids 需要删除的图书借阅ID
     * @return 结果
     */
    public int deleteTAccessByIds(Long[] ids);

    /**
     * 删除图书借阅信息
     * 
     * @param id 图书借阅ID
     * @return 结果
     */
    public int deleteTAccessById(Long id);
}
