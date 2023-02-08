package com.example.todo_api.model.entity;

import com.example.todo_api.exception.DataDuplicateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;

   private boolean expired;

   @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
   @JoinTable(name = "user_roles",
           joinColumns =  {@JoinColumn(name = "USER_ID")},
           inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
   )
   private Set<Role> roles = new HashSet<>();



   //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //helper methods
    public void addRole(Role role){
        if (roles.contains(role)){
            throw new DataDuplicateException("Duplicate error");
        }
        roles.add(role);
    }


    public void removeRole(Role role){
        if(!roles.contains(role)){
            throw new DataDuplicateException("Not found error");
        }
        roles.remove(role);
    }

}
