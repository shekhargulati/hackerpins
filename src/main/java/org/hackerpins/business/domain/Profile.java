package org.hackerpins.business.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by shekhargulati on 18/04/14.
 */
@Entity
@Table(indexes = {
        @Index(unique = true, columnList = "email"),
        @Index(unique = true, columnList = "username")
})
@NamedQueries({
        @NamedQuery(name = "Profile.findByEmail", query = "SELECT new Profile(p.id, p.username,p.email,p.fullname) from Profile p where p.email =:email"),
        @NamedQuery(name = "Profile.findByUsernameOrEmailAndPassword", query = "SELECT new Profile(p.id, p.username,p.email,p.fullname) from Profile p where p.username =:username OR p.email =:username AND p.password =:password")
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

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredOn = new Date();

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public Profile() {
    }

    public Profile(Long id, String username, String email, String fullname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullname = fullname;
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

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (email != null ? !email.equals(profile.email) : profile.email != null) return false;
        if (fullname != null ? !fullname.equals(profile.fullname) : profile.fullname != null) return false;
        if (id != null ? !id.equals(profile.id) : profile.id != null) return false;
        if (username != null ? !username.equals(profile.username) : profile.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        return result;
    }
}
