package ru.topjava.lunchvote.model;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Антон on 28.03.2018.
 */
public class User extends NamedBaseEntity {
    private String email;
    private String password;
    private Set<Role> roles;
    private LocalDateTime registered;

    public User() {
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, LocalDateTime registered) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.registered = registered;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", registered=" + registered +
                '}';
    }
}
