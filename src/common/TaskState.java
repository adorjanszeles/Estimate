package common;

public enum TaskState {
    EMPTY(""),
    OPEN("Nyitott"),
    IN_PROGRESS("Folyamatban"),
    DONE("Kész");

    private String name;

    TaskState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
