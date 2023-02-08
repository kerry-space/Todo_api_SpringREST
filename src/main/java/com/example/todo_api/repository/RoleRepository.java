package com.example.todo_api.repository;

import com.example.todo_api.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(String name);

    List<Role> findAllByOrderByIdDesc();
}
