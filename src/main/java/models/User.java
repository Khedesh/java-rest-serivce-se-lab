package models;

import javax.persistence.*;

/**
 * Created by Nader on 29/04/2018.
 */
@Entity
@Table(name="users")
public class User {

    public User(String email) {
        this.email = email;
    }

    @Id private String email;

    protected User() {
        //for JPA
    }

    public String getEmail() {
        return email;
    }
}
