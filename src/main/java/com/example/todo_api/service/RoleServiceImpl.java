package com.example.todo_api.service;

import com.example.todo_api.exception.DataDuplicateException;
import com.example.todo_api.exception.DataNotFoundException;
import com.example.todo_api.model.dto.RoleDto;
import com.example.todo_api.model.entity.Role;
import com.example.todo_api.repository.RoleRepository;
import jdk.nashorn.internal.runtime.regexp.joni.constants.internal.TokenType;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleList;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ModelMapper modelMapper;


    //Implement CRUD methods in service to by served by controller and the backend by entity repository

    //Create(C)
    @Override
    public RoleDto create(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("RoleDto was null");
        if(roleDto.getId() != 0)throw new IllegalArgumentException("role id should be noll or zero");
        // todo: check the name that should not exist in the db....

        Role createdEntity = roleRepository.save( modelMapper.map(roleDto, Role.class));
        return modelMapper.map(createdEntity, RoleDto.class);
    }

    //Read(R)
    @Override
    public List<RoleDto> getAll() {
       List<Role>  listRole = roleRepository.findAllByOrderByIdDesc();

      /* return listRole.stream()
               .map(entity -> new RoleDto(entity.getId(),entity.getName()))
               .collect(Collectors.toList());*/
       return modelMapper.map(listRole, new TypeToken<List<RoleDto>>(){}.getType());
    }

    //Read(R)
    @Override
    public RoleDto findById(Integer roleId) {
        if (roleId == null) throw new IllegalArgumentException("Role id was null");
       Optional<Role> roleOption = roleRepository.findById(roleId);
       if (!roleOption.isPresent()) throw new DataNotFoundException("role id was not valid");

       Role entity = roleOption.get();
       return modelMapper.map(entity, RoleDto.class);

    }


    //Update(U)
    @Override
    public void update(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("The roleDto data was null");
        if (roleDto.getId() == 0) throw new IllegalArgumentException("the id does't exist");

        if(!roleRepository.findById(roleDto.getId()).isPresent())
            throw new IllegalArgumentException("data not found");

        if(roleRepository.findByName(roleDto.getName()).isPresent())
            throw new DataDuplicateException("duplicate error");

        roleRepository.save(modelMapper.map(roleDto, Role.class));
    }

    //Delete(D)
    @Override
    public void delete(Integer roleId) {
            if (roleId == null) throw new IllegalArgumentException("role id was null");
           RoleDto roleDto = findById(roleId);
           if (roleDto == null) throw new IllegalArgumentException("The data was not found");
            roleRepository.deleteById(roleId);

    }
}
