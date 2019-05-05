/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public Integer getProjectCategoryId() {
        return projectCategoryId;
    }

    @Override
    public String toString() {
        return this.subject;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.projectCategoryId, other.projectCategoryId)) {
            return false;
        }
        return true;
    }

}
