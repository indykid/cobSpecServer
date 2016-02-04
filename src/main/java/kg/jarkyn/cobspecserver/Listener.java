package kg.jarkyn.cobspecserver;

public interface Listener {
    Client accept();

    boolean isAccepting();
}
