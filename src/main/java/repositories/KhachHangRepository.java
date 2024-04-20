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

   public List<KhachHang> findByMaKhachHang(String maKhachHang);

   public List<KhachHang> findByTenKhachHang(String tenKhachHang) ;

   public List<KhachHang> findBySoDienThoai(String soDienThoai);

   public boolean addCustomer(KhachHang khachHang);

   public boolean updateCustomer(KhachHang khachHang);

}
