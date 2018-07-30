package com.booway.manmanage.entity;

import java.util.List;

/**
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean<T>
{
    private List<T> pageData;
    private int currentPage;
    private int currentCount;
    private long totalPage;
    public List<T> getPageData()
    {
        return pageData;
    }
    public void setPageData(List<T> pageData)
    {
        this.pageData = pageData;
    }
    public int getCurrentPage()
    {
        return currentPage;
    }
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }
    public int getCurrentCount()
    {
        return currentCount;
    }
    public void setCurrentCount(int currentCount)
    {
        this.currentCount = currentCount;
    }
    public long getTotalPage()
    {
        return totalPage;
    }
    public void setTotalPage(long totalPage)
    {
        this.totalPage = totalPage;
    }
    @Override
    public String toString()
    {
        return "PageBean [pageData=" + pageData + ", currentPage=" + currentPage + ", currentCount=" + currentCount
                + ", totalPage=" + totalPage + "]";
    }
    
    
    
}
