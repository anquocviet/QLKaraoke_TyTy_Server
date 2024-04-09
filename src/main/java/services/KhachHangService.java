package services;

import entities.KhachHang;
import repositories.KhachHangRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class KhachHangService implements KhachHangRepository {
   @Override
   public List<KhachHang> findAll() {
      return List.of();
   }

   @Override
   public KhachHang findByMaKhachHang(String maKhachHang) {
      return null;
   }

   @Override
   public KhachHang findByTenKhachHang(String tenKhachHang) {
      return null;
   }

   @Override
   public KhachHang findBySoDienThoai(Integer soDienThoai) {
      return null;
   }

   @Override
   public boolean addCustomer(KhachHang khachHang) {
      return false;
   }

   @Override
   public boolean updateCustomer(KhachHang khachHang) {
      return false;
   }

   @Override
   public boolean deleteCustomer(KhachHang khachHang) {
      return false;
   }
}
