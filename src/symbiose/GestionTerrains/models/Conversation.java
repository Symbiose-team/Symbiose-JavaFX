package symbiose.GestionTerrains.models;

public class Conversation {
    private int user1_id;
    private int user2_id;

    public int getUser1_id(){
        return user1_id;
    }

    public void setUser1_id(int user1_id){
        this.user1_id=user1_id;
    }

    public int getUser2_id(){
        return user2_id;
    }

    public void setUser2_id(int user2_id){
        this.user2_id=user2_id;
    }
}
