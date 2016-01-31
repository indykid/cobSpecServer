package kg.jarkyn.server;

import kg.jarkyn.server.incoming.ReceivingSocket;
import kg.jarkyn.server.incoming.RequestParser;
import kg.jarkyn.server.outgoing.Controller;
import kg.jarkyn.server.outgoing.Delegator;
import kg.jarkyn.server.outgoing.ResponseController;
import kg.jarkyn.server.utils.PublicResource;

import java.io.IOException;
import java.net.ServerSocket;

public class Runner {
    public static void main(String[] args) {
        server().run();
    }

    private static Server server() {
        Delegator delegator = new Delegator(new PublicResource("/Users/Jarkyn/Projects/8thLight/JAVA/cob_spec/public"));
        Controller controller = new ResponseController(new RequestParser(), delegator);
        return new Server(openSocket(5000), controller);
    }

    private static ReceivingSocket openSocket(int port) {
        try {
            return new ReceivingSocket(new ServerSocket(port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
