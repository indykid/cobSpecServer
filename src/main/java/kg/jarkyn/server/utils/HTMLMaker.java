package kg.jarkyn.server.utils;

public class HTMLMaker {
    private static String linkElementTemplate = "<a href=\"%s\">%s</a>";

    public static String makeLink(String href, String linkText) {
        return String.format(linkElementTemplate, href, linkText);
    }
}
