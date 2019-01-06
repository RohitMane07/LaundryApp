package com.xbiztechventures.lapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Webservice {

    public String registerUser(String action, String mobile , String username , String address, String landmark, String pincode, String email){
        try{

            URL url = new URL("http://androidtestapp.dx.am/service.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String data = URLEncoder.encode("action","UTF-8")+"="+URLEncoder.encode(action,"UTF-8")+"&"+
                    URLEncoder.encode("mobile_no","UTF-8") +"="+ URLEncoder.encode(mobile,"UTF-8")+"&"+
                    URLEncoder.encode("customer_name","UTF-8" )+"="+ URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("address","UTF-8") +"=" +URLEncoder.encode(address,"UTF-8")+"&"+
                    URLEncoder.encode("landmark","UTF-8") +"="+ URLEncoder.encode(landmark,"UTF-8")+"&"+
                    URLEncoder.encode("pincode","UTF-8") + "=" +URLEncoder.encode(pincode,"UTF-8")+"&"+
                    URLEncoder.encode("email","UTF-8") + "=" +URLEncoder.encode(email,"UTF-8");

            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line ="";
            while ((line = reader.readLine())!=null ){
                response = response + line;
            }

            return response;
        }
        catch (MalformedURLException m){
            m.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public String checkIMEI(String action, String IMEI){

        try {
            URL url = new URL("http://androidtestapp.dx.am/service.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(outputStream , "UTF-8" ));
            String param = URLEncoder.encode("action","UTF-8") + "=" + URLEncoder.encode(action,"UTF-8")+"&"+
                    URLEncoder.encode("IMEI_NO","UTF-8") + "=" + URLEncoder.encode(IMEI,"UTF-8");

            writer.write(param);
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line ="";
            while ((line = reader.readLine())!=null ){
                response = response + line;
            }

            return response;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public String getAllOrders(String action, String username, String mobileNo){
        String response="",line="";


        try {
            URL url = new URL("http://androidtestapp.dx.am/service.php");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data = URLEncoder.encode("action","UTF-8")+ "=" + URLEncoder.encode(action,"UTF-8")+"&"+
                    URLEncoder.encode("customerName","UTF-8")+ "=" + URLEncoder.encode(username,"UTF-8")+"&"+
                    URLEncoder.encode("mobile_no","UTF-8")+ "=" + URLEncoder.encode(mobileNo,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            while ((line = reader.readLine())!=null ){
                response = response + line;
            }
            return response;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public String registerOrNot(String IMEI){
        return  null;
    }

}
