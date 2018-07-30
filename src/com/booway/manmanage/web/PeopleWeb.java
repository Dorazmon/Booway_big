package com.booway.manmanage.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.booway.manmanage.entity.PageBean;
import com.booway.manmanage.entity.People;
import com.booway.manmanage.service.PeopleService;
import com.booway.manmanage.service.PeopleServiceImpl;

/**
 * @author Administrator
 *根据请求然后Route路由调用的People相关类
 */
public class PeopleWeb
{
    private PeopleService peopleService = new PeopleServiceImpl();

    public void getPeoples(HttpServletRequest request, HttpServletResponse response)
    {
        // String pId = req.getParameter("pId");
        //List<People> peoples = peopleService.getPeoples();
        //HttpSession session = request.getSession();
        //session.setAttribute("peoples", peoples);

    }

    public void addPeople(HttpServletRequest request, HttpServletResponse response)
    {
        People people = new People();
        people.setpId(UUID.randomUUID().toString());
        people.setpName(request.getParameter("pName"));
        people.setAge(Integer.parseInt(request.getParameter("age")));
        people.setJob(request.getParameter("job"));
        peopleService.addPeople(people);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response)
    {
        peopleService.deletePeople(request.getParameter("pId"));
    }

    public People get(HttpServletRequest request, HttpServletResponse response)
    {
        String pId = request.getParameter("pId");
        return peopleService.getPeopleById(pId);

    }

    public void update(HttpServletRequest request, HttpServletResponse response)
    {
        String pId = request.getParameter("pId");
        String pName = request.getParameter("pName");
        int age = Integer.parseInt(request.getParameter("age"));
        String job = request.getParameter("job");
        People people = new People();
        people.setpId(pId);
        people.setpName(pName);
        people.setAge(age);
        people.setJob(job);
        peopleService.updatePeople(people);
    }

    public void page(HttpServletRequest request, HttpServletResponse response)
    {
        int currentPage = 1;
        int row = 5;
        if (request.getParameter("page") == null)
        {
            PageBean<People> pageBean = peopleService.getPageBean(currentPage, row);
            HttpSession session = request.getSession();
            session.setAttribute("pageBean", pageBean);
        } else
        {
            currentPage = Integer.parseInt(request.getParameter("page"));
            PageBean<People> pageBean = peopleService.getPageBean(currentPage, row);
            HttpSession session = request.getSession();
            session.setAttribute("pageBean", pageBean);
        }
    }
}
