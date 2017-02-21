package com.example.user.volleyjson;

/**
 * Created by USER on 2/21/2017.
 */

public class questiongetter {

    private String Years,Types,pdf;

    public String getYears() {
        return Years;
    }

    public void setYears(String years) {
        Years = years;
    }

    public String getTypes() {
        return Types;
    }

    public void setTypes(String types) {
        Types = types;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

public questiongetter(String Types,String Years, String pdf){

    this.setTypes(Types);
    this.setPdf(pdf);
    this.setYears(Years);


}
}
