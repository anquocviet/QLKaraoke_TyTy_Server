package services;

import entities.CT_KhuyenMai;
import repositories.CT_KhuyenMaiRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class CT_KhuyenMaiService implements CT_KhuyenMaiRepository {
   @Override
   public List<CT_KhuyenMai> findAll() {
      return List.of();
   }

   @Override
   public boolean addCT_KhuyenMai(CT_KhuyenMai ct_khuyenMai) {
      return false;
   }

   @Override
   public boolean updateCT_KhuyenMai(CT_KhuyenMai ct_khuyenMai) {
      return false;
   }

   @Override
   public boolean deleteCT_KhuyenMai(String maKhuyenMai) {
      return false;
   }

   @Override
   public CT_KhuyenMai findByMaKhuyenMai(String maKhuyenMai) {
      return null;
   }

   @Override
   public CT_KhuyenMai findByTenKhuyenMai(String tenKhuyenMai) {
      return null;
   }

   @Override
   public boolean updateLuotSuDungConLai(String maKhuyenMai, int luotSuDungConLai) {
      return false;
   }
}
