package src.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import src.Handler;
import src.gfx.ImageLoader;
import src.gfx.SpriteSheet;
import src.maps.Map;
import src.tiles.Layer;
import src.tiles.TileSet;

public class JSONMapReader {

    // public static void main(String[] args) throws Exception {
    // readFile();
    // }

    public static Map loadMap(Handler handler, String path) {

        // String path = new String("data\\tiles\\map01.json");

        Map map;

        try (FileReader reader = new FileReader(path)) {

            // parsing file "JSONExample.json"
            Object fileObject = new JSONParser().parse(reader);

            // typecasting obj to JSONObject
            JSONObject obj = (JSONObject) fileObject;

            // getting width and height
            int width = ((Long) obj.get("width")).intValue();
            int height = ((Long) obj.get("height")).intValue();

            map = new Map(handler, width, height, 0, 0);

            // System.out.println("\nMap Size: " + map.getWidth() + ", " + map.getHeight());

            // System.out.println("\nTilesets:\n");
            ArrayList<JSONObject> tilesets = new ArrayList<JSONObject>();
            for (Object o : ((JSONArray) obj.get("tilesets")).toArray()) {
                tilesets.add((JSONObject) o);
            }
            // *temp
            ArrayList<String> tilesetNames = new ArrayList<String>();

            for (JSONObject o : tilesets) {
                if (((String) o.get("name")).equals("Regions")) {
                    map.regionIndex = ((Long) o.get("firstgid")).intValue();
                    // System.out.println("Region Index: " + map.regionIndex);
                } else {
                    String p = (String) o.get("image");
                    String[] split = p.split("\\.\\.");
                    int len = split.length;
                    p = split[len - 1];

                    tilesetNames.add(p);
                    map.tileSets.add(new TileSet(new SpriteSheet(ImageLoader.loadImage(p)),
                            ((Long) o.get("firstgid")).intValue(), ((Long) o.get("tilecount")).intValue()));

                    // System.out.println(p + ", " + o.get("firstgid"));
                }
            }

            ArrayList<JSONObject> layers = new ArrayList<JSONObject>();
            for (Object o : (JSONArray) obj.get("layers")) {
                layers.add((JSONObject) o);
            }
            int index = 0;
            for (JSONObject o : layers) {
                JSONArray a = (JSONArray) o.get("data");
                if (a == null)
                    continue;
                int[] data = new int[a.size()];
                for (int i = 0; i < a.size(); i++) {
                    data[i] = ((Long) a.get(i)).intValue();
                }
                if (((String) o.get("name")).equals("regions"))
                    map.regionLayer = index;
                map.layers.add(new Layer((String) o.get("name"), ((Long) o.get("width")).intValue(),
                        ((Long) o.get("height")).intValue(), data));

                index++;
            }

            return map;
            // Iterator itr = tilesets.iterator();

            /*
             * while (itr.hasNext()) { mapItr = ((Map) itr.next()).entrySet().iterator();
             * while () }
             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
         * // getting age long age = (long) jo.get("age"); System.out.println(age);
         * 
         * // getting address Map address = ((Map)jo.get("address"));
         * 
         * // iterating address Map Iterator<Map.Entry> itr1 =
         * address.entrySet().iterator(); while (itr1.hasNext()) { Map.Entry pair =
         * itr1.next(); System.out.println(pair.getKey() + " : " + pair.getValue()); }
         * 
         * // getting phoneNumbers JSONArray ja = (JSONArray) jo.get("phoneNumbers");
         * 
         * // iterating phoneNumbers Iterator itr2 = ja.iterator();
         * 
         * while (itr2.hasNext()) { itr1 = ((Map) itr2.next()).entrySet().iterator();
         * while (itr1.hasNext()) { Map.Entry pair = itr1.next();
         * System.out.println(pair.getKey() + " : " + pair.getValue()); } }
         */
        return null;
    }
}