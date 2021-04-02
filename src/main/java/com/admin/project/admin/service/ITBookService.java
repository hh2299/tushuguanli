package com.admin.project.admin.service;

import java.util.List;
import com.admin.project.admin.domain.TBook;

/**
 * 图书信息Service接口
 * 
 * @author admin
 * @date 2021-03-06
 */
public interface ITBookService 
{
    /**
     * 查询图书信息
     * 
     * @param id 图书信息ID
     * @return 图书信息
     */
    public TBook selectTBookById(Long id);

    /**
     * 查询图书信息列表
     * 
     * @param tBook 图书信息
     * @return 图书信息集合
     */
    public List<TBook> selectTBookList(TBook tBook);

    /**
     * 新增图书信息
     * 
     * @param tBook 图书信息
     * @return 结果
     */
    public int insertTBook(TBook tBook);

    /**
     * 修改图书信息
     * 
     * @param tBook 图书信息
     * @return 结果
     */
    public int updateTBook(TBook tBook);

    /**
     * 批量删除图书信息
     * 
     * @param ids 需要删除的图书信息ID
     * @return 结果
     */
    public int deleteTBookByIds(Long[] ids);

    /**
     * 删除图书信息信息
     * 
     * @param id 图书信息ID
     * @return 结果
     */
    public int deleteTBookById(Long id);
}
