package services;

import entities.DichVu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DichVuServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addDichVu() {
        DichVu dv = new DichVu("DV01", "Dich vu 1", 10, "Ly", 10000, "anh1.jpg");
        DichVuService dichVuService = new DichVuService();
        boolean result = dichVuService.addDichVu(dv);
        assertTrue(result);
    }


    @Test
    void updateDichVu() {
        DichVu dv = new DichVu("DV01", "Dich vuttest 1", 10, "Lytest", 10000, "anhtest.jpg");
        DichVuService dichVuService = new DichVuService();
        boolean result = dichVuService.updateDichVu(dv);
        assertTrue(result);
    }

    @Test
    void findAllDichVu() {
        List<DichVu> dichVus = new DichVuService().findAllDichVu();
        for (DichVu dichVu : dichVus) {
            System.out.println(dichVu);
        }
        assertNotNull(dichVus);
    }

    @Test
    void findDichVuById() {
        List<DichVu> dichVus = new DichVuService().findDichVuById("DV01");
        for (DichVu dichVu : dichVus) {
            System.out.println(dichVu);
        }
        assertNotNull(dichVus);
    }

    @Test
    void findListDichVuByMaHoaDon() {
        List<DichVu> dichVus = new DichVuService().findListDichVuByMaHoaDon("HD001");
        for (DichVu dichVu : dichVus) {
            System.out.println(dichVu);
        }
        assertNotNull(dichVus);
    }

    @Test
    void countDichVu() {
        Long count = new DichVuService().countDichVu();
        System.out.println(count);
        assertTrue(count > 0);
    }
}