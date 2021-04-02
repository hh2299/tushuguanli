package com.admin.project.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.project.admin.mapper.TBookMapper;
import com.admin.project.admin.domain.TBook;
import com.admin.project.admin.service.ITBookService;

/**
 * 图书信息Service业务层处理
 * 
 * @author admin
 * @date 2021-03-06
 */
@Service
public class TBookServiceImpl implements ITBookService 
{
    @Autowired
    private TBookMapper tBookMapper;

    /**
     * 查询图书信息
     * 
     * @param id 图书信息ID
     * @return 图书信息
     */
    @Override
    public TBook selectTBookById(Long id)
    {
        return tBookMapper.selectTBookById(id);
    }

    /**
     * 查询图书信息列表
     * 
     * @param tBook 图书信息
     * @return 图书信息
     */
    @Override
    public List<TBook> selectTBookList(TBook tBook)
    {
        return tBookMapper.selectTBookList(tBook);
    }

    /**
     * 新增图书信息
     * 
     * @param tBook 图书信息
     * @return 结果
     */
    @Override
    public int insertTBook(TBook tBook)
    {
        return tBookMapper.insertTBook(tBook);
    }

    /**
     * 修改图书信息
     * 
     * @param tBook 图书信息
     * @return 结果
     */
    @Override
    public int updateTBook(TBook tBook)
    {
        return tBookMapper.updateTBook(tBook);
    }

    /**
     * 批量删除图书信息
     * 
     * @param ids 需要删除的图书信息ID
     * @return 结果
     */
    @Override
    public int deleteTBookByIds(Long[] ids)
    {
        return tBookMapper.deleteTBookByIds(ids);
    }

    /**
     * 删除图书信息信息
     * 
     * @param id 图书信息ID
     * @return 结果
     */
    @Override
    public int deleteTBookById(Long id)
    {
        return tBookMapper.deleteTBookById(id);
    }
}
