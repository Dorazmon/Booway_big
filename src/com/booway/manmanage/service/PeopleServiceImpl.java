package com.booway.manmanage.service;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.booway.manmanage.dao.PeopleDao;
import com.booway.manmanage.entity.PageBean;
import com.booway.manmanage.entity.People;
import com.booway.manmanage.utils.StreamUtil;

/**
 * @author Administrator
 *PeopleService的 实现类
 */
public class PeopleServiceImpl implements PeopleService
{
    private PeopleDao peopleDao;

    public PeopleServiceImpl()
    {
        Properties prop = new Properties();
        InputStream in = null;
        try
        {
            in = PeopleServiceImpl.class.getClassLoader().getResourceAsStream("daoImpl.properties");
            prop.load(in);
            String className = prop.getProperty("class_name");
            Class<?> reflectClass = Class.forName(className);
            peopleDao = (PeopleDao) reflectClass.newInstance();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            StreamUtil.closeStream(in);
        }
    }

    @Override
    public boolean addPeople(People people)
    {
        // TODO Auto-generated method stub

        return peopleDao.addPeople(people);
    }

    @Override
    public boolean updatePeople(People people)
    {
        // TODO Auto-generated method stub
        return peopleDao.updatePeople(people);
    }

    @Override
    public People getPeopleById(String pId)
    {
        // TODO Auto-generated method stub
        return peopleDao.getPeopleById(pId);
    }

    @Override
    public List<People> getPeoples()
    {
        // TODO Auto-generated method stub
        return peopleDao.getAllPeoples();
    }

    @Override
    public boolean deletePeople(String pId)
    {
        // TODO Auto-generated method stub
        return peopleDao.deletePeople(pId);
    }

    @Override
    public PageBean<People> getPageBean(int currentPage, int row)
    {
        // TODO Auto-generated method stub
        PageBean<People> pageBean = new PageBean<People>();
        List<People> peoples = peopleDao.getPeoplesByPage(currentPage, row);
        int currentCount = peoples.size();
        long totalCount = peopleDao.getTotalCount();
        int totalPage = (int) Math.ceil((1.0 * totalCount) / row);
        pageBean.setPageData(peoples);
        pageBean.setCurrentPage(currentPage);
        pageBean.setCurrentCount(currentCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

}
