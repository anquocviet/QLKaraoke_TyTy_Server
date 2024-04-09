package repositories;

import entities.Phong;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public interface PhongRepository {
   public boolean addPhong();

   public boolean updatePhong();

   public boolean deletePhong();

   public List<Phong> findAll();

   public Phong findByMaPhong(String maPhong);
}
