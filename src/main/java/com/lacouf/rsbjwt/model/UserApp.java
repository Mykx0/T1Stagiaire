package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Credentials;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @Embedded
    private Credentials credentials;

    protected UserApp() {
    }

    protected UserApp(String firstName, String lastName, Credentials credentials) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return credentials.getEmail();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getPassword() {
        return credentials.getPassword();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return credentials.getAuthorities();
    }
}