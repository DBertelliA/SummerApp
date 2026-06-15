package com.example.summerapp.Interface;

import com.example.summerapp.Models.User;

public interface UserFunctionsInterface {
    public User addUser(User user);
    public String showData();
    public boolean deleteUser(String name);
    public User editUser(String user);
    public boolean limitsForUsers();
    public boolean tutorialValue(boolean tutorial);
}
