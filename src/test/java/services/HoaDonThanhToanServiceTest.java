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
   void addHoaDonThanhToan() {
   }

   @Test
   void updateHoaDonThanhToan() {
   }

   @Test
   void deleteHoaDonThanhToan() {
   }

   @Test
   void findAll() {
      service.findAll().forEach(System.out::println);
   }

   @Test
   void findBill() {
   }

   @Test
   void findByRoomID() {
   }

   @Test
   void findByCustomerID() {
   }

   @Test
   void findByEmployeeID() {
   }

   @Test
   void countBill() {
   }

   @Test
   void testCountBill() {
   }

   @Test
   void getBillsByDate() {
   }

   @Test
   void calcMoney() {
   }

   @Test
   void testCalcMoney() {
   }

   @Test
   void testCalcMoney1() {
   }
}