package symbiose.GestionTerrains.models;

public class Notification {
    private int user_id;
    private int game_id;
    private int joined_by_id;
    private String discr;

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }

    public int getGame_id(){
        return game_id;
    }

    public void setGame_id(int game_id){
        this.game_id=game_id;
    }

    public int getJoined_by_id(){
        return joined_by_id;
    }

    public void setJoined_by_id(int joined_by_id){
        this.joined_by_id=joined_by_id;
    }

    public String getDiscr(){
        return discr;
    }

    public void setDiscr(String discr){
        this.discr=discr;
    }
}
