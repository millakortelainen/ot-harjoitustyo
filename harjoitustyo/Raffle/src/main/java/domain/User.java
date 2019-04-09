/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

public class User {

    private int id;
    private String username;
    private boolean isModerator;

    public User(int id, String username, boolean isModerator) {
        this.id = id;
        this.username = username;
        this.isModerator = isModerator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIsModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }

    public void setUsername(String username) {
        this.username = username;
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
