package services;

import entities.CT_KhuyenMai;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.CT_KhuyenMaiRepository;

import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CT_KhuyenMaiServiceTest {
    CT_KhuyenMaiRepository service = null;
    @BeforeEach
    void setUp() {
        service = new CT_KhuyenMaiService();
    }

    @AfterEach
    void tearDown() {
         service = null;
    }

    @Test
    void findAll() {
    }

    @Test
    void addCT_KhuyenMai() {
//        LocalDate ngayKetThuc = LocalDate.of(2021, 12, 31);
        CT_KhuyenMai ct_khuyenMai = new CT_KhuyenMai("KM01", "Khuyen mai 1", Instant.now(), Instant.now(), 30, 40);
        CT_KhuyenMaiService ct_khuyenMaiService = new CT_KhuyenMaiService();
        boolean result = ct_khuyenMaiService.addCT_KhuyenMai(ct_khuyenMai);
        assertTrue(result);

    }

    @Test
    void updateCT_KhuyenMai() {
        CT_KhuyenMai ct_khuyenMai = new CT_KhuyenMai("KM01", "Khuyen mai 2222222", Instant.now(), Instant.now(), 30, 40);
        CT_KhuyenMaiService ct_khuyenMaiService = new CT_KhuyenMaiService();
        boolean result = ct_khuyenMaiService.updateCT_KhuyenMai(ct_khuyenMai);
        assertTrue(result);
    }

    @Test
    void deleteCT_KhuyenMai() {
        CT_KhuyenMai ct_khuyenMai = new CT_KhuyenMai("KM01", "Khuyen mai 2222222", Instant.now(), Instant.now(), 30, 40);
        CT_KhuyenMaiService ct_khuyenMaiService = new CT_KhuyenMaiService();
        boolean result = ct_khuyenMaiService.deleteCT_KhuyenMai("KM01");
        assertTrue(result);
    }


    @Test
    void findByMaKhuyenMai() {
    }

    @Test
    void findByTenKhuyenMai() {
    }

    @Test
    void testFindByMaKhuyenMai() {
        service.findByMaKhuyenMai("").forEach(System.out::println);
    }
}