package GestionEvents.models;

import javax.persistence.*;

@Entity
@Table(name = "role_user")
public class RoleUser {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
