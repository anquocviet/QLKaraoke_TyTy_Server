package server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class Server {

   public void start() {
      try (ServerSocket socket = new ServerSocket(8080)) {
         System.out.println("Server started at port 8080");

         while (true) {
            Socket client = socket.accept();
            System.out.println("Client connected");

            new Thread(new ServerThread(client)).start();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
