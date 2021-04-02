package com.admin.project.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.admin.framework.aspectj.lang.annotation.Excel;
import com.admin.framework.web.domain.BaseEntity;

/**
 * 图书信息对象 t_book
 * 
 * @author admin
 * @date 2021-03-06
 */
public class TBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 书名 */
    @Excel(name = "书名")
    private String name;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** isbn */
    @Excel(name = "isbn")
    private String isbn;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 数量 */
    @Excel(name = "数量")
    private String totle;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setIsbn(String isbn) 
    {
        this.isbn = isbn;
    }

    public String getIsbn() 
    {
        return isbn;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setTotle(String totle) 
    {
        this.totle = totle;
    }

    public String getTotle() 
    {
        return totle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("author", getAuthor())
            .append("isbn", getIsbn())
            .append("type", getType())
            .append("totle", getTotle())
            .toString();
    }
}
