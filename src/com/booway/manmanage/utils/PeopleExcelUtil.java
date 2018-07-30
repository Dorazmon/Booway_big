package com.booway.manmanage.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.booway.manmanage.entity.People;

/**
 * @author Administrator
 *存储People对象数据
 */
public class PeopleExcelUtil
{
    private static String path;

    private PeopleExcelUtil()
    {

    }

    static
    {
        Properties prop = new Properties();
        InputStream in = null;
        try
        {
            in = JdbcUtil.class.getClassLoader().getResourceAsStream("path.properties");
            prop.load(in);
            path = prop.getProperty("excel_path");
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
        InputStream is = null;
        Workbook workbook = null;
        OutputStream os = null;

        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(people.getpId());
            row.createCell(1).setCellValue(people.getpName());
            row.createCell(2).setCellValue(people.getAge());
            row.createCell(3).setCellValue(people.getJob());
            os = new FileOutputStream(path);
            workbook.write(os);
            os.flush();
            return true;
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(os, workbook, is);
        }
        return false;

    }

    public static boolean deleteById(String pId)
    {
        InputStream is = null;
        Workbook workbook = null;
        OutputStream os = null;

        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum(); i++)
            {
                String cellValue = sheet.getRow(i + 1).getCell(0).getStringCellValue();
                if (pId.equals(cellValue))
                {
                    //sheet.removeRow(sheet.getRow(i + 1));
                    //删除行后整体向上平移
                    sheet.shiftRows(i + 2, sheet.getLastRowNum() + 1, -1);
                }
            }
            os = new FileOutputStream(path);
            workbook.write(os);
            os.flush();
            return true;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(os, workbook, is);
        }
        return false;
    }

    public static boolean updatePeople(People people)
    {
        InputStream is = null;
        Workbook workbook = null;
        OutputStream os = null;

        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum(); i++)
            {
                String cellValue = sheet.getRow(i + 1).getCell(0).getStringCellValue();
                if (people.getpId().equals(cellValue))
                {
                    sheet.getRow(i + 1).getCell(1).setCellValue(people.getpName());
                    sheet.getRow(i + 1).getCell(2).setCellValue(people.getAge());
                    sheet.getRow(i + 1).getCell(3).setCellValue(people.getJob());
                }
            }
            os = new FileOutputStream(path);
            workbook.write(os);
            os.flush();
            return true;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(os, workbook, is);
        }
        return false;

    }

    public static People getPeopleById(String pId)
    {
        InputStream is = null;
        Workbook workbook = null;
        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum(); i++)
            {
                String cellValue = sheet.getRow(i + 1).getCell(0).getStringCellValue();
                if (pId.equals(cellValue))
                {
                    People people = new People();
                    people.setpId(sheet.getRow(i + 1).getCell(0).getStringCellValue());
                    people.setpName(sheet.getRow(i + 1).getCell(1).getStringCellValue());
                    people.setAge((int) (sheet.getRow(i + 1).getCell(2).getNumericCellValue()));
                    people.setJob(sheet.getRow(i + 1).getCell(3).getStringCellValue());
                    return people;
                }
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(workbook, is);
        }

        return null;

    }

    public static List<People> getPeoples()
    {
        InputStream is = null;
        Workbook workbook = null;
        List<People> peoples = new ArrayList<People>();
        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < sheet.getLastRowNum(); i++)
            {
                People people = new People();
                people.setpId(sheet.getRow(i + 1).getCell(0).getStringCellValue());
                people.setpName(sheet.getRow(i + 1).getCell(1).getStringCellValue());
                people.setAge((int) (sheet.getRow(i + 1).getCell(2).getNumericCellValue()));
                people.setJob(sheet.getRow(i + 1).getCell(3).getStringCellValue());
                peoples.add(people);
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(workbook, is);
        }

        return peoples;

    }

    public static List<People> getPeoplesByPage(int currentPage, int row)
    {
        InputStream is = null;
        Workbook workbook = null;
        List<People> peoples = new ArrayList<People>();
        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            int j = (currentPage - 1) * 5 + 1;
            for (int i = 0; i < row; i++)
            {
                if (sheet.getRow(j) != null)
                {
                    People people = new People();
                    people.setpId(sheet.getRow(j).getCell(0).getStringCellValue());
                    people.setpName(sheet.getRow(j).getCell(1).getStringCellValue());
                    people.setAge((int) (sheet.getRow(j).getCell(2).getNumericCellValue()));
                    people.setJob(sheet.getRow(j).getCell(3).getStringCellValue());
                    peoples.add(people);
                }
                j++;
            }
            return peoples;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(workbook, is);
        }
        return peoples;

    }

    public static long getTotalCount()
    {
        InputStream is = null;
        Workbook workbook = null;
        try
        {
            is = new FileInputStream(path);
            workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            long totalCount = sheet.getLastRowNum();
            return totalCount;
        } catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        } finally
        {
            StreamUtil.closeStream(workbook, is);
        }
        return 0;

    }

}
