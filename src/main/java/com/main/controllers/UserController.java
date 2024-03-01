package com.main.controllers;

import com.main.entity.User;
import com.main.payloads.UserDto;
import com.main.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    getall user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>  findAllUser(){
        List<UserDto> allUser = userService.getAllUser();
        return  new ResponseEntity<>(allUser, HttpStatus.OK);
    }
//    getSingle user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id)
    {
        UserDto userById = userService.getUserById(id);
        return  new ResponseEntity<>(userById, HttpStatus.OK);
    }

//    create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto user = userService.createUser(userDto);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

//    update
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto)
    {
        UserDto user = userService.updateUser(userDto, userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    //    update
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
        return  new ResponseEntity<>( HttpStatus.OK);
    }

}
