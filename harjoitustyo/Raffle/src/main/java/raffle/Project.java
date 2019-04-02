/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffle;

/**
 *
 * @author kortemil
 */
public class Project {

    private Integer id;
    private String subject;
    private String description;
    private Integer projectCategoryId;

    public Project(Integer id, String subject, String description, Integer projectCategoryId) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.projectCategoryId = projectCategoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    public void setProjectCategoryId(Integer projectCategoryId) {
        this.projectCategoryId = projectCategoryId;
    }

    @Override
    public String toString() {
        return this.subject;
    }

}