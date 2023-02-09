package com.example.todo_api.controller;

import com.example.todo_api.model.dto.RoleDto;
import com.example.todo_api.model.entity.Role;
import com.example.todo_api.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    //Controller is responsible for every request from outside and inside controller layer
    //method
    //http://localhost:8080/api/v1/role
    // @GetMapping: Fetch this method to read data from server
    //ResponseEntity<body> convert list of Role to JSON method and send to client, message body


    @Autowired
    RoleService roleService;


    //our CRUD, fetch from https requests CRUD methods from frond end give to service layer

    //Create(C)
    @Operation(summary = "Create a Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created the role",
                    content = {@Content(mediaType = "application/json", schema = @Schema(name = "Example", implementation = RoleDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = {@Content})
    })
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
    @Operation(summary = "Get a role by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the role", content = {@Content}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = {@Content})
    })
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
