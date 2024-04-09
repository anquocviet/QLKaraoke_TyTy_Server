package services;

import entities.ChiTietHD_Phong;
import repositories.ChiTietHD_PhongRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class ChiTietHD_PhongService implements ChiTietHD_PhongRepository {
   @Override
   public List<ChiTietHD_Phong> getCTPhongTheoMaHD(String maHD) {
      return List.of();
   }

   @Override
   public boolean addChiTietHD_Phong(ChiTietHD_Phong ct) {
      return false;
   }

   @Override
   public boolean updateChiTietHD_Phong(ChiTietHD_Phong ct) {
      return false;
   }

   @Override
   public boolean deleteChiTietHD_Phong(ChiTietHD_Phong ct) {
      return false;
   }

   @Override
   public List<ChiTietHD_Phong> findByMaHoaDon(String maHoaDon) {
      return List.of();
   }

   @Override
   public List<ChiTietHD_Phong> findByMaPhong(String maPhong) {
      return List.of();
   }

   @Override
   public ChiTietHD_Phong findByMaPhongAndMaHoaDon(String maHoaDon, String maPhong) {
      return null;
   }

   @Override
   public List<ChiTietHD_Phong> findByDate(String start, String end) {
      return List.of();
   }

   @Override
   public long calcTotalHoursOfUseOfCustomer(String maKhachHang) {
      return 0;
   }
}
