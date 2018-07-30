package com.booway.manmanage.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.booway.manmanage.entity.People;

/**
 * @author Administrator
 *
 */
public class GetPeopleService implements ServiceInterface
{
    private PeopleWeb peopleWeb = new PeopleWeb();

    @Override
    public void doService(HttpServletRequest request, HttpServletResponse response)
    {
        // TODO Auto-generated method stub
        People people = peopleWeb.get(request, response);
        String json = JSONObject.toJSONString(people);
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try
        {
            response.getWriter().write(json);
            response.getWriter().flush();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
