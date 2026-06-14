package com.example.summerapp.Interface;

import com.example.summerapp.Models.User;

public interface UserFunctionsInterface {
    public User addUser(User user);
    public String showData();
    public boolean deleteUser(User user);
    public User editUser(User user);
    public boolean limitsForUsers();
    public boolean tutorialValue();
}
