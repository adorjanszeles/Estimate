package common;

public enum Difficulty {
    EMPTY(""),
    EASY("Könnyű"),
    MEDIUM("Közepes"),
    HARD("Nehéz");

    private String name;

    Difficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
