import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.google.gson.Gson;

import java.sql.*;

public class ScotGridDataProcessing
{
    public static void main(String[] args) throws IOException
    {
        // get sheet
        readExcel instance = new readExcel();
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

        //System.out.print(addresses);

        // create a vector of responses
        List<GeocoderResponse> geocoderResponses = new Vector<GeocoderResponse>();
        Gson gson = new Gson();

        // for each JSON address, add equivalent JSON class to the response list
        int i = 0;
        for (String address : addresses)
        {
            geocoderResponses.add(gson.fromJson(addresses.get(i), GeocoderResponse.class));

//            System.out.println((i+2) + ":");
//            System.out.println(geocoderResponses.get(i).getResults().get(0).getAddressComponents().get(1).getShortName() + "\n");

            ++i;
        }


        // TODO: establish a connection, execute the sql statements to insert data
        
        //Connection conn = null;
        //Properties connectionProps = new Properties();


    }
}
