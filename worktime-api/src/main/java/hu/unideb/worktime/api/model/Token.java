package hu.unideb.worktime.api.model;

import java.io.Serializable;

public class Token implements Serializable {
    
    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public Token() {
        this.token = "";
    }

    public String getToken() {
        return token;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.token != null ? this.token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        return other.token != null ? other.token.equals(other.token) : this.token == null;
    }

    @Override
    public String toString() {
        return "Token{" + "token=" + token + '}';
    }
    
}
