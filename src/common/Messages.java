package common;

public enum Messages {
    USER_CREATE_SUCCESS("A felhasználó regisztrálása sikeres volt."),
    USER_CREATE_FAILED("A felhasználó regisztrálása sikertelen volt."),
    USER_MODIFY_SUCCESS("A felhasználó módosítása sikeres volt."),
    USER_MODIFY_FAILED("A felhasználó módosítása sikertelen volt."),
    USER_DELETE_FAILED("A felhasználó törlése sikertelen volt."),
    PROJECT_CREATE_SUCCESS("A projekt létrehozása sikeres volt."),
    PROJECT_CREATE_FAILED("A projekt létrehozása sikertelen volt."),
    PROJECT_MODIFY_SUCCESS("A projekt módosítása sikeres volt."),
    PROJECT_MODIFY_FAILED("A projekt módosítása sikertelen volt."),
    PROJECT_DELETE_FAILED("A projekt törlése sikertelen volt."),
    TASK_CREATE_FAILED("A feladat létrehozása sikertelen volt."),
    TASK_CREATE_SUCCESS("A feladat létrehozása sikeres volt."),
    TASK_MODIFY_FAILED("A feladat módosítása sikertelen volt."),
    TASK_MODIFY_SUCCESS("A feladat módosítása sikeres volt."),
    TASK_DELETE_FAILED("A feladat törlése sikertelen volt."),
    WORKLOG_SAVE_SUCCESS("A munkanapló bejegyzés mentése sikeres volt."),
    WORKLOG_SAVE_FAILED("A munkanapló bejegyzés mentése sikertelen volt."),
    WORKLOG_DELETE_FAILED("A munkanapló bejegyzés törlése sikertelen volt.");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
