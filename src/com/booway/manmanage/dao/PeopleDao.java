package com.booway.manmanage.dao;

import java.util.List;

import com.booway.manmanage.entity.People;

/**
 * @author Administrator
 *PeopleDao接口
 */
public interface PeopleDao
{
    /**
     * @param people
     * @return
     * 添加一个people
     */
    public boolean addPeople(People people);

    /**
     * @param people
     * @return
     * 更新一个people
     */
    public boolean updatePeople(People people);

    /**
     * @param pId
     * @return
     * 根据id删除people
     */
    public boolean deletePeople(String pId);

    /**
     * @param pId
     * @return
     * 根据id获得people
     */
    public People getPeopleById(String pId);

    /**
     * @return
     * 查询所有的people
     */
    public List<People> getAllPeoples();

    /**
     * @return
     * 查询当前分页的所有people
     */
    public List<People> getPeoplesByPage(int currentPage, int row);

    /**
     * @return
     * 查询people的总条数
     */
    public long getTotalCount();
}
