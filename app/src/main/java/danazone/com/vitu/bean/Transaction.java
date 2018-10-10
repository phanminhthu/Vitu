package danazone.com.vitu.bean;

import java.io.Serializable;

public class Transaction implements Serializable{
    private String idTransaction;
    private String myClass;
    private String subject;
    private String streetClass;
    private String countyClass;
    private String cityClass;
    private String numberStudent;
    private String schedule;
    private String schoolStudent;
    private String feePerDay;
    private String totalFee;
    private String dateStart;
    private String nameParent;
    private String week;
    private String nameVisu;

    public Transaction() {
    }

    public Transaction(String idTransaction, String myClass, String subject, String streetClass, String countyClass, String cityClass, String numberStudent, String schedule, String schoolStudent, String feePerDay, String totalFee, String dateStart, String nameParent, String week, String nameVisu) {
        this.idTransaction = idTransaction;
        this.myClass = myClass;
        this.subject = subject;
        this.streetClass = streetClass;
        this.countyClass = countyClass;
        this.cityClass = cityClass;
        this.numberStudent = numberStudent;
        this.schedule = schedule;
        this.schoolStudent = schoolStudent;
        this.feePerDay = feePerDay;
        this.totalFee = totalFee;
        this.dateStart = dateStart;
        this.nameParent = nameParent;
        this.week = week;
        this.nameVisu = nameVisu;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStreetClass() {
        return streetClass;
    }

    public void setStreetClass(String streetClass) {
        this.streetClass = streetClass;
    }

    public String getCountyClass() {
        return countyClass;
    }

    public void setCountyClass(String countyClass) {
        this.countyClass = countyClass;
    }

    public String getCityClass() {
        return cityClass;
    }

    public void setCityClass(String cityClass) {
        this.cityClass = cityClass;
    }

    public String getNumberStudent() {
        return numberStudent;
    }

    public void setNumberStudent(String numberStudent) {
        this.numberStudent = numberStudent;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchoolStudent() {
        return schoolStudent;
    }

    public void setSchoolStudent(String schoolStudent) {
        this.schoolStudent = schoolStudent;
    }

    public String getFeePerDay() {
        return feePerDay;
    }

    public void setFeePerDay(String feePerDay) {
        this.feePerDay = feePerDay;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getNameParent() {
        return nameParent;
    }

    public void setNameParent(String nameParent) {
        this.nameParent = nameParent;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getNameVisu() {
        return nameVisu;
    }

    public void setNameVisu(String nameVisu) {
        this.nameVisu = nameVisu;
    }
}

