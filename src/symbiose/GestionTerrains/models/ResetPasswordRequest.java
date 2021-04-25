package symbiose.GestionTerrains.models;

public class ResetPasswordRequest {
    private int user_id;
    private String selector;
    private String hashed_token;

    public int getUser_id(){
        return user_id;
    }

    public void setUser_id(int user_id){
        this.user_id=user_id;
    }

    public String getSelector(){
        return selector;
    }

    public void setSelector(String selector){
        this.selector=selector;
    }

    public String getHashed_token(){
        return hashed_token;
    }

    public void setHashed_token(String hashed_token){
        this.hashed_token=hashed_token;
    }
}
