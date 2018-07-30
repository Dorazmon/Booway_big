package com.booway.manmanage.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booway.manmanage.entity.People;
import com.booway.manmanage.service.PeopleService;
import com.booway.manmanage.service.PeopleServiceImpl;

/**
 * @author Administrator
 *
 */
public class AddPeopleService implements ServiceInterface
{
    private PeopleWeb peopleWeb = new PeopleWeb();

    @Override
    public void doService(HttpServletRequest request, HttpServletResponse response)
    {
        // TODO Auto-generated method stub
        peopleWeb.addPeople(request, response);
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
