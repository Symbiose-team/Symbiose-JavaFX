package symbiose.GestionTerrains.models;

public class Game {
    private int user_id;
    private String name;

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
}
