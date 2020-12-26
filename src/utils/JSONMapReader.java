package src.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator; 
import java.util.Map; 
  
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
  
public class JSONMapReader  
{

    public static void main(String[] args) throws Exception {   readFile(); }
    public static void readFile() throws Exception  
    { 

        String path = new String("data\\tiles\\map01.json");


        try (FileReader reader = new FileReader(path)) {

            // parsing file "JSONExample.json" 
            Object fileObject = new JSONParser().parse(reader); 
            
            // typecasting obj to JSONObject 
            JSONObject obj = (JSONObject) fileObject; 
            
            // getting width and height
            int width = ((Long) obj.get("width")).intValue(); 
            int height = ((Long) obj.get("height")).intValue(); 

            
            System.out.println("\nMap Size: " + width + ", " + height); 

            //System.out.println("\nObject: " + obj);
            
            JSONArray tilesets = (JSONArray) obj.get("tilesets");

            System.out.println("\nTilesets:\n\n" + tilesets);

            Iterator itr = tilesets.iterator();

            while (itr.hasNext()) {
                mapItr = ((Map) itr.next()).entrySet().iterator();
                while ()
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*// getting age 
        long age = (long) jo.get("age"); 
        System.out.println(age); 
          
        // getting address 
        Map address = ((Map)jo.get("address")); 
          
        // iterating address Map 
        Iterator<Map.Entry> itr1 = address.entrySet().iterator(); 
        while (itr1.hasNext()) { 
            Map.Entry pair = itr1.next(); 
            System.out.println(pair.getKey() + " : " + pair.getValue()); 
        } 
          
        // getting phoneNumbers 
        JSONArray ja = (JSONArray) jo.get("phoneNumbers"); 
          
        // iterating phoneNumbers 
        Iterator itr2 = ja.iterator(); 
          
        while (itr2.hasNext())  
        { 
            itr1 = ((Map) itr2.next()).entrySet().iterator(); 
            while (itr1.hasNext()) { 
                Map.Entry pair = itr1.next(); 
                System.out.println(pair.getKey() + " : " + pair.getValue()); 
            } 
        }*/
    } 
}