package services;

import entities.NhanVien;
import entities.TaiKhoan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaiKhoanServiceTest {
   TaiKhoanService taiKhoanService;

   @BeforeEach
   void setUp() {
      taiKhoanService = new TaiKhoanService();
   }

   @AfterEach
   void tearDown() {
      taiKhoanService.close();
   }

   @Test
   void findByMaTaiKhoan() {
        TaiKhoan taiKhoan = taiKhoanService.findByMaTaiKhoan("TK0001");
       System.out.println(taiKhoan);
   }

   @Test
   void findAll() {
       List<TaiKhoan> taiKhoans = taiKhoanService.findAll();
       taiKhoans.forEach(System.out::println);
   }

   @Test
   void findByName() {
        TaiKhoan taiKhoan = taiKhoanService.findByName("anquocviet");
        System.out.println(taiKhoan);
   }

   @Test
   void findByMaNhanVien() {
        TaiKhoan taiKhoan = taiKhoanService.findByMaNhanVien("NV0103");
        System.out.println(taiKhoan);
   }

   @Test
   void addTaiKhoan() {
        TaiKhoan taiKhoan = new TaiKhoan("TK0009", "nguyenhongson", "123",
                new NhanVien("NV0503"));
       boolean result = taiKhoanService.addTaiKhoan(taiKhoan);
       System.out.println(result);
   }

   @Test
   void updateTaiKhoan() {
        TaiKhoan taiKhoan = taiKhoanService.findByMaTaiKhoan("TK0009");
        taiKhoan.setMatKhau("123456");
        boolean result = taiKhoanService.updateTaiKhoan(taiKhoan);
        System.out.println(result);
   }

   @Test
   void deleteTaiKhoan() {
        TaiKhoan taiKhoan = taiKhoanService.findByMaTaiKhoan("TK0009");
        boolean result = taiKhoanService.deleteTaiKhoan(taiKhoan.getMaTaiKhoan());
        System.out.println(result);
   }

   @Test
   void login() {
      NhanVien nhanVien = new NhanVien();
      nhanVien.setMaNhanVien("TK0009");
      TaiKhoan taiKhoan = new TaiKhoan("TK0001", "anquocviet", "123", nhanVien);
      System.out.println(taiKhoanService.login(taiKhoan).getNhanVien());
   }

}