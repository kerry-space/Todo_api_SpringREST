package com.example.todo_api.controller;

import com.example.todo_api.model.dto.UserDto;
import com.example.todo_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")

@Validated
public class UserController {


    //Controller is responsible for every request from outside and inside controller layer
    //method
    //http://localhost:8080/api/v1/user
    // @GetMapping: Fetch this method to read data from server
    //ResponseEntity<body> convert list of User to JSON method and send to client, message body

    @Autowired
    UserService userService;


    //our CRUD, fetch from https requests CRUD methods from frond end give to service layer

    //Create(C)
    //@RequestMapping(path = "/", method = RequestMethod.POST) old annotation
    @PostMapping("/")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto dto){
        if (dto == null)throw new IllegalArgumentException("The param was null");
        System.out.println("USERNAME: "+ dto.getUsername());
        UserDto serviceResult = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }


    //Read(R)
    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> findByUsername(@PathVariable("username") @NotEmpty @Size(min = 4, max = 50) String username){
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    //Update(U)
    @PutMapping("/disable")
    public ResponseEntity<Void> disableUserByUsername(@RequestParam("username")  @NotEmpty @Size(min = 4, max = 50)String username){
        userService.disableUserByUsername(username);
        return ResponseEntity.noContent().build();
    }


}
