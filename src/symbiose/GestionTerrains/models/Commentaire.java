package symbiose.GestionTerrains.models;

public class Commentaire {
    private int user_id;
    private int publication_id;
    private String contenu;
    private int productcomment_id;

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }

    public int getPublication_id(){
        return publication_id;
    }

    public void setPublication_id(int publication_id){
        this.publication_id=publication_id;
    }

    public String getContenu(){
        return contenu;
    }

    public void setContenu(String contenu){
        this.contenu=contenu;
    }

    public int getProductcomment_id(){
        return productcomment_id;
    }

    public void setProductcomment_id(int productcomment_id){
        this.productcomment_id=productcomment_id;
    }
}
