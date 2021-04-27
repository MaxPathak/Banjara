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
import src.entities.creatures.Player;
import src.gfx.ImageLoader;
import src.gfx.SpriteSheet;
import src.maps.Map;
import src.tiles.Layer;
import src.tiles.TileSet;

public class JSONMapReader {

    public static Map loadMap(Handler handler, Player player, String path) {

        // String path = new String("data\\tiles\\map01.json");

        Map map;

        try (FileReader reader = new FileReader(String.format("data/tiles/%s.json", path))) {

            // parsing file "JSONExample.json"
            Object fileObject = new JSONParser().parse(reader);

            // typecasting obj to JSONObject
            JSONObject obj = (JSONObject) fileObject;

            // getting width and height
            int width = ((Long) obj.get("width")).intValue();
            int height = ((Long) obj.get("height")).intValue();

            map = new Map(handler, player, path, width, height);

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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void loadEvents() {
        // TODO
    }

}