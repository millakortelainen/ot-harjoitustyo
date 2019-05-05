/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;

public class User {

    private String username;
    private boolean isModerator;

    public User(String username, boolean isModerator) {

        this.username = username;
        this.isModerator = isModerator;
    }
    
    public String getUsername() {
        return username;
    }

    public boolean isModerator() {
        return isModerator;
    }

    @Override
    public String toString() {
        return this.username;
    }

}
