package services;

import entities.NhanVien;
import repositories.NhanVienRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class NhanVienService implements NhanVienRepository {
   @Override
   public List<NhanVien> findAll() {
      return List.of();
   }

   @Override
   public NhanVien findByMaNhanVien(String maNhanVien) {
      return null;
   }

   @Override
   public NhanVien findByHoTen(String hoTen) {
      return null;
   }

   @Override
   public NhanVien findByCCCD(String cccd) {
      return null;
   }

   @Override
   public NhanVien findBySoDienThoai(String soDienThoai) {
      return null;
   }

   @Override
   public boolean addEmployee(NhanVien nhanVien) {
      return false;
   }

   @Override
   public boolean updateEmployee(NhanVien nhanVien) {
      return false;
   }

   @Override
   public boolean deleteEmployee(String maNhanVien) {
      return false;
   }
}
