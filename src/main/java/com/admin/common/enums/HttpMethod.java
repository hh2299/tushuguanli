package com.admin.common.enums;

import java.util.HashMap;
import java.util.Map;
import org.springframework.lang.Nullable;

/**
 * 请求方式
 *
 * @author 
 */
public enum HttpMethod
{
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    private static final Map<String, HttpMethod> mappings = new HashMap<>(16);

   //随着类的加载而执行，而且只执行一次,将http请求方法加入到hashmap
    static
    {
        for (HttpMethod httpMethod : values())
        {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    /**
     * 返回该方法的枚举变量
     * @param method
     * @return
     */
    @Nullable
    public static HttpMethod resolve(@Nullable String method)
    {
        return (method != null ? mappings.get(method) : null);
    }

    /**
     * 是否匹配
     * @param method
     * @return
     */
    public boolean matches(String method)
    {
        return (this == resolve(method));
    }
}
