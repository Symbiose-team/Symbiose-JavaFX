package symbiose.GestionTerrains.models;

public class Client {
    private int party_id;
    private String username;

    public int getParty_id(){
        return party_id;
    }

    public void setParty_id(int party_id){
        this.party_id=party_id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }
}
