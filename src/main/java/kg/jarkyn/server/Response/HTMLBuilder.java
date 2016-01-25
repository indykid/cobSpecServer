package kg.jarkyn.server.Response;

public class HTMLBuilder {
    private static String linkElementTemplate = "<a href=\"%s\">%s</a>";

    public static String makeLink(String href, String linkText) {
        return String.format(linkElementTemplate, href, linkText);
    }
}
