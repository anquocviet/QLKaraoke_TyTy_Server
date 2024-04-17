package services;

import entities.PhieuDatPhong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repositories.PhieuDatPhongRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class PhieuDatPhongService implements PhieuDatPhongRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";
   private final EntityTransaction transaction;

   public PhieuDatPhongService() {
      em = jakarta.persistence.Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
      transaction = em.getTransaction();
   }

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
      try {
         transaction.begin();
         em.merge(phieuDatPhong);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         return false;
      }
   }

   @Override
   public boolean deletePhieuDatPhong(String maPhieuDat) {
      return false;
   }

   @Override
   public PhieuDatPhong findBookingTicketByRoomID(String roomID) {
      return em.createNamedQuery("PhieuDatPhong.findBookingTicketByRoomID", PhieuDatPhong.class)
                   .setParameter("roomID", roomID)
                   .getResultStream()
                   .findFirst()
                   .orElse(null);
   }
}
