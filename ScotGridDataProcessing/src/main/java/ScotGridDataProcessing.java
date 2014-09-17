/**
 * Created by isaaclong on 9/17/14.
 */

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ScotGridDataProcessing
{
    public static void main(String[] args) throws IOException
    {
        // get sheet
        readExcel instance = new readExcel();
        //instance.createNewWorkbook();
        XSSFWorkbook riverside_jobs = instance.readWorkbook();
        XSSFSheet sheet = riverside_jobs.getSheetAt(0);

        // iterate through rows, construct full address, send to google, store in list
        List<String> addresses = new Vector<String>();

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext())
        {
            String fullAddress = "";

            Row row = rowIterator.next();

            for (int i = 1; i < 5; i++) {
                //System.out.println(row.getCell(i).getStringCellValue());
                fullAddress += row.getCell(i).getStringCellValue();
                if (i != 4) fullAddress += ",";
            }
            //System.out.println(fullAddress);
            GeocodeImplementation gi = new GeocodeImplementation();
            addresses.add(gi.getJSONByGoogle(fullAddress));
        }

        System.out.print(addresses);
    }
}
