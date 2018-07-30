package com.booway.manmanage.dao;

import java.util.List;

import com.booway.manmanage.entity.People;
import com.booway.manmanage.utils.PeopleXmlUtil;

/**
 * @author Administrator
 *Xml实现的PeopleDao接口
 */
public class PeopleDaoImplByXml implements PeopleDao
{

    @Override
    public boolean addPeople(People people)
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.addPeople(people);
    }

    @Override
    public boolean updatePeople(People people)
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.updatePeopleById(people);
    }

    @Override
    public boolean deletePeople(String pId)
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.deletePeopleById(pId);
    }

    @Override
    public People getPeopleById(String pId)
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.getPeopleById(pId);
    }

    @Override
    public List<People> getAllPeoples()
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.getPeoples();
    }

    @Override
    public List<People> getPeoplesByPage(int currentPage, int row)
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.getPeoplesByPage(currentPage, row);
    }

    @Override
    public long getTotalCount()
    {
        // TODO Auto-generated method stub
        return PeopleXmlUtil.getTotalCount();
    }

}
