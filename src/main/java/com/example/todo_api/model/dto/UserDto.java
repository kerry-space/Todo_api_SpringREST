package com.example.todo_api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UserDto {

    @NotEmpty
    @Size(min = 4, max = 50)
    private String username;

    @Size(min = 6, max = 50)
    private String password;

    @NotNull
    @Valid
    private List<RoleDto> roles;
}
