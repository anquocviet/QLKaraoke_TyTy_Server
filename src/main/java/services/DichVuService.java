package services;

import entities.DichVu;
import repositories.DichVuRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class DichVuService implements DichVuRepository {
   @Override
   public boolean addDichVu(DichVu dv) {
      return false;
   }

   @Override
   public boolean updateDichVu(DichVu dv) {
      return false;
   }

   @Override
   public boolean deleteDichVu(DichVu dv) {
      return false;
   }

   @Override
   public List<DichVu> findAllDichVu() {
      return List.of();
   }

   @Override
   public DichVu findDichVuById(String maDichVu) {
      return null;
   }

   @Override
   public int countDichVu() {
      return 0;
   }
}
