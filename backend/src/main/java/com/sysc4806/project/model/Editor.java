package com.sysc4806.project.model;

import javax.persistence.Entity;

@Entity
public class Editor extends User {

    public Editor() {}

    public Editor(String name, String username, String password) {
        super(name, username, password);
        this.setRole(Role.ROLE_EDITOR);
    }
}
