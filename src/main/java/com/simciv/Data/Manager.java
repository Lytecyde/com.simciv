package com.simciv.Data;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Manager {
    static Gson gson = new Gson();
    ClassLoader classLoader = getClass().getClassLoader();
    File fileUnits = new File(
            classLoader.getResource("Units.json").getFile());
    File fileBuildings = new File(
            classLoader.getResource("Buildings.json").getFile());
    File fileAdvances = new File(
            classLoader.getResource("Advances.json").getFile());
    JsonElement jse = gson.fromJson(new FileReader(fileUnits), JsonElement.class);
    static Type type = new TypeToken<List<Map<String, Object>>>() {}.getType();
    static List<Map<String, Object>> units;
    static List<Map<String, Object>> buildings;
    static List<Map<String, Object>> advances;

    static public List<Map<String, Object>> getUnits() {
        return units;
    }

    public static Map<String, Object> getUnitData(int i) {
        return units.get(i);
    }

    public Manager() throws FileNotFoundException {
        unitData();
        advanceData();
        buildingData();
    }

    private List<Map<String, Object>> unitData() throws FileNotFoundException {
        units = gson.fromJson(new FileReader(fileUnits), type);
        //access data from the Map...
        String[] variableNames = {""};
        for (int i = 0; i < units.size(); i++) {
            variableNames = units.get(i).keySet().toArray(new String[units.get(i).size()]);
        }

        if (variableNames[0].equals("")) {
            return null;
        }
        for (int j = 0; j < units.size(); j++) {
            for (int i = 0; i < variableNames.length; i++) {
                System.out.print(variableNames[i]);
                Object[] values = units.get(j).values().toArray();
                System.out.println(values[i]);
            }
        }
        return units;
    }

    private List<Map<String, Object>> buildingData() throws FileNotFoundException {
        buildings = gson.fromJson(new FileReader(fileBuildings), type);
        return buildings;
    }

    private List<Map<String, Object>> advanceData() throws FileNotFoundException {
        advances = gson.fromJson(new FileReader(fileAdvances), type);
        return advances;
    }
}
