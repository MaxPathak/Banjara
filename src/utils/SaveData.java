package src.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import src.Handler;
import src.global.Global;
import src.states.GameState;

public class SaveData {

    public static void save(Handler handler) {
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(handler.getGame().gameState);
        data.add(Global.variables);
        data.add(Global.switches);

        try {
            File file = new File("save/banjara.sav");

            // create file if not exists
            file.createNewFile();

            // create a writer
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream writer = new ObjectOutputStream(fos);

            // write data to file
            writer.writeObject(data);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static boolean load(Handler handler) {
        ArrayList<Object> data = new ArrayList<Object>();

        try {

            File file = new File("save/banjara.sav");

            // return false if file does not exist
            if (!file.exists()) {
                return false;
            }

            // create a reader
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream reader = new ObjectInputStream(fis);

            // read data from file
            data = (ArrayList<Object>) reader.readObject();

            // close the reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        handler.getGame().gameState = (GameState) data.get(0);
        Global.variables = (int[]) data.get(1);
        Global.switches = (boolean[]) data.get(2);

        return true;

    }

}
