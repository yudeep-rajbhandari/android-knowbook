package com.example.user.volleyjson;

/**
 * Created by USER on 1/30/2017.
 */

public class Subjectdisplay {
    private String subjectcode,subjectname,credit, syllabus,subjectid;

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public Subjectdisplay(String subjectcode, String subjectname, String credit, String syllabus, String subjectid){
        this.setSubjectcode(subjectcode);
        this.setSubjectname(subjectname);
        this.setCredit(credit);
        this.setSyllabus(syllabus);
        this.setSubjectid(subjectid);

    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }



    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
