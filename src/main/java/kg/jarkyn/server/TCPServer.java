package kg.jarkyn.server;

import kg.jarkyn.server.Request.ParsedRequest;
import kg.jarkyn.server.Request.RequestParser;
import kg.jarkyn.server.Resource.ResourceFolder;
import kg.jarkyn.server.Resource.ResourceHandler;
import kg.jarkyn.server.Response.ResponseContent;
import kg.jarkyn.server.Response.ResponseContentMaker;
import kg.jarkyn.server.Response.ResponseStatusLine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public void run() {
//        open socket
        try {
            ServerSocket server = new ServerSocket(5000); // to test
            ResourceFolder resourceFolder = new ResourceFolder("/Users/Jarkyn/Projects/8thLight/JAVA/cob_spec/public");
            ResourceHandler resourceHandler = new ResourceHandler(resourceFolder);
            ResponseContentMaker contentBuilder = new ResponseContentMaker(resourceHandler);
            System.out.println(server);
            while (true) {
                try (Socket client = server.accept()) { // test

                    System.out.println(client.getInetAddress());
                    ParsedRequest request = RequestParser.parse(client.getInputStream());

                    String requestPath = request.getPath();

                    ResponseContent responseContent = contentBuilder.make(requestPath);

                    String statusLine = new ResponseStatusLine("200").format();

                    String header = statusLine + "\r\n" + "Content-Type: " + responseContent.getContentType() + "\r\n\r\n";
                    byte[] headerByteContent = header.getBytes();
                    byte[] contentBytes = responseContent.getByteContent();
                    byte[] result = new byte[headerByteContent.length + contentBytes.length];
                    System.arraycopy(headerByteContent, 0, result, 0, headerByteContent.length);
                    System.arraycopy(contentBytes, 0, result, headerByteContent.length, contentBytes.length);

                    client.getOutputStream().write(result);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

