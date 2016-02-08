package kg.jarkyn.cobspecserver;

import kg.jarkyn.cobspecserver.middleware.ResponseController;
import kg.jarkyn.cobspecserver.middleware.Router;
import kg.jarkyn.cobspecserver.responders.FormResponder;
import kg.jarkyn.cobspecserver.responders.OptionsResponder;
import kg.jarkyn.cobspecserver.responders.PublicResourceResponder;
import kg.jarkyn.cobspecserver.responders.RedirectResponder;
import kg.jarkyn.cobspecserver.sockets.ListenerSocket;
import kg.jarkyn.cobspecserver.utils.PublicResource;
import kg.jarkyn.cobspecserver.utils.RequestParser;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static boolean KEEP_RUNNING = true;
    private static int defaultPort      = 5000;
    private static String publicResourcePath = "/Users/Jarkyn/Projects/8thLight/JAVA/cob_spec/public";

    public static void main(String[] args) {
        server().run();
    }

    private static Server server() {
        return new Server(executor(), listener(), controller());
    }

    private static ExecutorService executor() {
        return Executors.newFixedThreadPool(10);
    }

    private static ListenerSocket listener() {
        return new ListenerSocket(getServerSocket(), KEEP_RUNNING);
    }

    private static ResponseController controller() {
        RequestParser parser = new RequestParser();
        Router router = setupRouter();
        return new ResponseController(parser, router);
    }

    private static ServerSocket getServerSocket() {
        try {
            return new ServerSocket(defaultPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Router setupRouter() {
        Router router = new Router(defaultResponder());
        setupRoutes(router);
        return router;
    }

    private static PublicResourceResponder defaultResponder() {
        PublicResource publicResource = new PublicResource(publicResourcePath);
        return new PublicResourceResponder(publicResource);
    }

    private static RedirectResponder setupRedirectResponder() {
        RedirectResponder redirectResponder = new RedirectResponder();
        redirectResponder.registerRedirection("/redirect", "/");
        return redirectResponder;
    }

    private static void setupRoutes(Router router) {
        router.registerRoute("/redirect", setupRedirectResponder());
        router.registerRoute("/method_options", new OptionsResponder());
        router.registerRoute("/form", new FormResponder());
    }
}