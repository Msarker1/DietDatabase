package com.shaer.dietdatabase;

/**
 * Created by mohammad on 12/11/17.
 */

public class Node {

    private String name;
    private String NDBNO;
    private String Fats;
    private String Energy;
    private String Carbohydrates;
    private String sugar;
    private String Date;
    private Node next;


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }



    public Node( ) {
        next = null;

    }



    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNDBNO() {
        return NDBNO;
    }

    public void setNDBNO(String NDBNO) {
        this.NDBNO = NDBNO;
    }

    public String getFats() {
        return Fats;
    }

    public void setFats(String fats) {
        Fats = fats;
    }

    public String getEnergy() {
        return Energy;
    }

    public void setEnergy(String energy) {
        Energy = energy;
    }

    public String getCarbohydrates() {
        return Carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        Carbohydrates = carbohydrates;
    }
}
