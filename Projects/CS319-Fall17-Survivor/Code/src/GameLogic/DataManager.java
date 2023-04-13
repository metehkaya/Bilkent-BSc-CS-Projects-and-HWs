package GameLogic;

import Components.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class DataManager {

    private String path;

    public DataManager() throws IOException {
        path = "C:/Users/" + System.getProperty("user.name") + "/Documents/Survivor/";
        if (!makeDirectory()) {
            saveText(path + "scoreboard.txt", "0;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1;-1");
        }
    }

    public void saveText(String filePath, String text) throws IOException {
        makeDirectory();
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public void setScore(int idLevel, int score) throws IOException {

        StringBuilder outputScores = new StringBuilder();
        String[] inputScores = getText(path + "scoreboard.txt").split(";");

        ArrayList<Integer> values = new ArrayList<>();
        for (String str : inputScores) {
            values.add(Integer.parseInt(str));
        }

        values.set(0, Math.max(idLevel, values.get(0)));
        values.set(idLevel, Math.max(score, values.get(idLevel)));

        outputScores.append(values.get(0));
        for (int i = 1; i <= 12; i++) {
            outputScores.append(";" + values.get(i));
        }

        saveText(path + "scoreboard.txt", outputScores.toString());

    }

    public ArrayList<Integer> getScores() throws IOException {

        String[] inputScores = getText(path + "scoreboard.txt").split(";");

        ArrayList<Integer> values = new ArrayList<>();
        for (String str : inputScores) {
            values.add(Integer.parseInt(str));
        }

        return values;

    }

    public boolean makeDirectory() {
        File file = new File(path);
        if (!(file.exists() && file.isDirectory())) {
            new File(path).mkdirs();
            return false;
        }
        return true;
    }

    public String getText(String textFile) throws FileNotFoundException, IOException {

        FileReader fr = new FileReader(textFile);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer sb = new StringBuffer();

        String str = br.readLine();
        while (str != null) {
            sb.append(str);
            str = br.readLine();
        }

        fr.close();
        br.close();

        return sb.toString();

    }

    public ArrayList<Integer> getLimits(String path) throws IOException {

        int timeLimit, resourceLimit;
        ArrayList<Integer> limits = new ArrayList<Integer>();

        String str;
        String file = getText(path);

        String[] objects = file.split(";");

        timeLimit = Integer.parseInt(objects[0]);
        resourceLimit = Integer.parseInt(objects[1]);

        limits.add(timeLimit);
        limits.add(resourceLimit);

        return limits;

    }

    public ArrayList<StaticGameObject> getStaticObjects(String path) throws IOException {

        int id, x, y, w, h;

        String file, source;
        String[] object, objects;

        ArrayList<StaticGameObject> objectList = new ArrayList<StaticGameObject>();

        file = getText(path);
        objects = file.split(";");

        for (String str : objects) {
            object = str.split(",");
            id = Integer.parseInt(object[0]);
            x = Integer.parseInt(object[1]);
            y = Integer.parseInt(object[2]);
            w = Integer.parseInt(object[3]);
            h = Integer.parseInt(object[4]);
            source = object[5];
            objectList.add(new StaticGameObject(id, x, y, w, h, source));
        }

        return objectList;

    }

    public ArrayList<DynamicGameObject> getDynamicObjects(String path) throws IOException {

        int id, x, y, w, h, dirX, dirY, period;

        String file, source;
        String[] object, objects;

        ArrayList<DynamicGameObject> objectList = new ArrayList<DynamicGameObject>();

        file = getText(path);
        objects = file.split(";");

        for (String str : objects) {
            object = str.split(",");
            id = Integer.parseInt(object[0]);
            x = Integer.parseInt(object[1]);
            y = Integer.parseInt(object[2]);
            w = Integer.parseInt(object[3]);
            h = Integer.parseInt(object[4]);
            source = object[5];
            dirX = Integer.parseInt(object[6]);
            dirY = Integer.parseInt(object[7]);
            period = Integer.parseInt(object[8]);
            objectList.add(new DynamicGameObject(id, x, y, w, h, source, dirX, dirY, period));
        }

        return objectList;

    }

}