package com.example.user.volleyjson;

/**
 * Created by USER on 2/2/2017.
 */

public class Bookdisplay {
    private String bookName,writer,booktype,price,availability,publication,rackNumber,pdf;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    //private String bookName,writer,booktype,price,availability,publication,rackNumber,pdf;

    public Bookdisplay(String bookName, String writer, String booktype, String price, String availability, String publication, String rackNumber, String pdf){
        this.setBooktype(booktype);
        this.setAvailability(availability);
        this.setPrice(price);
        this.setRackNumber(rackNumber);
        this.setPdf(pdf);
        this.setWriter(writer);
        this.setPublication(publication);
        this.setBookName(bookName);
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(String rackNumber) {
        this.rackNumber = rackNumber;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
