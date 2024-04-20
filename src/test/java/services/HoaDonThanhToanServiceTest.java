package services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HoaDonThanhToanServiceTest {
   private HoaDonThanhToanService service;

   @BeforeEach
   void setUp() {
      service = new HoaDonThanhToanService();
   }

   @AfterEach
   void tearDown() {
      service = null;
   }

   @Test
   void findAll() {
      service.findAll().forEach(System.out::println);
   }


   @Test
   void findByRoomUsing() {
      System.out.println(service.findByRoomUsing("P104VIP").getKhachHang());
   }
}