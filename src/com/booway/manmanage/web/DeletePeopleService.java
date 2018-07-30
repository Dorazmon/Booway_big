package com.booway.manmanage.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booway.manmanage.service.PeopleService;
import com.booway.manmanage.service.PeopleServiceImpl;

/**
 * @author Administrator
 *
 */
public class DeletePeopleService implements ServiceInterface
{
    private PeopleService peopleService = new PeopleServiceImpl();
    @Override
    public void doService(HttpServletRequest request, HttpServletResponse response)
    {
        // TODO Auto-generated method stub
        peopleService.deletePeople(request.getParameter("pId"));
        String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        try
        {
            response.sendRedirect(path + "/People");
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
