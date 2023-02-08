package com.example.todo_api.controller;

import com.example.todo_api.model.dto.RoleDto;
import com.example.todo_api.model.entity.Role;
import com.example.todo_api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    //method
    //http://localhost:8080/api/v1/role
    // @GetMapping: Fetch this method to read data from server
    //ResponseEntity<body> convert list of Role to JSON method and send to client, message body

    @Autowired
    RoleService roleService;


    //our CRUD, fetch from https requests CRUD methods from frond end give to service layer

    //Create(C)
    @PostMapping("/")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto dto){
        RoleDto createdRoleDto = roleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoleDto); // 201
    }


    //Read(R)
    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAll(){
       //return ResponseEntity.ok(roleService.getAll());//200
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());

    }

    //Read(R)
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable("id") Integer id){

        return ResponseEntity.ok(roleService.findById(id));
    }



    //Upate(U)
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody RoleDto dto){
        roleService.update(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //Delete(D)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        roleService.delete(id);
        //return ResponseEntity.noContent().build();//204
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
