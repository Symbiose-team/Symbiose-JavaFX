/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package symbiose.GestionCommunication.Entities;

/**
 *
 * @author medal
 */
public class Message {
    int id;
    int conversation_id;
    User user;
    String contenu;
    String username;

  
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Message() {
    }
  public Message(int id, int conversation_id, User user, String contenu, String username) {
        this.id = id;
        this.conversation_id = conversation_id;
        this.user = user;
        this.contenu = contenu;
        this.username = username;
    }
    public Message(int id, int conversation_id, User user, String contenu) {
        this.id = id;
        this.conversation_id = conversation_id;
        this.user = user;
        this.contenu = contenu;
    }
     @Override
    public String toString() {
        return "Message sent by:"+ username + "\n" + contenu +"\n" ;
    }
}
