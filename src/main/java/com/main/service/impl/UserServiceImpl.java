package com.main.service.impl;

import com.main.entity.User;
import com.main.exception.ResourceNotFoundException;
import com.main.payloads.UserDto;
import com.main.repository.UserRepository;
import com.main.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

private  final UserRepository userRepository;
private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId)
        );
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User savedUser = userRepository.save(user);


        return userToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId));

        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> collect = allUsers.stream().map(this::userToUserDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", userId));
        userRepository.delete(user);

    }
    private User  userDtoToUser(UserDto userDto)
    {
        User user = modelMapper.map(userDto, User.class);
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
        return  user;
    }
    private UserDto  userToUserDto(User user)
    {
        UserDto userdto =  modelMapper.map(user, UserDto.class);
//        userdto.setUserId(user.getUserId());
//        userdto.setName(user.getName());
//        userdto.setEmail(user.getEmail());
//        userdto.setPassword(user.getPassword());
        return  userdto;
    }
}
