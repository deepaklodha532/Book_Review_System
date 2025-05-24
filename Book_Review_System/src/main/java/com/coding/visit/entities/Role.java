package com.coding.visit.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String roleId;
    private String name;

    @ManyToMany(mappedBy = "roles" )
    private Set<User> users = new HashSet<>() ;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
