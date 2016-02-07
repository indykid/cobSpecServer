package kg.jarkyn.cobspecserver.utils;

public enum Status {
    SUCCESS("200", "OK"),
    REDIRECT("302", "Found"),
    NOTFOUND("404", "Not Found"),
    ;

    private final String code;
    private final String description;

    Status(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
