package com.example.summerapp.Interface;

import com.example.summerapp.Models.User;

public interface UserFunctionsInterface {
    public boolean addUser(User user);
    public String showData();
    public boolean deleteUser(User name);
    public User updateUser(User user);
}
