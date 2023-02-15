package com.example.todo_api.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleDto {
    private int id;

    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, max = 40, message = "name length should be between 2-40")
    private String name;

}
