package hu.unideb.worktime.api.model;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {

    NOT_SET(0),
    MALE(1),
    FEMALE(2);

    private Gender(int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return this.id;
    }
    
    public static Gender valueOf(int id){
        Optional<Gender> g = Arrays.stream(Gender.values()).filter(gender -> gender.getId() == id).findFirst();
        if( g.isPresent() ){
            return g.get();
        } else {
            return Gender.NOT_SET;
        }
    }
}
