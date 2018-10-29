package com.shaer.dietdatabase;

/**
 * Created by mohammad on 12/3/17.
 */

public interface asyncResponse {

    void searchNDBNO(LinkedList n, int size);
    void scanNDBNO(String n);
    void getNutrients(String[] num);

}
