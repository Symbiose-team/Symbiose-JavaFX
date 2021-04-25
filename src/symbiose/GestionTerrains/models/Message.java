package symbiose.GestionTerrains.models;

public class Message {
    private int conversation_id;
    private int user_id;
    private String contenu;

    public int getConversation_id(){
        return conversation_id;
    }

    public void setConversation_id(int conversation_id){
        this.conversation_id=conversation_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }

    public String getContenu(){
        return contenu;
    }

    public void setContenu(String contenu){
        this.contenu=contenu;
    }
}
