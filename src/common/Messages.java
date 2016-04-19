package common;

public enum Messages {
    USER_CREATE_SUCCESS("A felhasználó regisztrálása sikeres volt."),
    USER_CREATE_FAILED("A felhasználó regisztrálása sikertelen volt."),
    USER_MODIFY_SUCCESS("A felhasználó módosítása sikeres volt."),
    USER_MODIFY_FAILED("A felhasználó módosítása sikertelen volt."),
    USER_DELETE_FAILED("A felhasználó törlése sikertelen"),
    PROJECT_CREATE_SUCCESS("A projekt létrehozása sikeres volt."),
    PROJECT_CREATE_FAILED("A projekt létrehozása sikertelen volt.");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
