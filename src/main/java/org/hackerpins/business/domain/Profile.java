package org.hackerpins.business.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Entity
@Table(indexes = {
        @Index(unique = true, columnList = "email"),
        @Index(unique = true, columnList = "username")
})
@NamedQueries({
        @NamedQuery(name = "Profile.findByEmail", query = "SELECT new Profile(p.username,p.email,p.fullname) from Profile p where p.email =:email"),
        @NamedQuery(name= "Profile.findByUsernameOrEmailAndPassword",query = "SELECT new Profile(p.username,p.email,p.fullname) from Profile p where p.username =:username OR p.email =:username AND p.password =:password")
})
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 20, message = "password should be between {min} and {max}")
    private String password;

    @NotNull
    private String fullname;

    public Profile() {
    }

    public Profile(String username, String email, String fullname) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
