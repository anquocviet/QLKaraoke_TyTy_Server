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

}