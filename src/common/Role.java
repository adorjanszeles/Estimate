package common;

import java.io.Serializable;

public enum Role implements Serializable{
    ADMIN("Adminisztrátor"),
    USER("Felhasználó");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
