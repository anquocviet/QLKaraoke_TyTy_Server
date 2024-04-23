package repositories;

import entities.NhanVien;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface NhanVienRepository {
   public List<NhanVien> findAll();

   public List<NhanVien> findByMaNhanVien(String maNhanVien);

   public List<NhanVien> findByHoTen(String hoTen);

   public List<NhanVien> findByCCCD(String cccd);

   public List<NhanVien> findBySoDienThoai(int soDienThoai);

   public boolean addEmployee(NhanVien nhanVien);

   public boolean updateEmployee(NhanVien nhanVien);

   public boolean deleteEmployee(String maNhanVien);
   public Long demSoLuongNhanVien(int year);
}
