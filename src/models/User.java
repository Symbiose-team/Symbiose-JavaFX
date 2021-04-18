package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "picture")
    private String picture;

    @Column(name = "hash")
    private String hash;

    @Column(name = "cin")
    private Integer cin;

    @Column(name = "birthday")
    private java.sql.Date birthday;

    @Column(name = "slug")
    private String slug;

    @Column(name = "role")
    private String role;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "registered_at")
    private java.sql.Timestamp registeredAt;

    @Column(name = "account_must_be_verified_before")
    private java.sql.Timestamp accountMustBeVerifiedBefore;

    @Column(name = "registration_token")
    private String registrationToken;

    @Column(name = "is_verified")
    private Byte isVerified;

    @Column(name = "account_verified_at")
    private java.sql.Timestamp accountVerifiedAt;

    @Column(name = "forgot_password_token")
    private String forgotPasswordToken;

    @Column(name = "forgot_password_token_requested_at")
    private java.sql.Timestamp forgotPasswordTokenRequestedAt;

    @Column(name = "forgot_password_token_must_be_verified_before")
    private java.sql.Timestamp forgotPasswordTokenMustBeVerifiedBefore;

    @Column(name = "forgot_password_token_verified_at")
    private java.sql.Timestamp forgotPasswordTokenVerifiedAt;

    @Column(name = "is_enabled")
    private Byte isEnabled;

    public User(Integer id, String firstName, String lastName, String email, Integer cin, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cin = cin;
        this.birthday = birthday;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getCin() {
        return this.cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }

    public java.sql.Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public java.sql.Timestamp getRegisteredAt() {
        return this.registeredAt;
    }

    public void setRegisteredAt(java.sql.Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public java.sql.Timestamp getAccountMustBeVerifiedBefore() {
        return this.accountMustBeVerifiedBefore;
    }

    public void setAccountMustBeVerifiedBefore(java.sql.Timestamp accountMustBeVerifiedBefore) {
        this.accountMustBeVerifiedBefore = accountMustBeVerifiedBefore;
    }

    public String getRegistrationToken() {
        return this.registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public Byte getIsVerified() {
        return this.isVerified;
    }

    public void setIsVerified(Byte isVerified) {
        this.isVerified = isVerified;
    }

    public java.sql.Timestamp getAccountVerifiedAt() {
        return this.accountVerifiedAt;
    }

    public void setAccountVerifiedAt(java.sql.Timestamp accountVerifiedAt) {
        this.accountVerifiedAt = accountVerifiedAt;
    }

    public String getForgotPasswordToken() {
        return this.forgotPasswordToken;
    }

    public void setForgotPasswordToken(String forgotPasswordToken) {
        this.forgotPasswordToken = forgotPasswordToken;
    }

    public java.sql.Timestamp getForgotPasswordTokenRequestedAt() {
        return this.forgotPasswordTokenRequestedAt;
    }

    public void setForgotPasswordTokenRequestedAt(java.sql.Timestamp forgotPasswordTokenRequestedAt) {
        this.forgotPasswordTokenRequestedAt = forgotPasswordTokenRequestedAt;
    }

    public java.sql.Timestamp getForgotPasswordTokenMustBeVerifiedBefore() {
        return this.forgotPasswordTokenMustBeVerifiedBefore;
    }

    public void setForgotPasswordTokenMustBeVerifiedBefore(java.sql.Timestamp forgotPasswordTokenMustBeVerifiedBefore) {
        this.forgotPasswordTokenMustBeVerifiedBefore = forgotPasswordTokenMustBeVerifiedBefore;
    }

    public java.sql.Timestamp getForgotPasswordTokenVerifiedAt() {
        return this.forgotPasswordTokenVerifiedAt;
    }

    public void setForgotPasswordTokenVerifiedAt(java.sql.Timestamp forgotPasswordTokenVerifiedAt) {
        this.forgotPasswordTokenVerifiedAt = forgotPasswordTokenVerifiedAt;
    }

    public Byte getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Byte isEnabled) {
        this.isEnabled = isEnabled;
    }
}
