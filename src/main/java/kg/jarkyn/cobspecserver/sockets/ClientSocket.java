package kg.jarkyn.cobspecserver.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {
     private Socket socket;

     public ClientSocket(Socket socket) {
          this.socket = socket;
     }

     public OutputStream getOutputStream() {
          try {
               return socket.getOutputStream();
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }

     public InputStream getInputStream() {
          try {
               return socket.getInputStream();
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }

     public void close() {
          try {
               socket.close();
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
     }
}
