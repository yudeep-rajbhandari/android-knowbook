package com.example.user.volleyjson;

/**
 * Created by USER on 2/4/2017.
 */

public class Routinedisplay {

    private String subjectname,subjectcode,faculty,semester,startingtime,endingtime,teacher,day;

    public String getStartingtime() {
        return this.startingtime;
    }

    public void setStartingtime(String startingtime) {
        this.startingtime = startingtime;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
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


    public String getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(String endingtime) {
        this.endingtime = endingtime;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Routinedisplay(String faculty, String semester, String subjectname, String subjectcode, String day, String startingtime, String endingtime, String teacher){
            this.setDay(day);
        this.setFaculty(faculty);

        this.setSemester(semester);
        this.setEndingtime(endingtime);
        this.setStartingtime(startingtime);
        this.setSubjectcode(subjectcode);
        this.setSubjectname(subjectname);
        this.setTeacher(teacher);
    }


}
