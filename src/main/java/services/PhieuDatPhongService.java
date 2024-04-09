package services;

import entities.PhieuDatPhong;
import repositories.PhieuDatPhongRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class PhieuDatPhongService implements PhieuDatPhongRepository {
   @Override
   public List<PhieuDatPhong> findAll() {
      return List.of();
   }

   @Override
   public PhieuDatPhong findByMaPhieuDat(String maPhieuDat) {
      return null;
   }

   @Override
   public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
      return false;
   }

   @Override
   public boolean updatePhieuDatPhong(PhieuDatPhong phieuDatPhong) {
      return false;
   }

   @Override
   public boolean deletePhieuDatPhong(String maPhieuDat) {
      return false;
   }
}
