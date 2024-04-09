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

   public NhanVien findByMaNhanVien(String maNhanVien);

   public NhanVien findByHoTen(String hoTen);

   public NhanVien findByCCCD(String cccd);

   public NhanVien findBySoDienThoai(String soDienThoai);

   public boolean addEmployee(NhanVien nhanVien);

   public boolean updateEmployee(NhanVien nhanVien);

   public boolean deleteEmployee(String maNhanVien);
}
