package kg.jarkyn.server.responders;

import kg.jarkyn.server.incoming.Request;
import kg.jarkyn.server.outgoing.Responder;
import kg.jarkyn.server.outgoing.Response;
import kg.jarkyn.server.utils.PublicResource;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

import static kg.jarkyn.server.utils.Status.PARTIAL;

public class PartialContentResponder extends Responder {
    private PublicResource publicResource;

    public PartialContentResponder(PublicResource publicResource) {
        this.publicResource = publicResource;
    }

    @Override
    public Response respond(Request request) {
        return new Response(status(), headers(request), body(request));
    }

    private String status() {
        return String.format(STATUS_LINE_TEMPLATE, PARTIAL.getCode(), PARTIAL.getDescription());
    }

    private byte[] body(Request request) {
        int startingByte = startingByte(request);
        int endingByte = endingByte(request);

        int amount = endingByte - startingByte + 1;

        try {
            byte[] body = new byte[amount];
            RandomAccessFile raf = new RandomAccessFile(publicResource.fullPathFor(request.getPath()), "r");
            raf.read(body, startingByte, amount);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }

    private int endingByte(Request request) {
        String[] range = rawByteRange(request).split("-", 2);
        String start = range[0];
        String end = range[1];
        int endingByte;

        if (end.isEmpty() || start.isEmpty()) {
            endingByte = lastByteInTheResource(request);
        } else {
            endingByte = Integer.valueOf(end);
        }
        return endingByte;
    }

    private int startingByte(Request request) {
        String[] range = rawByteRange(request).split("-", 2);
        String start = range[0];
        int startingByte;

        if (start.isEmpty()) {
            int endingByte = lastByteInTheResource(request);
            startingByte = startingByteFromUpperRangeOnly(range, endingByte);
        } else {
            startingByte = Integer.valueOf(range[0]);
        }
        return startingByte;
    }

    private int startingByteFromUpperRangeOnly(String[] range, int endingByte) {
        int amount = Integer.valueOf(range[1]);
        return endingByte - amount + 1;
    }

    private int lastByteInTheResource(Request request) {
        return (int) fullResourceLength(request) - 1;
    }

    private String headers(Request request) {
        List<String> headers = Arrays.asList(
                "Content-Range: " + formatContentRange(request),
                "Content-Length: " + partialResourceLength(request)
        );
        return String.join("\r\n", headers);
    }

    private String rawByteRange(Request request) {
        return request.getHeaders().get("Range").replace("bytes=", "");
    }

    private String fullResourceReadableLength(Request request) {
        return String.valueOf(fullResourceLength(request));
    }

    private long fullResourceLength(Request request) {
        return new File(publicResource.fullPathFor(request.getPath())).length();
    }

    private String formatContentRange(Request request) {
        return ("bytes " + startingByte(request) + "-" + endingByte(request) + "/" + fullResourceReadableLength
                (request));
    }

    private int partialResourceLength(Request request) {
        return endingByte(request) - startingByte(request) + 1;
    }
}
