package repositories;

import entities.KhachHang;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface KhachHangRepository {
   public List<KhachHang> findAll();

   public KhachHang findByMaKhachHang(String maKhachHang);

   public KhachHang findByTenKhachHang(String tenKhachHang);

   public KhachHang findBySoDienThoai(Integer soDienThoai);

   public boolean addCustomer(KhachHang khachHang);

   public boolean updateCustomer(KhachHang khachHang);

   public boolean deleteCustomer(KhachHang khachHang);
}
