package com.admin.project.admin.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.admin.framework.aspectj.lang.annotation.Log;
import com.admin.framework.aspectj.lang.enums.BusinessType;
import com.admin.project.admin.domain.TAccess;
import com.admin.project.admin.service.ITAccessService;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.common.utils.SecurityUtils;
import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.page.TableDataInfo;

/**
 * 图书借阅Controller
 * 
 * @author admin
 * @date 2021-03-06
 */
@RestController
@RequestMapping("/admin/access")
public class TAccessController extends BaseController
{
    @Autowired
    private ITAccessService tAccessService;

    /**
     * 查询图书借阅列表
     */
    @PreAuthorize("@ss.hasPermi('admin:access:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAccess tAccess)
    {
        startPage();
        if(SecurityUtils.getLoginUser().getUser().getRoleId() == 2L) {
        	tAccess.setCreateBy(SecurityUtils.getUsername());
        }
        List<TAccess> list = tAccessService.selectTAccessList(tAccess);
        return getDataTable(list);
    }

    /**
     * 导出图书借阅列表
     */
    @PreAuthorize("@ss.hasPermi('admin:access:export')")
    @Log(title = "图书借阅", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TAccess tAccess)
    {
        List<TAccess> list = tAccessService.selectTAccessList(tAccess);
        ExcelUtil<TAccess> util = new ExcelUtil<TAccess>(TAccess.class);
        return util.exportExcel(list, "access");
    }

    /**
     * 获取图书借阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:access:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tAccessService.selectTAccessById(id));
    }

    /**
     * 新增图书借阅
     */
    @PreAuthorize("@ss.hasPermi('admin:access:add')")
    @Log(title = "图书借阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAccess tAccess)
    {
        return toAjax(tAccessService.insertTAccess(tAccess));
    }

    /**
     * 修改图书借阅
     */
    @PreAuthorize("@ss.hasPermi('admin:access:edit')")
    @Log(title = "图书借阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAccess tAccess)
    {
        return toAjax(tAccessService.updateTAccess(tAccess));
    }

    /**
     * 删除图书借阅
     */
    @PreAuthorize("@ss.hasPermi('admin:access:remove')")
    @Log(title = "图书借阅", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tAccessService.deleteTAccessByIds(ids));
    }
}
