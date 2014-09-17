/**
 * Created by isaaclong on 9/17/14.
 */

import java.io.*;

import org.apache.poi.xssf.usermodel.*;

public class readExcel
{
    public void createNewWorkbook()
    {
        XSSFWorkbook wb = new XSSFWorkbook();
        try
        {
            FileOutputStream fileOut = new FileOutputStream("workbook.xls");
            try
            {
                wb.write(fileOut);
                fileOut.close();
            }
            catch(IOException ioe)
            {
                System.out.println(ioe.getMessage());
            }

        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
        }
    }

    // reads hardcoded excel file name and returns a poi XFFSWorkbook
    public XSSFWorkbook readWorkbook()
    {
        XSSFWorkbook wbDefault = new XSSFWorkbook();
        try
        {
            String pathname = "";

            // check what OS we are running on
            String os = System.getProperty("os.name").toLowerCase();

            if(os.contains("win")) pathname = "I:\\c3\\Projects\\Career-Center-Yelp-Project\\Design-Docs\\Riverside-Jobs-090514.xlsx";
            else if(os.contains("mac")) pathname = "/Volumes/d5/c3/Projects/Career-Center-Yelp-Project/Design-Docs/Riverside-Jobs-090514.xlsx";

            FileInputStream fileIn = new FileInputStream(pathname);
            try
            {
                XSSFWorkbook wb = new XSSFWorkbook(fileIn);
                return wb;
            }
            catch(IOException ioe)
            {
                System.out.println(ioe.getMessage());
                return wbDefault;
            }
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(fnfe.getMessage());
            return wbDefault;
        }
    }

}
