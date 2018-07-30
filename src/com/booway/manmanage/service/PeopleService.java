package com.booway.manmanage.service;

import java.util.List;

import com.booway.manmanage.entity.PageBean;
import com.booway.manmanage.entity.People;

/**
 * @author Administrator
 *PeopleService接口
 */
public interface PeopleService
{
    public boolean addPeople(People people);

    public boolean updatePeople(People people);

    public People getPeopleById(String pId);

    public List<People> getPeoples();

    public boolean deletePeople(String pId);
    
    /**
     * @param currentPage
     * @param row
     * @return
     * 获得一个分页对象
     */
    public PageBean<People> getPageBean(int currentPage,int row);

}
