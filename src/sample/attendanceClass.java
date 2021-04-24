package sample;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aboosidhu
 */
class AttendenceClass {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty days = new SimpleStringProperty();

    private final Map<String, StringProperty> activeByGroup = new HashMap<>();




    public AttendenceClass(List<String> dayarray) {
        for (String group : dayarray) {

            activeByGroup.put(group, new SimpleStringProperty()) ;
        }
    }


    public final StringProperty daysProperty(String group) {
        // might need to deal with the case where
        // there is no entry in the map for group
        // (else calls to isActive(...) and setActive(...) with
        // a non-existent group will give a null pointer exception):

        return activeByGroup.get(group) ;
    }

    public final String getDays(String group) {
        return this.daysProperty(group).get();
    }


    public final void setDays(final String group,String value) {
        daysProperty(group).set(value);
    }




    public final StringProperty nameProperty() {
        return this.name;
    }


    public final String getName() {
        return this.nameProperty().get();
    }


    public final void setName(final String name) {
        this.nameProperty().set(name);
    }


















}
