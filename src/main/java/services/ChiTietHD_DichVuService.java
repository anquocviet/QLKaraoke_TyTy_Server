package services;

import repositories.ChiTietHD_DichVuRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class ChiTietHD_DichVuService implements ChiTietHD_DichVuRepository {
   @Override
   public List<entities.ChiTietHD_DichVu> getCTDichVuTheoMaHD(String maHD) {
      return List.of();
   }

   @Override
   public boolean addChiTietHD_DichVu(entities.ChiTietHD_DichVu ct) {
      return false;
   }

   @Override
   public boolean updateChiTietHD_DichVu(entities.ChiTietHD_DichVu ct) {
      return false;
   }

   @Override
   public boolean deleteChiTietHD_DichVu(entities.ChiTietHD_DichVu ct) {
      return false;
   }

   @Override
   public List<entities.ChiTietHD_DichVu> findByMaHoaDon(String maHoaDon) {
      return List.of();
   }

   @Override
   public List<entities.ChiTietHD_DichVu> findByMaDichVu(String maDichVu) {
      return List.of();
   }
}
