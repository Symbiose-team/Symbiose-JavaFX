package symbiose.models;

public class Lobby {
    private String name;
    private String owner;
    private int nbplayers;
    private int maxplayers;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner=owner;
    }

    public int getNbplayers(){
        return nbplayers;
    }

    public void setNbplayers(int nbplayers){
        this.nbplayers=nbplayers;
    }

    public int getMaxplayers(){
        return maxplayers;
    }

    public void setMaxplayers(int maxplayers){
        this.maxplayers=maxplayers;
    }
}
