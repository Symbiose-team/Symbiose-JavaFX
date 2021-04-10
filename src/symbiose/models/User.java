package symbiose.models;

public class User {
    private String first_name;
    private String last_name;
    private String email;
    private String picture;
    private String hash;
    private int cin;
    private java.util.Date birthday;
    private String slug;
    private String role;
    private String adresse;
    private int phone_number;
    private String registration_token;
    private String forgot_password_token;

    public String getFirst_name(){
        return first_name;
    }

    public void setFirst_name(String first_name){
        this.first_name=first_name;
    }

    public String getLast_name(){
        return last_name;
    }

    public void setLast_name(String last_name){
        this.last_name=last_name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPicture(){
        return picture;
    }

    public void setPicture(String picture){
        this.picture=picture;
    }

    public String getHash(){
        return hash;
    }

    public void setHash(String hash){
        this.hash=hash;
    }

    public int getCin(){
        return cin;
    }

    public void setCin(int cin){
        this.cin=cin;
    }

    public java.util.Date getBirthday(){
        return birthday;
    }

    public void setBirthday(java.util.Date birthday){
        this.birthday=birthday;
    }

    public String getSlug(){
        return slug;
    }

    public void setSlug(String slug){
        this.slug=slug;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role=role;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String adresse){
        this.adresse=adresse;
    }

    public int getPhone_number(){
        return phone_number;
    }

    public void setPhone_number(int phone_number){
        this.phone_number=phone_number;
    }

    public String getRegistration_token(){
        return registration_token;
    }

    public void setRegistration_token(String registration_token){
        this.registration_token=registration_token;
    }

    public String getForgot_password_token(){
        return forgot_password_token;
    }

    public void setForgot_password_token(String forgot_password_token){
        this.forgot_password_token=forgot_password_token;
    }
}
