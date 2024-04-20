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
        return em.createNamedQuery("PhieuDatPhong.findAll", PhieuDatPhong.class).getResultList();
   }

    @Override
    public List<PhieuDatPhong> findByMaPhieuDat(String maPhieuDat) {
        return em.createQuery("SELECT p FROM PhieuDatPhong p WHERE p.maPhieuDat LIKE :maPhieuDat", PhieuDatPhong.class)
                .setParameter("maPhieuDat", "%" + maPhieuDat + "%")
                .getResultList();
    }



    @Override
    public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        try {
            System.out.println("Persisting: " + phieuDatPhong);
            transaction.begin();
            em.persist(phieuDatPhong);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
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
        try {
             transaction.begin();
             PhieuDatPhong phieuDatPhong = em.find(PhieuDatPhong.class, maPhieuDat);
             if (phieuDatPhong == null) {
                return false;
             }
             em.remove(phieuDatPhong);
             transaction.commit();
             return true;
        } catch (Exception e) {
             transaction.rollback();
             return false;
        }
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
