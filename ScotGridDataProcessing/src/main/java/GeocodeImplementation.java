/**
 * Created by isaaclong on 9/17/14.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class GeocodeImplementation
{
    /*
   Geocode BASE request url
    */
    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json";

    /*
    fullAddress String is in format "address,city,state,zip" with address meaning "street number + route"
     */

    public String getJSONByGoogle(String fullAddress) throws IOException
    {
        URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress,"UTF-8") + "&key=AIzaSyASiYBLbaG6eocJQ4rl33NPD5IMv63cHiE");

        // open connection
        URLConnection conn = url.openConnection();

        // sample byte array output stream that we will use to keep the output data from google
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);

        // copying output data from google (JSON or XML)
        IOUtils.copy(conn.getInputStream(), output);

        // close byte array stream
        output.close();

        // JSON string from which you can get key value pairs
        return output.toString();
    }

}
