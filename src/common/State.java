package common;

public enum State {
    DESIGNING("Tervezés alatt"),
    IN_PROGRESS("Folyamatban"),
    TESTING("Tesztelés alatt"),
    READY("Elkészült");

    private String name;

    State(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
