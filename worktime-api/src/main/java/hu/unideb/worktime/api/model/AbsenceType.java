package hu.unideb.worktime.api.model;

public class AbsenceType {

    private final int id;
    private final String type;

    public AbsenceType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "AbsenceType{" + "id=" + id + ", type=" + type + '}';
    }
}
