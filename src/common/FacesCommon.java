package common;

public class FacesCommon {
    public static String redirectToJSFPage(String page) {
        return page + "?faces-redirect=true";
    }

    public static String stayOnPage() {
        return "";
    }
}
