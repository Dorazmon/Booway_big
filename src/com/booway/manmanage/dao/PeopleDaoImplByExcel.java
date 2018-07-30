package com.booway.manmanage.dao;

import java.util.List;

import com.booway.manmanage.entity.People;
import com.booway.manmanage.utils.PeopleExcelUtil;

/**
 * @author Administrator
 *
 */
public class PeopleDaoImplByExcel implements PeopleDao
{

    @Override
    public boolean addPeople(People people)
    {
        // TODO Auto-generated method stub

        return PeopleExcelUtil.addPeople(people);
    }

    @Override
    public boolean updatePeople(People people)
    {
        // TODO Auto-generated method stub
        return PeopleExcelUtil.updatePeople(people);
    }

    @Override
    public boolean deletePeople(String pId)
    {
        // TODO Auto-generated method stub
        return PeopleExcelUtil.deleteById(pId);
    }

    @Override
    public People getPeopleById(String pId)
    {
        // TODO Auto-generated method stub
        return PeopleExcelUtil.getPeopleById(pId);
    }

    @Override
    public List<People> getAllPeoples()
    {
        // TODO Auto-generated method stub
        return PeopleExcelUtil.getPeoples();
    }

    @Override
    public List<People> getPeoplesByPage(int currentPage, int row)
    {
        // TODO Auto-generated method stub

        return PeopleExcelUtil.getPeoplesByPage(currentPage, row);
    }

    @Override
    public long getTotalCount()
    {
        // TODO Auto-generated method stub
        return PeopleExcelUtil.getTotalCount();
    }

}
