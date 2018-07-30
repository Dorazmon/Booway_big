package com.booway.manmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booway.manmanage.entity.People;
import com.booway.manmanage.utils.JdbcUtil;
import com.booway.manmanage.utils.StreamUtil;

/**
 * @author Administrator
 *
 */
public class PeopleDaoImplByDatabase implements PeopleDao
{

    @Override
    public boolean addPeople(People people)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            String sql = "insert into people(p_id,p_name,p_age,p_job) values(?,?,?,?)";
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, people.getpId());
            ps.setString(2, people.getpName());
            ps.setInt(3, people.getAge());
            ps.setString(4, people.getJob());
            return ps.executeUpdate() > 0;

        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(ps, connection);
        }

        return false;
    }

    @Override
    public boolean updatePeople(People people)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            String sql = "update people set p_name = ?,p_age = ?,p_job = ? where p_id = ?";
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, people.getpName());
            ps.setInt(2, people.getAge());
            ps.setString(3, people.getJob());
            ps.setString(4, people.getpId());
            return ps.executeUpdate() > 0;

        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(ps, connection);
        }

        return false;
    }

    @Override
    public boolean deletePeople(String pId)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        try
        {
            String sql = "delete from people where p_id = ?";
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, pId);
            return ps.executeUpdate() > 0;

        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(ps, connection);
        }
        return false;
    }

    @Override
    public People getPeopleById(String pId)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            String sql = "select * from people where p_id = ?";
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, pId);
            rs = ps.executeQuery();
            People people = new People();
            while (rs.next())
            {
                people.setpId(rs.getString("p_id"));
                people.setpName(rs.getString("p_name"));
                people.setAge(rs.getInt("p_age"));
                people.setJob(rs.getString("p_job"));
                return people;
            }

        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(rs, ps, connection);
        }

        return null;
    }

    @Override
    public List<People> getAllPeoples()
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<People> peoples = new ArrayList<People>();
        try
        {
            String sql = "select * from people";
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                People people = new People();
                people.setpId(rs.getString("p_id"));
                people.setpName(rs.getString("p_name"));
                people.setAge(rs.getInt("p_age"));
                people.setJob(rs.getString("p_job"));
                peoples.add(people);
            }
            return peoples;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(rs, ps, connection);
        }
        return null;

    }

    @Override
    public List<People> getPeoplesByPage(int currentPage, int row)
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int index = (currentPage - 1) * row;
        List<People> peoples = new ArrayList<People>();
        try
        {    
            String sql = "select * from people limit ?,?";
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, index);
            ps.setInt(2, row);
            rs = ps.executeQuery();
            while (rs.next())
            {
                People people = new People();
                people.setpId(rs.getString("p_id"));
                people.setpName(rs.getString("p_name"));
                people.setAge(rs.getInt("p_age"));
                people.setJob(rs.getString("p_job"));
                peoples.add(people);
            }
            return peoples;

        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(rs, ps, connection);
        }
        return null;
    }

    @Override
    public long getTotalCount()
    {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) from people";
        try
        {   
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                return rs.getLong("count(*)");
            }
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(rs, ps, connection);
        }
        return 0;
    }
}
