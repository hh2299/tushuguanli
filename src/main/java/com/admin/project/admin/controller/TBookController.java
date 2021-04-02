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
import com.admin.project.admin.domain.TBook;
import com.admin.project.admin.service.ITBookService;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.page.TableDataInfo;

/**
 * 图书信息Controller
 * 
 * @author admin
 * @date 2021-03-06
 */
@RestController
@RequestMapping("/admin/book")
public class TBookController extends BaseController
{
    @Autowired
    private ITBookService tBookService;

    /**
     * 查询图书信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TBook tBook)
    {
        startPage();
        List<TBook> list = tBookService.selectTBookList(tBook);
        return getDataTable(list);
    }

    /**
     * 导出图书信息列表
     */
    @PreAuthorize("@ss.hasPermi('admin:book:export')")
    @Log(title = "图书信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TBook tBook)
    {
        List<TBook> list = tBookService.selectTBookList(tBook);
        ExcelUtil<TBook> util = new ExcelUtil<TBook>(TBook.class);
        return util.exportExcel(list, "book");
    }

    /**
     * 获取图书信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:book:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tBookService.selectTBookById(id));
    }

    /**
     * 新增图书信息
     */
    @PreAuthorize("@ss.hasPermi('admin:book:add')")
    @Log(title = "图书信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBook tBook)
    {
        return toAjax(tBookService.insertTBook(tBook));
    }

    /**
     * 修改图书信息
     */
    @PreAuthorize("@ss.hasPermi('admin:book:edit')")
    @Log(title = "图书信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBook tBook)
    {
        return toAjax(tBookService.updateTBook(tBook));
    }

    /**
     * 删除图书信息
     */
    @PreAuthorize("@ss.hasPermi('admin:book:remove')")
    @Log(title = "图书信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tBookService.deleteTBookByIds(ids));
    }
}
