package services;

import entities.TaiKhoan;
import repositories.TaiKhoanRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class TaiKhoanService implements TaiKhoanRepository {
   @Override
   public TaiKhoan findByMaTaiKhoan(String maTaiKhoan) {
      return null;
   }

   @Override
   public List<TaiKhoan> findAll() {
      return List.of();
   }

   @Override
   public TaiKhoan findByName(String tenDangNhap) {
      return null;
   }

   @Override
   public TaiKhoan findByMaNhanVien(String maNhanVien) {
      return null;
   }

   @Override
   public boolean addTaiKhoan(TaiKhoan taiKhoan) {
      return false;
   }

   @Override
   public boolean updateTaiKhoan(TaiKhoan taiKhoan) {
      return false;
   }

   @Override
   public boolean deleteTaiKhoan(String maTaiKhoan) {
      return false;
   }
}
