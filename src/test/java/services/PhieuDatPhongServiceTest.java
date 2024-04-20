package services;

import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuDatPhong;
import entities.Phong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhieuDatPhongServiceTest {
    private PhieuDatPhongService service;

   @BeforeEach
   void setUp() {
      service = new PhieuDatPhongService();
   }

   @AfterEach
   void tearDown() {
      service = null;
   }


   @Test
   void findAll() {
        for (PhieuDatPhong phieuDatPhong : service.findAll()) {
             System.out.println(phieuDatPhong);
        }
   }

    @Test
    void findByMaPhieuDat() {
        // Arrange
        String maPhieuDat = "0723"; // replace with a maPhieuDat that exists in your database

        // Act
        List<PhieuDatPhong> result = service.findByMaPhieuDat(maPhieuDat);

        // Assert
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy PhieuDatPhong với mã " + maPhieuDat);
        } else {
            System.out.println("Tìm thấy PhieuDatPhong với mã " + maPhieuDat);
            result.forEach(phieuDatPhong -> {
                assertTrue(phieuDatPhong.getMaPhieuDat().contains(maPhieuDat), "Mã Phiếu Đặt không khớp");
                System.out.println(phieuDatPhong);
            });
        }
    }

    @Test
    void addPhieuDatPhong() {
        // Create instances of Phong, KhachHang, and NhanVien
        Phong phong = new Phong("P102");
        KhachHang khachHang = new KhachHang("KH0008");
        NhanVien nhanVien = new NhanVien("NV0103");

        // Create a PhieuDatPhong instance
        PhieuDatPhong phieuDatPhong = new PhieuDatPhong("PD21121523",phong, khachHang, nhanVien, Instant.now(), Instant.now(),"GhiChu",1);

        boolean isAdded = service.addPhieuDatPhong(phieuDatPhong);
        if (isAdded) {
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }

    }

    @Test
    void updatePhieuDatPhong() {
        // Retrieve the PhieuDatPhong object from the database
        PhieuDatPhong phieuDatPhong = service.findByMaPhieuDat("PD21121523").stream().findFirst().orElse(null);        if (phieuDatPhong != null) {
            // Make necessary changes
            // For example, let's change the "GhiChu" field
            phieuDatPhong.setGhiChu("New GhiChu");

            // Update the PhieuDatPhong object
            boolean isUpdated = service.updatePhieuDatPhong(phieuDatPhong);
            if (isUpdated) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Cập nhật thất bại");
            }
        } else {
            System.out.println("Không tìm thấy PhieuDatPhong với mã PD27121323");
        }
    }

    @Test
    void deletePhieuDatPhong() {
        boolean isDeleted = service.deletePhieuDatPhong("PD21121523");
        if (isDeleted) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Không tìm thấy");
        }
    }
}
