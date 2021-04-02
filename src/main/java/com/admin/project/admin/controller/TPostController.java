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
import com.admin.project.admin.domain.TPost;
import com.admin.project.admin.service.ITPostService;
import com.admin.framework.web.controller.BaseController;
import com.admin.framework.web.domain.AjaxResult;
import com.admin.common.utils.poi.ExcelUtil;
import com.admin.framework.web.page.TableDataInfo;

/**
 * 通知公告Controller
 * 
 * @author admin
 * @date 2021-03-06
 */
@RestController
@RequestMapping("/admin/post")
public class TPostController extends BaseController
{
    @Autowired
    private ITPostService tPostService;

    /**
     * 查询通知公告列表
     */
    @PreAuthorize("@ss.hasPermi('admin:post:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPost tPost)
    {
        startPage();
        List<TPost> list = tPostService.selectTPostList(tPost);
        return getDataTable(list);
    }

    /**
     * 导出通知公告列表
     */
    @PreAuthorize("@ss.hasPermi('admin:post:export')")
    @Log(title = "通知公告", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TPost tPost)
    {
        List<TPost> list = tPostService.selectTPostList(tPost);
        ExcelUtil<TPost> util = new ExcelUtil<TPost>(TPost.class);
        return util.exportExcel(list, "post");
    }

    /**
     * 获取通知公告详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:post:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tPostService.selectTPostById(id));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPermi('admin:post:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPost tPost)
    {
        return toAjax(tPostService.insertTPost(tPost));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPermi('admin:post:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPost tPost)
    {
        return toAjax(tPostService.updateTPost(tPost));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPermi('admin:post:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tPostService.deleteTPostByIds(ids));
    }
}
