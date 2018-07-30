package com.booway.manmanage.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Administrator
 *jdbc工具类
 */
public class JdbcUtil
{
    private static String url = null;
    private static String driver = null;
    private static String username = null;
    private static String password = null;

    private JdbcUtil()
    {

    }

    static
    {
        Properties prop = new Properties();
        InputStream in = null;
        try
        {
            in = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            prop.load(in);
            url = prop.getProperty("db_url");
            driver = prop.getProperty("db_driver");
            username = prop.getProperty("db_username");
            password = prop.getProperty("db_password");
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        } finally
        {
            StreamUtil.closeStream(in);
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);

    }
}
