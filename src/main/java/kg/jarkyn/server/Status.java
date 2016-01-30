package kg.jarkyn.server;

public enum Status {
    SUCCESS("200", "OK"),
    NOCONTENT("204", "No Content"),
    PARTIAL("206", "Partial Content"),
    REDIRECT("302", "Found"),
    UNAUTHORIZED("401", "Unauthorized"),
    NOTFOUND("404", "Not Found"),
    NOTALLOWED("405", "Method Not Allowed"),
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
