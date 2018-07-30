package com.booway.manmanage.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booway.manmanage.utils.StreamUtil;

/**
 * Servlet implementation class RouteServlet
 */

public class RouteServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private Properties prop;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RouteServlet()
    {
        super();
        // TODO Auto-generated constructor stub
        InputStream in = null;
        try
        {
            prop = new Properties();
            in = RouteServlet.class.getClassLoader().getResourceAsStream("classmap.properties");
            prop.load(in);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            StreamUtil.closeStream(in);
        }
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        String path = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        //处理编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        String[] str = uri.split("/");
        //获得Prop的Key
        String uriMethod = str[str.length - 1];
        if (!uriMethod.trim().equals("") || uriMethod != null)
        {
            //获得全类名
            String className = prop.getProperty(uriMethod);  
            if (className != null)
            {
                try
                {   
                    Class<?> reflectClass = Class.forName(className);
                    ServiceInterface service = (ServiceInterface) reflectClass.newInstance();
                    //执行
                    service.doService(request, response);
                    return;
                } catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    
                }
            }
        }
        //找不到key时返回404
        String peopleUrl = path + "/People";
        response.setStatus(404);
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("找不到页面");
        out.print("<a href='" + peopleUrl + "'>返回people列表</a>");
        out.flush();

        //        if (str.endsWith("/People/add") && reMethod.equals("POST"))
        //        {
        //            peopleWeb.addPeople(request, response);
        //            response.sendRedirect(path + "/People");
        //        } else if (str.endsWith("/People/update") && reMethod.equals("POST"))
        //        {
        //            peopleWeb.update(request, response);
        //            response.sendRedirect(path + "/People");
        //        } else if (str.indexOf("/People/delete/pId") != -1 && reMethod.equals("GET"))
        //        {
        //            peopleWeb.delete(request, response);
        //            response.sendRedirect(path + "/People");
        //        } else if (str.indexOf("/People/get/pId") != -1 && reMethod.equals("GET"))
        //        {
        //            People people = peopleWeb.get(request, response);
        //            String json = JSONObject.toJSONString(people);
        //            response.setHeader("Content-Type", "application/json;charset=UTF-8");
        //            response.getWriter().write(json);
        //            response.getWriter().flush();
        //
        //        } else if (str.indexOf("/People/page") != -1 && reMethod.equals("GET"))
        //        {
        //            peopleWeb.page(request, response);
        //            request.getRequestDispatcher("/WEB-INF/pages/people.jsp").forward(request, response);
        //
        //        } else if (str.endsWith("/People"))
        //        {
        //            peopleWeb.page(request, response);
        //            request.getRequestDispatcher("/WEB-INF/pages/people.jsp").forward(request, response);
        //        } else
        //        {
        //            String peopleUrl = path + "/People";
        //            response.setStatus(404);
        //            response.setHeader("Content-Type", "text/html;charset=UTF-8");
        //            PrintWriter out = response.getWriter();
        //            out.println("找不到页面");
        //            out.print("<a href='" + peopleUrl + "'>返回people列表</a>");
        //            out.flush();
        //        }

    }

}
