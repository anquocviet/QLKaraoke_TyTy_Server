package server;

import entities.TaiKhoan;
import services.TaiKhoanService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @description
 * @author: vie
 * @date: 15/4/24
 */
public class ServerThread implements Runnable {
   private Socket socket;

   private DataInputStream is;
   private DataOutputStream os;
   private ObjectInputStream ois;
   private ObjectOutputStream oos;
   private TaiKhoanService accountService;

   public ServerThread(Socket socket) {
      this.socket = socket;
      accountService = new TaiKhoanService();
   }

   @Override
   public void run() {
      try {
         is = new DataInputStream(socket.getInputStream());
         os = new DataOutputStream(socket.getOutputStream());
         ois = new ObjectInputStream(socket.getInputStream());
         oos = new ObjectOutputStream(socket.getOutputStream());

         while (true) {
            String line = is.readUTF();
            System.out.println(line);

            if (line.equals("login")) {
               TaiKhoan account = accountService.login((TaiKhoan) ois.readObject());
               if (account != null) {
                  System.out.println("login-success");
                  os.writeUTF("login-success");
                  oos.writeObject(account);
               } else {
                  os.writeUTF("login-fail");
               }
            }


         }

      } catch (IOException | ClassNotFoundException e) {
      }
   }
}