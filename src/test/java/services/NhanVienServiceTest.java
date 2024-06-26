package services;

import entities.NhanVien;
import enums.Enum_ChucVu;
import enums.Enum_TrangThaiLamViec;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NhanVienServiceTest {

    @Test
    void findAll() {
        List<NhanVien> nhanViens = new NhanVienService().findAll();
        for (NhanVien nhanVien : nhanViens) {
            System.out.println(nhanVien);
        }
    }

    @Test
    void findByMaNhanVien() {
        List<NhanVien> list = new NhanVienService().findByMaNhanVien("NV0503");
        list.forEach(System.out::println);
    }

    @Test
    void findByHoTen() {
        NhanVien nhanVien = new NhanVienService().findByHoTen("Nguyễn Văn Thạch").getFirst();
        System.out.println(nhanVien);
    }

    @Test
    void findByCCCD() {
    }

    @Test
    void findBySoDienThoai() {
        List<NhanVien> list = new NhanVienService().findBySoDienThoai(912345678);
         list.forEach(System.out::println);
    }

    @Test
    void addEmployee() {
        boolean result = new NhanVienService().addEmployee(new NhanVien("NV0505", "Nguyễn Hồng Sơn", "123456789", 912345678,
                Instant.now(), "Long an",1, Enum_ChucVu.BAOVE, Enum_TrangThaiLamViec.CONLAMVIEC, "anhdaidien"));
        System.out.println(result);
    }

    @Test
    void updateEmployee() {
        boolean result = new NhanVienService().updateEmployee(new NhanVien("NV0505", "Nguyễn Hồng Sơn", "123456789", 912345678,
                Instant.now(), "Long an",1, Enum_ChucVu.BAOVE, Enum_TrangThaiLamViec.CONLAMVIEC, "anhdaidienupdate"));
        System.out.println(result);
    }

    @Test
    void deleteEmployee() {
        boolean result = new NhanVienService().deleteEmployee("NV0505");
        System.out.println(result);
    }

    @Test
    void demSoLuongNhanVien() {
        Long result = new NhanVienService().demSoLuongNhanVien(2003);
        System.out.println(result);
    }
}