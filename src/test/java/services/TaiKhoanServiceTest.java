package services;

import entities.NhanVien;
import entities.TaiKhoan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
   }

   @Test
   void findAll() {
   }

   @Test
   void findByName() {
   }

   @Test
   void findByMaNhanVien() {
   }

   @Test
   void addTaiKhoan() {
   }

   @Test
   void updateTaiKhoan() {
   }

   @Test
   void deleteTaiKhoan() {
   }

   @Test
   void login() {
      NhanVien nhanVien = new NhanVien();
      nhanVien.setMaNhanVien("NV0103");
      TaiKhoan taiKhoan = new TaiKhoan("TK0001", "anquocviet", "123", nhanVien);
      System.out.println(taiKhoanService.login(taiKhoan).getNhanVien());
   }
}