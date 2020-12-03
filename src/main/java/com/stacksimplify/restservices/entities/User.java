package com.stacksimplify.restservices.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname", "lastname"}) -- Static filtering Jsonignore
@JsonFilter(value = "userFilter")
public class User extends RepresentationModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Username is Mandatory field. Please provide username")
    @Column(name = "user_name", length = 50, nullable = false, unique =  true)
    private String username;

    @Size(min = 2, message = "FirsName should have atleast 2 characters")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstname;
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastname;
    @Column(name = "email_address", length = 50, nullable = false)
    private String email;
    @Column(name = "role", length = 50, nullable = false)
    private String role;
    @Column(name = "ssn", length = 50, nullable = false, unique = true)
    //@JsonIgnore -- static filtering
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Order> order;


    // CTOs
    public User(){}

    public User(Long id, String username, String firstname, String lastname, String email, String role, String ssn) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    // ToString

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
