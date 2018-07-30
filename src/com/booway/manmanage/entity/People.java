package com.booway.manmanage.entity;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class People implements Serializable
{

    private static final long serialVersionUID = -6504239428350687682L;
    private String pId;
    private String pName;
    private int age;
    /**
     * 职业
     */
    private String job;

    public String getpId()
    {
        return pId;
    }

    public void setpId(String pId)
    {
        this.pId = pId;
    }

    public String getpName()
    {
        return pName;
    }

    public void setpName(String pName)
    {
        this.pName = pName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    @Override
    public String toString()
    {
        return "People [pId=" + pId + ", pName=" + pName + ", age=" + age + ", job=" + job + "]";
    }

}
