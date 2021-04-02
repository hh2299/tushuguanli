package com.admin.project.admin.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.admin.common.exception.CustomException;
import com.admin.common.utils.DateUtils;
import com.admin.common.utils.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.project.admin.mapper.TAccessMapper;
import com.admin.project.admin.domain.TAccess;
import com.admin.project.admin.domain.TBook;
import com.admin.project.admin.service.ITAccessService;
import com.admin.project.admin.service.ITBookService;
import com.admin.project.system.domain.SysUser;
import com.admin.project.system.service.ISysUserService;

/**
 * 图书借阅Service业务层处理
 * 
 * @author admin
 * @date 2021-03-06
 */
@Service
public class TAccessServiceImpl implements ITAccessService 
{
    @Autowired
    private TAccessMapper tAccessMapper;
    @Autowired
    private ISysUserService userSrv;
    @Autowired
    private ITBookService bookSrv;

    /**
     * 查询图书借阅
     * 
     * @param id 图书借阅ID
     * @return 图书借阅
     */
    @Override
    public TAccess selectTAccessById(Long id)
    {
        return tAccessMapper.selectTAccessById(id);
    }

    /**
     * 查询图书借阅列表
     * 
     * @param tAccess 图书借阅
     * @return 图书借阅
     */
    @Override
    public List<TAccess> selectTAccessList(TAccess tAccess)
    {
        return tAccessMapper.selectTAccessList(tAccess);
    }

    /**
     * 新增图书借阅
     * 
     * @param tAccess 图书借阅
     * @return 结果
     */
    @Override
    public int insertTAccess(TAccess tAccess)
    {
    	tAccess.setCreateBy(SecurityUtils.getUsername());
        tAccess.setCreateTime(DateUtils.getNowDate());
        TBook selectTBookById = bookSrv.selectTBookById(tAccess.getBookId());
        if(selectTBookById == null) {
        	throw new CustomException("图书不存在");
        }else {
        	int totle = Integer.parseInt(selectTBookById.getTotle()) ;
        	if(totle == 0) {
        		throw new CustomException("图书数量不足");
        	}
        	selectTBookById.setTotle(totle-1+"");
        	bookSrv.updateTBook(selectTBookById);
        }
        return tAccessMapper.insertTAccess(tAccess);
    }

    /**
     * 修改图书借阅
     * 
     * @param tAccess 图书借阅
     * @return 结果
     */
    @Override
    public int updateTAccess(TAccess tAccess)
    {
    	TAccess old = tAccessMapper.selectTAccessById(tAccess.getId());
    	if(old.getStatus().equals("0") && "1".equals(tAccess.getStatus())) {
    		tAccess.setBackTime(DateUtils.getNowDate());
    		SysUser selectUserByUserName = userSrv.selectUserByUserName(old.getCreateBy());
    		Double price = selectUserByUserName.getPrice();
    		if(price == null) {
    			price = .0;
    		}
    		Long time = tAccess.getBackTime().getTime() - tAccess.getCreateTime().getTime();
    		int days = (int) (time/(24*60*60*1000));
    		if(days<=30) {
    			tAccess.setPrice(new BigDecimal(0));
    		}else {
    			tAccess.setPrice(new BigDecimal(0.1*(days-30)));
    		}
    		
    		selectUserByUserName.setPrice(price-tAccess.getPrice().doubleValue());
    		userSrv.updateUser(selectUserByUserName);
    	}
        return tAccessMapper.updateTAccess(tAccess);
    }

    /**
     * 批量删除图书借阅
     * 
     * @param ids 需要删除的图书借阅ID
     * @return 结果
     */
    @Override
    public int deleteTAccessByIds(Long[] ids)
    {
        return tAccessMapper.deleteTAccessByIds(ids);
    }

    /**
     * 删除图书借阅信息
     * 
     * @param id 图书借阅ID
     * @return 结果
     */
    @Override
    public int deleteTAccessById(Long id)
    {
        return tAccessMapper.deleteTAccessById(id);
    }
}
