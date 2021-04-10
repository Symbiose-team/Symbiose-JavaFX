package models;

import javax.persistence.*;

@Entity
@Table(name = "reset_password_request")
public class ResetPasswordRequest {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "selector")
    private String selector;

    @Column(name = "hashed_token")
    private String hashedToken;

    @Column(name = "requested_at")
    private java.sql.Timestamp requestedAt;

    @Column(name = "expires_at")
    private java.sql.Timestamp expiresAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSelector() {
        return this.selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getHashedToken() {
        return this.hashedToken;
    }

    public void setHashedToken(String hashedToken) {
        this.hashedToken = hashedToken;
    }

    public java.sql.Timestamp getRequestedAt() {
        return this.requestedAt;
    }

    public void setRequestedAt(java.sql.Timestamp requestedAt) {
        this.requestedAt = requestedAt;
    }

    public java.sql.Timestamp getExpiresAt() {
        return this.expiresAt;
    }

    public void setExpiresAt(java.sql.Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }
}
