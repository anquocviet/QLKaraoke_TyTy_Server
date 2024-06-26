package services;

import entities.Phong;
import enums.Enum_LoaiPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import repositories.PhongRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class PhongService implements PhongRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public PhongService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   @Override
   public boolean addPhong(Phong room) {
      EntityTransaction transaction = null;
      try {
         transaction = em.getTransaction();
         transaction.begin();
         em.persist(room); // Thêm phòng mới vào cơ sở dữ liệu
         transaction.commit();
         return true;
      } catch (Exception e) {
         if (transaction != null && transaction.isActive()) {
            transaction.rollback();
         }
         e.printStackTrace();
         return false;
      }

   }

   @Override
   public boolean updatePhong(Phong room) {
      EntityTransaction transaction = em.getTransaction();
      try {
         transaction.begin();
         em.merge(room);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         return false;
      }
   }

   @Override
   public boolean deletePhong(String idRoom) {
      EntityTransaction transaction = em.getTransaction();
      try {
         transaction.begin();
         Phong room = em.find(Phong.class, idRoom);
         em.remove(room);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         return false;
      }
   }

   @Override
   public List<Phong> findAll() {

      return em.createNamedQuery("Phong.findAll").getResultList();
   }

   @Override
   public List<Phong> findByMaPhong(String maPhong) {
      return em.createNamedQuery("Phong.findByMaPhong", Phong.class)
                   .setParameter("maPhong", "%" + maPhong + "%")
                   .getResultStream()
                   .toList();
   }

   @Override
   public Long countRoomStatus(int status) {
      return em.createNamedQuery("Phong.countRoomStatus", Long.class)
                   .setParameter("status", status)
                   .getSingleResult();
   }

   @Override
   public List<Phong> findRoomByTypeStatusCapacity(List<Integer> arrType, List<Integer> arrStatus, int capacity) {
      List<Enum_LoaiPhong> arrTypeEnum = Enum_LoaiPhong.getEnumList(arrType);
      return em.createNamedQuery("Phong.findRoomByTypeStatusCapacity", Phong.class)
                   .setParameter("arrType", arrTypeEnum)
                   .setParameter("arrStatus", arrStatus)
                   .setParameter("capacity", capacity)
                   .getResultList();
   }

   @Override
   public List<Phong> findRoomByStatus(int status) {
      return em.createNamedQuery("Phong.findRoomByStatus", Phong.class)
                   .setParameter("status", status)
                   .getResultList();
   }
}
