package repositories;

import entities.Phong;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public interface PhongRepository {
   public boolean addPhong(Phong room);

   public boolean updatePhong(Phong room);

   public boolean deletePhong(String idRoom);

   public List<Phong> findAll();

   public List<Phong> findByMaPhong(String maPhong);

   public Long countRoomStatus(int status);

   public List<Phong> findRoomByTypeStatusCapacity(List<Integer> arrType, List<Integer> arrStatus, int capacity);

   public List<Phong> findRoomByStatus(int status);
}
