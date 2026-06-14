package com.example.summerapp.Models;

import java.util.Objects;

public class User {
    private String nameSystem;
    private String passwordSystem;

    public User(String nameSystem, String passwordSystem) {
        this.nameSystem = nameSystem;
        this.passwordSystem = passwordSystem;
    }

    public String getNameSystem() {
        return nameSystem;
    }

    public void setNameSystem(String nameSystem) {
        this.nameSystem = nameSystem;
    }

    public String getPasswordSystem() {
        return passwordSystem;
    }

    public void setPasswordSystem(String passwordSystem) {
        this.passwordSystem = passwordSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nameSystem, user.nameSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameSystem);
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("Usuario: ").append(nameSystem).append(":").append(" Contraseña: ").append(passwordSystem).append("\n");
        return sB.toString();
    }
}
