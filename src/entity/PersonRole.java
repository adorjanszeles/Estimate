package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class PersonRole implements Serializable{
    private int personRoleId;
    private String role;
    private String personNickName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getPersonRoleId() {
        return personRoleId;
    }

    public void setPersonRoleId(int personRoleId) {
        this.personRoleId = personRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPersonNickName() {
        return personNickName;
    }

    public void setPersonNickName(String personNickName) {
        this.personNickName = personNickName;
    }
}
