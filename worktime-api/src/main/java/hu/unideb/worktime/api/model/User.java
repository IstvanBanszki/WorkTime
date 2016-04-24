package hu.unideb.worktime.api.model;

import java.time.LocalDateTime;

public class User {

    private final int id;
    private final String loginName;
    private final LocalDateTime dateOfRegistration;
    private final String password;
    private final Role role;

    private User(int id, String loginName, LocalDateTime dateOfRegistration, String password, Role role) {
        this.id = id;
        this.loginName = loginName;
        this.dateOfRegistration = dateOfRegistration;
        this.password = password;
        this.role = role;
    }

    public static class UserBuilder {

        private int id;
        private String loginName;
        private Role role;
        private LocalDateTime dateOfRegistration;
        private String password;

        public UserBuilder() {
            id = 0;
            loginName = "";
            role = Role.NOT_SET;
            dateOfRegistration = null;
            password = "";
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder loginName(String loginName) {
            this.loginName = loginName;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder dateOfRegistration(LocalDateTime dateOfRegistration) {
            this.dateOfRegistration = dateOfRegistration;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this.id, this.loginName, this.dateOfRegistration,
                    this.password, this.role);
        }
    }

    public int getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public LocalDateTime getDateOfRegistration() {
        return dateOfRegistration;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", loginName=" + loginName + ", dateOfRegistration="
                + dateOfRegistration + ", password=" + password + ", role=" + role + '}';
    }
}
