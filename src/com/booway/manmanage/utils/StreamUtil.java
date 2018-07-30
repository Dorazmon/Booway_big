package com.booway.manmanage.utils;


/**
 * @author Administrator
 *关闭流工具类
 */
public class StreamUtil
{
    private StreamUtil()
    {
    }

    /**
     * 
     * @param streams
     */
    public static void closeStream(AutoCloseable... streams)
    {
        if (streams != null && streams.length > 0)
        {
            for (AutoCloseable stream : streams)
            {
                if (stream != null)
                {
                    try
                    {
                        stream.close();
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
