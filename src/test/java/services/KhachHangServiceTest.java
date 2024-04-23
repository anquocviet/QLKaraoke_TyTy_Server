package services;

import entities.KhachHang;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KhachHangServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<KhachHang> khachHangs = new KhachHangService().findAll();
        for (KhachHang khachHang : khachHangs) {
            System.out.println(khachHang);
        }
        assertNotNull(khachHangs);
    }

    @Test
    void findByMaKhachHang() {
//        KhachHang(maKhachHang=KH0013, tenKhachHang=Đỗ Văn Tuấn, soDienThoai=355241428, namSinh=1995, gioiTinh=1)
        List<KhachHang> khachHangs = (List<KhachHang>) new KhachHangService().findByMaKhachHang("KH001");
        for (KhachHang khachHang : khachHangs) {
            System.out.println(khachHang);
        }
        assertNotNull(khachHangs);
    }

    @Test
    void findByTenKhachHang() {
        List<KhachHang> khachHangs = (List<KhachHang>) new KhachHangService().findByTenKhachHang("Văn");
        for (KhachHang khachHang : khachHangs) {
            System.out.println(khachHang);
        }
        assertNotNull(khachHangs);
    }

    @Test
    void findBySoDienThoai() {
        List<KhachHang> khachHangs = (List<KhachHang>) new KhachHangService().findBySoDienThoai("355241429");
        for (KhachHang khachHang : khachHangs) {
            System.out.println(khachHang);
        }
        assertNotNull(khachHangs);
    }

    @Test
    void addCustomer() {
        KhachHang khachHang = new KhachHang("", "Vũ Phi", 345678921, 2003, 1);
        boolean result = new KhachHangService().addCustomer(khachHang);
        System.out.println(result);
        assertTrue(result);
    }

    @Test
    void updateCustomer() {
        KhachHang khachHang = new KhachHang("KH0026", "Đỗ ", 355241428, 1995, 1);
        boolean result = new KhachHangService().updateCustomer(khachHang);
        System.out.println(result);
        assertTrue(result);
    }


}