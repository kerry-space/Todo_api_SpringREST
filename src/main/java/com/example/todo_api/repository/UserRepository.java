package com.example.todo_api.repository;

import com.example.todo_api.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Modifying
    @Query("update User u set u.expired = :expired where u.username = :username")
    void  updateExpiredByUsername(@Param("username") String username, @Param("expired") boolean expired);

    @Modifying
    @Query("update User u set u.password = :newPassword where u.username = :username")
    void resetPassword(@Param("username") String username, @Param("newPassword") String newPassword);

}
