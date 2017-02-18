package com.example.user.volleyjson;

/**
 * Created by USER on 2/11/2017.
 */

public class Getprofile {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    private String name="";
    private String id=null;
    private String faculty="";
    private String semester="";



    public Getprofile(String id, String name, String faculty, String semester){
        this.setFaculty(faculty);
        this.setId(id);
        this.setName(name);
        this.setSemester(semester);
    }

    public Getprofile() {
    }
}
