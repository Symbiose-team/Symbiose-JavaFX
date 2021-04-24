

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
public class Conversation {
    int id;
    User user1;
    User user2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Conversation(int id, User user1, User user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    public Conversation() {
    }

    @Override
    public String toString() {
        return "Conversation{" + "id=" + id + ", user1=" + user1 + ", user2=" + user2 + '}';
    }
    
    
}
