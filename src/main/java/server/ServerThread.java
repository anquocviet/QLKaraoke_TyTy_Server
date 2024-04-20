package server;

import entities.CT_KhuyenMai;
import entities.DichVu;
import entities.HoaDonThanhToan;
import entities.PhieuDatPhong;
import entities.Phong;
import entities.TaiKhoan;
import lombok.SneakyThrows;
import repositories.CT_KhuyenMaiRepository;
import repositories.ChiTietHD_DichVuRepository;
import repositories.DichVuRepository;
import repositories.HoaDonThanhToanRepository;
import repositories.NhanVienRepository;
import repositories.PhieuDatPhongRepository;
import repositories.PhongRepository;
import repositories.TaiKhoanRepository;
import services.CT_KhuyenMaiService;
import services.ChiTietHD_DichVuService;
import services.DichVuService;
import services.HoaDonThanhToanService;
import services.NhanVienService;
import services.PhieuDatPhongService;
import services.PhongService;
import services.TaiKhoanService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 15/4/24
 */
public class ServerThread implements Runnable {
   private final Socket socket;

   private DataInputStream dis;
   private DataOutputStream dos;
   private ObjectInputStream in;
   private ObjectOutputStream out;
   private final TaiKhoanRepository accountService;
   private final PhongRepository roomService;
   private final PhieuDatPhongRepository bookingTicketService;
   private final NhanVienRepository employeeService;
   private final DichVuRepository serviceService;
   private final HoaDonThanhToanRepository billService;
   private final ChiTietHD_DichVuRepository serviceDetailService;
   private final CT_KhuyenMaiRepository voucherService;

   public ServerThread(Socket socket) {
      this.socket = socket;
      accountService = new TaiKhoanService();
      roomService = new PhongService();
      bookingTicketService = new PhieuDatPhongService();
      employeeService = new NhanVienService();
      serviceService = new DichVuService();
      billService = new HoaDonThanhToanService();
      serviceDetailService = new ChiTietHD_DichVuService();
      voucherService = new CT_KhuyenMaiService();
   }

   @Override
   public void run() {
      try {
         dis = new DataInputStream(socket.getInputStream());
         dos = new DataOutputStream(socket.getOutputStream());
         in = new ObjectInputStream(socket.getInputStream());
         out = new ObjectOutputStream(socket.getOutputStream());

         while (true) {
            String line = dis.readUTF();
            String control = line.split("-")[0];
            line = line.replaceFirst(control + "-", "");

            System.out.println(line);

            switch (control) {
               case "room" -> roomController(line);
               case "account" -> accountController(line);
               case "bookingTicket" -> bookingTicketController(line);
               case "employee" -> employeeController(line);
               case "service" -> serviceController(line);
               case "bill" -> billController(line);
               case "serviceDetail" -> serviceDetailController(line);
               case "voucher" -> voucherController(line);
               default -> out.flush();
            }
         }

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @SneakyThrows
   private void voucherController(String line) {
      if (line.equals("find-all-voucher")) {
         out.writeObject(voucherService.findAll());
      } else if (line.matches("find-by-id,.*")) {
         out.writeObject(null);
      } else if (line.equals("update-voucher")) {
         out.writeBoolean(false);
      } else if (line.equals("delete-voucher")) {
         out.writeBoolean(false);
      } else if (line.equals("add-voucher")) {
         out.writeBoolean(false);
      } else if (line.equals("count-voucher")) {
         dos.writeUTF("0");
      }
   }

   @SneakyThrows
   private void serviceDetailController(String line) {
      if (line.matches("find-by-bill-id,.*")) {
         out.writeObject(serviceDetailService.findByMaHoaDon(line.split(",")[1]));
      } else if (line.equals("add-serviceDetail")) {
         boolean result = serviceDetailService.addChiTietHD_DichVu((entities.ChiTietHD_DichVu) in.readObject());
         out.writeBoolean(result);
      } else if (line.equals("update-serviceDetail")) {
         boolean result = serviceDetailService.updateChiTietHD_DichVu((entities.ChiTietHD_DichVu) in.readObject());
         out.writeBoolean(result);
      }
   }

   @SneakyThrows
   private void billController(String line) {
      if (line.matches("find-bill-by-room-id,.*")) {
         HoaDonThanhToan bill = billService.findByRoomUsing(line.split(",")[1]);
         out.writeObject(bill);
      }
   }

   @SneakyThrows
   private void serviceController(String line) {
      if (line.equals("find-all-service")) {
         out.writeObject(serviceService.findAllDichVu());
      } else if (line.matches("find-service,.*")) {
         out.writeObject(null);
      } else if (line.equals("update-service")) {
         boolean result = serviceService.updateDichVu((DichVu) in.readObject());
         out.writeBoolean(result);
      } else if (line.equals("delete-service")) {
         boolean result = serviceService.deleteDichVu((DichVu) in.readObject());
         out.writeBoolean(result);
      } else if (line.equals("add-service")) {
         out.writeBoolean(false);
      } else if (line.equals("count-service")) {
         dos.writeUTF("0");
      } else if (line.matches("service-find-by-bill-id,.*")) {
         out.writeObject(serviceService.findListDichVuByMaHoaDon(line.split(",")[1]));
      }
   }

   @SneakyThrows
   private void employeeController(String line) {
      if (line.equals("find-all-employee")) {
         out.writeObject(employeeService.findAll());
      } else if (line.matches("find-employee,.*")) {
         out.writeObject(employeeService.findByMaNhanVien(line.split(",")[1]));
      }
   }

   @SneakyThrows
   public void roomController(String line) {

      if (line.equals("update-room")) {
         Phong room = (Phong) in.readObject();
         out.writeBoolean(roomService.updatePhong(room));
      } else if (line.equals("find-all-room")) {
         out.writeObject(roomService.findAll());
      } else if (line.matches("find-room,.*")) {
         out.writeObject(roomService.findByMaPhong(line.split(",")[1]));
      } else if (line.matches("count-room-status,.*")) {
         dos.writeUTF(roomService.countRoomStatus(Integer.parseInt(line.split(",")[1])).toString());
      } else if (line.equals("find-room-by-type-status-capacity")) {
         List<Integer> arrType = (List<Integer>) in.readObject();
         List<Integer> arrStatus = (List<Integer>) in.readObject();
         int capacity = dis.readInt();
         out.writeObject(roomService.findRoomByTypeStatusCapacity(arrType, arrStatus, capacity));
      } else if (line.matches("find-room-by-status,.*")) {
         out.writeObject(roomService.findRoomByStatus(Integer.parseInt(line.split(",")[1])));
      }
   }


   @SneakyThrows
   private void bookingTicketController(String line) {
      if (line.matches("find-booking-ticket-by-room,.*")) {
         out.writeObject(bookingTicketService.findBookingTicketByRoomID(line.split(",")[1]));
      } else if (line.equals("update-booking-ticket")) {
         PhieuDatPhong bookingTicket = (PhieuDatPhong) in.readObject();
         out.writeBoolean(bookingTicketService.updatePhieuDatPhong(bookingTicket));
      }
   }

   @SneakyThrows
   private void accountController(String line) {
      if (line.equals("login")) {
         TaiKhoan account = accountService.login((TaiKhoan) in.readObject());
         if (account != null) {
            System.out.println("login-success");
            dos.writeUTF("login-success");
            out.writeObject(account);
         } else {
            dos.writeUTF("login-fail");
         }
      }
   }
}