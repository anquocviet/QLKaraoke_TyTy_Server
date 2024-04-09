package services;

import entities.Phong;
import repositories.PhongRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class PhongService implements PhongRepository {
   @Override
   public boolean addPhong() {
      return false;
   }

   @Override
   public boolean updatePhong() {
      return false;
   }

   @Override
   public boolean deletePhong() {
      return false;
   }

   @Override
   public List<Phong> findAll() {
      return List.of();
   }

   @Override
   public Phong findByMaPhong(String maPhong) {
      return null;
   }
}
