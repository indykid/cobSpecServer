package kg.jarkyn.cobspecserver.utils;

public class HTMLMaker {
    private static String linkElementTemplate = "<a href=\"%s\">%s</a>";

    public static String makeLink(String href, String text) {
        return String.format(linkElementTemplate, href, text);
    }
}
