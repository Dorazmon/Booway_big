package com.booway.manmanage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public class PagePeopleService implements ServiceInterface
{
    private PeopleWeb peopleWeb = new PeopleWeb();

    @Override
    public void doService(HttpServletRequest request, HttpServletResponse response)
    {
        // TODO Auto-generated method stub
        peopleWeb.page(request, response);
        try
        {
            request.getRequestDispatcher("/WEB-INF/pages/people.jsp").forward(request, response);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }

}
