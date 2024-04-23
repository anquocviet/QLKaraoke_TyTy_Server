package services;

import entities.HoaDonThanhToan;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

   @Test
   void findByCustomerID() {
      List<HoaDonThanhToan> list = service.findByCustomerID("KH0019");
      for (HoaDonThanhToan hoaDonThanhToan : list) {
         System.out.println(hoaDonThanhToan);
      }
      assertNotNull(list);
   }

   @Test
   void countByDate() {
//        2023-11-28 18:01:08
      Instant date = Instant.parse("2023-04-28T18:01:08Z");
      System.out.println(service.countByDate(date));

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
   void findBill() {
   }

   @Test
   void testFindBill() {
   }

   @Test
   void findByCustomerUsingRoom() {
   }

   @Test
   void findByEmployeeID() {
   }

   @Test
   void findBillByCustomerNotPay() {
      String customerID = "KH0019";
      List<HoaDonThanhToan> list = service.findBillByCustomerNotPay(customerID);
      list.forEach(System.out::println);
      System.out.println(list.isEmpty());
   }

   @Test
   void countBill() {
   }

   @Test
   void testCountBill() {
   }

   @Test
   void countBillByDate() {
      System.out.println(service.countBillByDate(Instant.now()));
   }

   @Test
   void getBillsByDate() {
      service.getBillsByDate(Instant.now(), "day")
            .forEach((key, value) -> System.out.println(key + " : " + value));
      System.out.println(service.getBillsByDate(Instant.now(), "day").get(23));
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

   @Test
   void findByPhoneCustomer() {
      List<HoaDonThanhToan> list = service.findByPhoneCustomer("39");
      list.forEach(System.out::println);
   }

   @Test
   void findByNameCustomer() {
      List<HoaDonThanhToan> list = service.findByNameCustomer("Thi");
      list.forEach(System.out::println);
   }

   @Test
   void findByDate() {
      Instant date = Instant.now();
      List<HoaDonThanhToan> list = service.findByDate(date);
      list.forEach(System.out::println);
   }
}