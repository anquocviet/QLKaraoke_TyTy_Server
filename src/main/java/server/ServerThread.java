package server;

import entities.CT_KhuyenMai;
import entities.ChiTietHD_DichVu;
import entities.ChiTietHD_Phong;
import entities.DichVu;
import entities.HoaDonThanhToan;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuDatPhong;
import entities.Phong;
import entities.TaiKhoan;
import lombok.SneakyThrows;
import repositories.CT_KhuyenMaiRepository;
import repositories.ChiTietHD_DichVuRepository;
import repositories.DichVuRepository;
import repositories.HoaDonThanhToanRepository;
import repositories.KhachHangRepository;
import repositories.NhanVienRepository;
import repositories.PhieuDatPhongRepository;
import repositories.PhongRepository;
import repositories.TaiKhoanRepository;
import services.CT_KhuyenMaiService;
import services.ChiTietHD_DichVuService;
import services.ChiTietHD_PhongService;
import services.DichVuService;
import services.HoaDonThanhToanService;
import services.KhachHangService;
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
import java.time.Instant;
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
   private final ChiTietHD_PhongService roomDetailService;
   private final CT_KhuyenMaiRepository voucherService;
   private final KhachHangRepository customerService;

   public ServerThread(Socket socket) {
      this.socket = socket;
      accountService = new TaiKhoanService();
      roomService = new PhongService();
      bookingTicketService = new PhieuDatPhongService();
      employeeService = new NhanVienService();
      serviceService = new DichVuService();
      billService = new HoaDonThanhToanService();
      serviceDetailService = new ChiTietHD_DichVuService();
      roomDetailService = new ChiTietHD_PhongService();
      voucherService = new CT_KhuyenMaiService();
      customerService = new KhachHangService();
   }

   @Override
   public void run() {
      try {
         dos = new DataOutputStream(socket.getOutputStream());
         dis = new DataInputStream(socket.getInputStream());
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
               case "roomDetail" -> roomDetailController(line);
               case "voucher" -> voucherController(line);
               case "customer" -> customerController(line);
               default -> dos.flush();
            }
         }

      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @SneakyThrows
   private void customerController(String line) {
      if (line.equals("add-customer")) {
         KhachHang customer = (KhachHang) in.readObject();
         dos.writeBoolean(customerService.addCustomer(customer));
      } else if (line.equals("update-customer")) {
         KhachHang customer = (KhachHang) in.readObject();
         dos.writeBoolean(customerService.updateCustomer(customer));
      } else if (line.equals("find-all-customer")) {
         out.reset();
         out.writeObject(customerService.findAll());
      } else if (line.matches("find-customer,.*")) {
         out.reset();
         out.writeObject(customerService.findByMaKhachHang(line.split(",")[1]));
      } else if (line.matches("find-customer-by-name,.*")) {
         out.reset();
         out.writeObject(customerService.findByTenKhachHang(line.split(",")[1]));
      } else if (line.matches("find-customer-by-phone,.*")) {
         out.reset();
         out.writeObject(customerService.findBySoDienThoai(line.split(",")[1]));
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void voucherController(String line) {
      if (line.equals("find-all-voucher")) {
         out.reset();
         out.writeObject(voucherService.findAll());
      } else if (line.matches("find-voucher,.*")) {
         try {
            out.reset();
            out.writeObject(voucherService.findByMaKhuyenMai(line.split(",")[1]));
         } catch (Exception e) {
            out.reset();
            out.writeObject(voucherService.findByMaKhuyenMai(""));
         }
      } else if (line.matches("find-voucher-by-name,.*")) {
         out.reset();
         out.writeObject(voucherService.findByTenKhuyenMai(line.split(",")[1]));
      } else if (line.equals("add-voucher")) {
         dos.writeBoolean(voucherService.addCT_KhuyenMai((CT_KhuyenMai) in.readObject()));
      } else if (line.equals("update-voucher")) {
         dos.writeBoolean(voucherService.updateCT_KhuyenMai((CT_KhuyenMai) in.readObject()));
      } else if (line.matches("delete-voucher,.*")) {
         dos.writeBoolean(voucherService.deleteCT_KhuyenMai(line.split(",")[1]));
      } else if (line.equals("count-voucher")) {
         dos.writeUTF("0");
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void roomDetailController(String line) {
      if (line.equals("add-roomDetail")) {
         dos.writeBoolean(roomDetailService.addChiTietHD_Phong((ChiTietHD_Phong) in.readObject()));
      } else if (line.equals("update-roomDetail")) {
         dos.writeBoolean(roomDetailService.updateChiTietHD_Phong((ChiTietHD_Phong) in.readObject()));
      } else if (line.equals("delete-roomDetail")) {
         dos.writeBoolean(roomDetailService.deleteChiTietHD_Phong((ChiTietHD_Phong) in.readObject()));
      } else if (line.matches("find-by-bill-id,.*")) {
         out.reset();
         out.writeObject(roomDetailService.findByMaHoaDon(line.split(",")[1]));
      } else if (line.matches("find-by-room-id,.*")) {
         out.reset();
         out.writeObject(roomDetailService.findByMaPhong(line.split(",")[1]));
      } else if (line.matches("find-by-room-bill-id,.*")) {
         out.reset();
         out.writeObject(roomDetailService.findByMaPhongAndMaHoaDon(line.split(",")[1].split("_")[0], line.split(",")[1].split("_")[1]));
      } else if (line.equals("find-by-date")) {
         Instant startDate = (Instant) in.readObject();
         Instant endDate = (Instant) in.readObject();
         out.reset();
         out.writeObject(roomDetailService.findByDate(startDate, endDate));
      } else if (line.matches("calc-total-hours-use-of-customer,.*")) {
         dos.writeLong(roomDetailService.calcTotalHoursOfUseOfCustomer(line.split(",")[1]));
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void serviceDetailController(String line) {
      if (line.equals("add-serviceDetail")) {
         boolean result = serviceDetailService.addChiTietHD_DichVu((ChiTietHD_DichVu) in.readObject());
         dos.writeBoolean(result);
      } else if (line.equals("update-serviceDetail")) {
         boolean result = serviceDetailService.updateChiTietHD_DichVu((ChiTietHD_DichVu) in.readObject());
         dos.writeBoolean(result);
      } else if (line.matches("delete-serviceDetail")) {
         dos.writeBoolean(serviceDetailService.deleteChiTietHD_DichVu((ChiTietHD_DichVu) in.readObject()));
      } else if (line.matches("find-by-bill-id,.*")) {
         out.reset();
         out.writeObject(serviceDetailService.findByMaHoaDon(line.split(",")[1]));
      } else if (line.matches("find-by-service-id,*")) {
         out.reset();
         out.writeObject(serviceDetailService.findByMaDichVu(line.split(",")[1]));
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void billController(String line) {
      if (line.equals("add-bill")) {
         HoaDonThanhToan bill = (HoaDonThanhToan) in.readObject();
         dos.writeBoolean(billService.addHoaDonThanhToan(bill));
      } else if (line.equals("update-bill")) {
         HoaDonThanhToan bill = (HoaDonThanhToan) in.readObject();
         dos.writeBoolean(billService.updateHoaDonThanhToan(bill));
      } else if (line.matches("delete-bill,.*")) {
         dos.writeBoolean(billService.deleteHoaDonThanhToan(line.split(",")[1]));
      } else if (line.equals("find-all-bill")) {
         out.reset();
         out.writeObject(billService.findAll());
      } else if (line.matches("find-bill,.*")) {
         out.reset();
         out.writeObject(billService.findBill(line.split(",")[1]));
      } else if (line.equals("find-bill-by-date-create")) {
         Instant ngayTao = (Instant) in.readObject();
         out.reset();
         out.writeObject(billService.findBill(ngayTao));
      } else if (line.matches("find-bill-by-room-id,.*")) {
         HoaDonThanhToan bill = billService.findByRoomUsing(line.split(",")[1]);
         out.reset();
         out.writeObject(bill);
      } else if (line.matches("find-bill-by-customer-id,.*")) {
         out.reset();
         out.writeObject(billService.findByCustomerID(line.split(",")[1]));
      } else if (line.matches("find-bill-by-phone-customer,.*")) {
         out.reset();
         out.writeObject(billService.findByPhoneCustomer(line.split(",")[1]));
      } else if (line.matches("find-bill-by-name-customer,.*")) {
         out.reset();
         out.writeObject(billService.findByNameCustomer(line.split(",")[1]));
      } else if (line.startsWith("find-bill-by-date,")) {
         String dateString = line.split(",")[1];
         Instant date = Instant.parse(dateString);
         out.reset();
         out.writeObject(billService.findByDate(date));
      } else if (line.matches("find-bill-by-employee-id,.*")) {
         out.reset();
         out.writeObject(billService.findByEmployeeID(line.split(",")[1]));
      } else if (line.equals("count-bill")) {
         dos.writeLong(billService.countBill());
      } else if (line.equals("count-bill-by-date")) {
         dos.writeLong(billService.countBillByDate((Instant) in.readObject()));
      } else if (line.matches("count-bill-by-customer-id,.*")) {
         dos.writeLong(billService.countBill(line.split(",")[1]));
      } else if (line.matches("find-by-ma-khach-hang-not-pay,.*")) {
         out.writeObject(billService.findBillByCustomerNotPay(line.split(",")[1]));
      } else if (line.matches("get-bill-by-date,.*")) {
         Instant date = (Instant) in.readObject();
         String type = line.split(",")[1];
         out.reset();
         out.writeObject(billService.getBillsByDate(date, type));
      } else if (line.equals("calc-money")) {
         dos.writeLong(billService.calcMoney());
      } else if (line.matches("calc-money-by-date,.*")) {
         Instant date = (Instant) in.readObject();
         String type = line.split(",")[1];
         dos.writeLong(billService.calcMoney(date, type));
      } else if (line.matches("calc-money-by-customer-id,.*")) {
         dos.writeLong(billService.calcMoney(line.split(",")[1]));
      } else if (line.matches("count-by-date,.*")) {
         dos.writeLong(billService.countByDate(Instant.parse(line.split(",")[1])));
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void serviceController(String line) {
      if (line.equals("find-all-service")) {
         out.reset();
         out.writeObject(serviceService.findAllDichVu());
      } else if (line.matches("find-service,.*")) {
         out.reset();
         out.writeObject(serviceService.findDichVu(line.split(",")[1]));
      } else if (line.equals("add-service")) {
         boolean result = serviceService.addDichVu((DichVu) in.readObject());
         dos.writeBoolean(result);
      } else if (line.equals("update-service")) {
         boolean result = serviceService.updateDichVu((DichVu) in.readObject());
         dos.writeBoolean(result);
      } else if (line.equals("delete-service")) {
         boolean result = serviceService.deleteDichVu((DichVu) in.readObject());
         dos.writeBoolean(result);
      } else if (line.matches("find-service-by-bill-id,.*")) {
         out.reset();
         out.writeObject(serviceService.findListDichVuByMaHoaDon(line.split(",")[1]));
      } else if (line.equals("count-all-service")) {
         dos.writeLong(serviceService.countDichVu());
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void employeeController(String line) {
      if (line.equals("find-all-employee")) {
         out.reset();
         out.writeObject(employeeService.findAll());
      } else if (line.matches("find-employee,.*")) {
         out.reset();
         out.writeObject(employeeService.findByMaNhanVien(line.split(",")[1]));
      } else if (line.matches("find-employee-by-name,.*")) {
         out.reset();
         out.writeObject(employeeService.findByHoTen(line.split(",")[1]));
      } else if (line.matches("find-employee-by-cccd,.*")) {
         out.reset();
         out.writeObject(employeeService.findByCCCD(line.split(",")[1]));
      } else if (line.matches("find-employee-by-phone,.*")) {
         out.reset();
         out.writeObject(employeeService.findBySoDienThoai(Integer.parseInt(line.split(",")[1])));
      } else if (line.equals("add-employee")) {
         boolean result = employeeService.addEmployee((NhanVien) in.readObject());
         dos.writeBoolean(result);
      } else if (line.equals("update-employee")) {
         boolean result = employeeService.updateEmployee((NhanVien) in.readObject());
         dos.writeBoolean(result);
      } else if (line.matches("delete-employee,.*")) {
         dos.writeBoolean(employeeService.deleteEmployee(line.split(",")[1]));
      } else if (line.matches("count-employee,.*")) {
         dos.writeLong(employeeService.demSoLuongNhanVien(Integer.parseInt(line.split(",")[1])));
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   public void roomController(String line) {
      if (line.equals("add-room")) {
         Phong room = (Phong) in.readObject();
         dos.writeBoolean(roomService.addPhong(room));
      } else if (line.equals("update-room")) {
         Phong room = (Phong) in.readObject();
         dos.writeBoolean(roomService.updatePhong(room));
      } else if (line.matches("delete-room,.*")) {
         dos.writeBoolean(roomService.deletePhong(line.split(",")[1]));
      } else if (line.equals("find-all-room")) {
         out.reset();
         out.writeObject(roomService.findAll());
      } else if (line.matches("find-room,.*")) {
         String text = "";
         try {
            text = line.split(",")[1];
         } catch (Exception e) {
            text = "";
         }
         out.reset();
         out.writeObject(roomService.findByMaPhong(text));
      } else if (line.equals("find-room-by-type-status-capacity")) {
         List<Integer> arrType = (List<Integer>) in.readObject();
         List<Integer> arrStatus = (List<Integer>) in.readObject();
         int capacity = dis.readInt();
         out.reset();
         out.writeObject(roomService.findRoomByTypeStatusCapacity(arrType, arrStatus, capacity));
      } else if (line.matches("find-room-by-status,.*")) {
         out.reset();
         out.writeObject(roomService.findRoomByStatus(Integer.parseInt(line.split(",")[1])));
      } else if (line.matches("count-room-by-status,.*")) {
         dos.writeLong(roomService.countRoomStatus(Integer.parseInt(line.split(",")[1])));
      } else {
         out.writeObject(null);
      }
   }


   @SneakyThrows
   private void bookingTicketController(String line) {

      if (line.equals("find-all-booking-ticket")) {
         out.reset();
         out.writeObject(bookingTicketService.findAll());
      } else if (line.matches("find-booking-ticket,.*")) {
         out.reset();
         out.writeObject(bookingTicketService.findByMaPhieuDat(line.split(",")[1]));
      } else if (line.matches("find-booking-ticket-by-room-id,.*")) {
         out.reset();
         out.writeObject(bookingTicketService.findBookingTicketByRoomID(line.split(",")[1]));
      } else if (line.matches("find-booking-ticket-by-customer,.*")) {
         out.reset();
         out.writeObject(bookingTicketService.findBookingTicketByCustomerID(line.split(",")[1]));
      } else if (line.equals("find-all-booking-ticket-not-used")) {
         out.reset();
         out.writeObject(bookingTicketService.findAllBookingTicketNotUsed());
      } else if (line.equals("count-booking-ticket-in-date")) {
         dos.writeLong(bookingTicketService.countBookingTicketInDate((Instant) in.readObject()));
      } else if (line.equals("add-booking-ticket")) {
         PhieuDatPhong bookingTicket = (PhieuDatPhong) in.readObject();
         dos.writeBoolean(bookingTicketService.addPhieuDatPhong(bookingTicket));
      } else if (line.equals("update-booking-ticket")) {
         PhieuDatPhong bookingTicket = (PhieuDatPhong) in.readObject();
         dos.writeBoolean(bookingTicketService.updatePhieuDatPhong(bookingTicket));
      } else if (line.matches("delete-booking-ticket,.*")) {
         dos.writeBoolean(bookingTicketService.deletePhieuDatPhong(line.split(",")[1]));
      } else {
         out.writeObject(null);
      }
   }

   @SneakyThrows
   private void accountController(String line) {
      if (line.equals("login")) {
         TaiKhoan account = accountService.login((TaiKhoan) in.readObject());
         if (account != null) {
            dos.writeUTF("login-success");
            out.reset();
            out.writeObject(account);
         } else {
            dos.writeUTF("login-fail");
         }
      } else if (line.equals("add-account")) {
         boolean result = accountService.addTaiKhoan((TaiKhoan) in.readObject());
         dos.writeBoolean(result);
      } else if (line.equals("all-account")) {
         out.writeObject(accountService.findAll());
      } else if (line.equals("update-account")) {
         boolean result = accountService.updateTaiKhoan((TaiKhoan) in.readObject());
         dos.writeBoolean(result);
      } else if (line.matches("delete-account,.*")) {
         dos.writeBoolean(accountService.deleteTaiKhoan(line.split(",")[1]));
      } else {
         out.writeObject(null);
      }
   }
}