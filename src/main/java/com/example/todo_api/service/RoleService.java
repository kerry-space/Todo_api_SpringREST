package com.example.todo_api.service;

import com.example.todo_api.model.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto findById(Integer id);

    RoleDto create(RoleDto roleDto);

    void update(RoleDto roleDto);

    void delete(Integer roleId);
}
