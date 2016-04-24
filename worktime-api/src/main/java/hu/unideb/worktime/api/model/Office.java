package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class Office {

    private final int id;
    private final String name;
    private final String address;
    private final LocalDateTime dateOfRegistration;
    private final LocalDateTime dateOfFoundtation;

    public Office(int id, String name, String address, LocalDateTime dateOfRegistration, LocalDateTime dateOfFoundtation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfRegistration = dateOfRegistration;
        this.dateOfFoundtation = dateOfFoundtation;
    }

    public static class OfficeBuilder {

        private int id;
        private String name;
        private String address;
        private LocalDateTime dateOfRegistration;
        private LocalDateTime dateOfFoundtation;

        public OfficeBuilder() {
            this.id = 0;
            this.name = "";
            this.address = "";
            this.dateOfRegistration = null;
            this.dateOfFoundtation = null;
        }

        public OfficeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public OfficeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public OfficeBuilder address(String address) {
            this.address = address;
            return this;
        }

        public OfficeBuilder dateOfRegistration(LocalDateTime dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public OfficeBuilder dateOfFoundtation(LocalDateTime dateOfFoundtation) {
            this.dateOfFoundtation = dateOfFoundtation;
            return this;
        }

        public Office build() {
            return new Office(this.id, this.name, this.address, this.dateOfRegistration, this.dateOfFoundtation);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public LocalDateTime getDateOfFoundtation() {
        return dateOfFoundtation;
    }

    @Override
    public String toString() {
        return "Office{" + "id=" + id + ", name=" + name + ", address=" + address
                + ", dateOfRegistration=" + dateOfRegistration + ", dateOfFoundtation="
                + dateOfFoundtation + '}';
    }
}
