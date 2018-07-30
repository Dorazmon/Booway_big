package com.booway.manmanage.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.booway.manmanage.entity.People;

/**
 * @author Administrator
 *操作People对象的Xml工具类
 */
public class PeopleXmlUtil
{
    private static String path;
    static
    {
        Properties prop = new Properties();
        InputStream in = null;
        try
        {
            in = JdbcUtil.class.getClassLoader().getResourceAsStream("path.properties");
            prop.load(in);
            path = prop.getProperty("xml_path");
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            StreamUtil.closeStream(in);
        }
    }

    public static boolean addPeople(People people)
    {
        SAXReader reader = new SAXReader();
        XMLWriter xw = null;
        FileWriter os = null;
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            Element peopleElement = peoplesElement.addElement("people");
            peopleElement.addAttribute("pId", people.getpId());
            peopleElement.addAttribute("名字", people.getpName());
            peopleElement.addAttribute("年龄", String.valueOf(people.getAge()));
            peopleElement.addAttribute("职业", people.getJob());
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            os = new FileWriter(path);
            xw = new XMLWriter(os, format);
            xw.write(document);
            xw.flush();
            os.flush();
            return true;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            try
            {
                os.close();
                xw.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return false;
    }

    public static boolean updatePeopleById(People people)
    {
        SAXReader reader = new SAXReader();
        XMLWriter xw = null;
        FileWriter os = null;
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            List<Element> peopleElements = peoplesElement.elements("people");
            for (Element peopleElement : peopleElements)
            {
                Attribute at = peopleElement.attribute("pId");
                if (at.getValue().equals(people.getpId()))
                {
                    peopleElement.attribute("名字").setValue(people.getpName());
                    peopleElement.attribute("年龄").setValue(String.valueOf(people.getAge()));
                    peopleElement.attribute("职业").setValue(people.getJob());
                }
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            os = new FileWriter(path);
            xw = new XMLWriter(os, format);
            xw.write(document);
            xw.flush();
            os.flush();
            return true;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {

            try
            {
                os.close();
                xw.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean deletePeopleById(String pId)
    {
        SAXReader reader = new SAXReader();
        XMLWriter xw = null;
        FileWriter os = null;
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            List<Element> peopleElements = peoplesElement.elements("people");
            for (Element peopleElement : peopleElements)
            {
                Attribute at = peopleElement.attribute("pId");
                if (at.getValue().equals(pId))
                {
                    peopleElement.detach();
                }
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            os = new FileWriter(path);
            xw = new XMLWriter(os, format);
            xw.write(document);
            xw.flush();
            os.flush();
            return true;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {

            try
            {
                os.close();
                xw.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    public static People getPeopleById(String pId)
    {
        SAXReader reader = new SAXReader();
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            List<Element> peopleElements = peoplesElement.elements("people");
            for (Element peopleElement : peopleElements)
            {
                Attribute at = peopleElement.attribute("pId");
                if (at.getValue().equals(pId))
                {
                    People people = new People();
                    people.setpId(peopleElement.attributeValue("pId"));
                    people.setpName(peopleElement.attributeValue("名字"));
                    people.setAge(Integer.parseInt(peopleElement.attributeValue("年龄")));
                    people.setJob(peopleElement.attributeValue("职业"));
                    return people;
                }
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    public static List<People> getPeoples()
    {
        SAXReader reader = new SAXReader();
        List<People> peoples = new ArrayList<People>();
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            List<Element> peopleElements = peoplesElement.elements("people");
            for (Element peopleElement : peopleElements)
            {
                People people = new People();
                people.setpId(peopleElement.attributeValue("pId"));
                people.setpName(peopleElement.attributeValue("名字"));
                people.setAge(Integer.parseInt(peopleElement.attributeValue("年龄")));
                people.setJob(peopleElement.attributeValue("职业"));
                peoples.add(people);

            }
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return peoples;

    }

    public static List<People> getPeoplesByPage(int currentPage, int row)
    {
        // TODO Auto-generated method stub
        SAXReader reader = new SAXReader();
        List<People> peoples = new ArrayList<People>();
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            List<Element> peopleElements = peoplesElement.elements("people");
            int j = (currentPage - 1) * row;
            for (int i = 0; i < row; i++)
            {
                if (j <= peopleElements.size() - 1)
                {
                    Element peopleElement = peopleElements.get(j);
                    if (peopleElement != null)
                    {
                        People people = new People();
                        people.setpId(peopleElement.attributeValue("pId"));
                        people.setpName(peopleElement.attributeValue("名字"));
                        people.setAge(Integer.parseInt(peopleElement.attributeValue("年龄")));
                        people.setJob(peopleElement.attributeValue("职业"));
                        peoples.add(people);
                    }
                    j++;
                }
            }
            return peoples;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }

        return peoples;
    }

    public static long getTotalCount()
    {
        // TODO Auto-generated method stub
        SAXReader reader = new SAXReader();
        try
        { // 通过read方法读取一个文件 转换成Document对象  
            Document document = reader.read(new File(path));
            Element peoplesElement = document.getRootElement();
            List<Element> peopleElements = peoplesElement.elements("people");
            return peopleElements.size();
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return 0;
    }

}
