package kg.jarkyn.cobspecserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket implements Client {
     private Socket socket;

     public ClientSocket(Socket socket) {
          this.socket = socket;
     }

     @Override
     public OutputStream getOutputStream() {
          try {
               return socket.getOutputStream();
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }
}
