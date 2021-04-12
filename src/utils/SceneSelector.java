package utils;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SceneSelector {

    // this class basically switch screens
    // without having to type same thing again and again

    // a key that is name, and its pane
    private static Map<String, Pane> screenMap;
    // the main stage
    private static Scene mainScene;

// constructor setting main stage
    public SceneSelector(Scene scene) {
        mainScene = scene;
        screenMap = new HashMap<>();
    }

    // adds stage to map
    public void addScreen(String name,Pane pane) {
        screenMap.put(name,pane);
    }

    // removes stage from map
    public void removeScreen(String name) {
        screenMap.remove(name);
    }

    // switches screen by changing root with supplied stage name
    public static void switchScreen(String name) {
        mainScene.setRoot(screenMap.get(name));
    }

    // sets width of main stage
    public static void setWidth(double width) {
        mainScene.getWindow().setWidth(width);
    }

    public static void setHight(double hight) {
        mainScene.getWindow().setHeight(hight);
    }


}
