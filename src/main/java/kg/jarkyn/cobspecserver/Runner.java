package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.middleware.ResponseController;
import kg.jarkyn.cobspecserver.middleware.Router;
import kg.jarkyn.cobspecserver.responders.PublicResourceResponder;
import kg.jarkyn.cobspecserver.sockets.ListenerSocket;
import kg.jarkyn.cobspecserver.utils.RequestParser;

import java.io.IOException;
import java.net.ServerSocket;

public class Runner {
    public static void main(String[] args) {
        server().run();
    }

    private static Server server() {
        return new Server(listener(), controller());
    }

    private static ListenerSocket listener() {
        return new ListenerSocket(getServerSocket(), true);
    }

    private static ResponseController controller() {
        RequestParser parser = new RequestParser();
        Router router = new Router(new PublicResourceResponder());
        return new ResponseController(parser, router);
    }

    private static ServerSocket getServerSocket() {
        try {
            return new ServerSocket(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
