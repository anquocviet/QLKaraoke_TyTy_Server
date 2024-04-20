package services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChiTietHD_DichVuServiceTest {
   private ChiTietHD_DichVuService ctService = null;

   @BeforeEach
   void setUp() {
      ctService = new ChiTietHD_DichVuService();
   }

   @AfterEach
   void tearDown() {
      ctService = null;
   }

   @Test
   void findByMaHoaDon() {
      ctService.findByMaHoaDon("HD05131223").forEach(System.out::println);
   }
}