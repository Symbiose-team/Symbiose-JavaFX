package symbiose.GestionTerrains.models;

public class Publication {
    private String contenu;
    private double vote;

    public String getContenu(){
        return contenu;
    }

    public void setContenu(String contenu){
        this.contenu=contenu;
    }

    public double getVote(){
        return vote;
    }

    public void setVote(double vote){
        this.vote=vote;
    }
}
