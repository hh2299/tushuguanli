package com.admin.project.admin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.admin.framework.aspectj.lang.annotation.Excel;
import com.admin.framework.web.domain.BaseEntity;

/**
 * 图书借阅对象 t_access
 * 
 * @author admin
 * @date 2021-03-06
 */
public class TAccess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 图书 */
    @Excel(name = "图书")
    private Long bookId;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date backTime;

    private TBook book;
    
    public TBook getBook() {
		return book;
	}

	public void setBook(TBook book) {
		this.book = book;
	}

	public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setBackTime(Date backTime) 
    {
        this.backTime = backTime;
    }

    public Date getBackTime() 
    {
        return backTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("backTime", getBackTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
