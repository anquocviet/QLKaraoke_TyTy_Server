package services;

import entities.ChiTietHD_DichVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import repositories.ChiTietHD_DichVuRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class ChiTietHD_DichVuService implements ChiTietHD_DichVuRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";
   EntityTransaction transaction = null;

   public ChiTietHD_DichVuService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
      transaction = em.getTransaction();
   }

   @Override
   public boolean addChiTietHD_DichVu(ChiTietHD_DichVu ct) {
      try {
         transaction.begin();
         em.persist(ct);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public boolean updateChiTietHD_DichVu(ChiTietHD_DichVu ct) {
      try {
         transaction.begin();
         if (em.find(ChiTietHD_DichVu.class, ct.getId()) == null) {
            return false;
         }
         em.merge(ct);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public boolean deleteChiTietHD_DichVu(ChiTietHD_DichVu ct) {
      try {
         transaction.begin();
         em.remove(ct);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public List<ChiTietHD_DichVu> findByMaHoaDon(String maHoaDon) {
      return em.createNamedQuery("ChiTietHD_DichVu.findByMaHoaDon", ChiTietHD_DichVu.class)
                   .setParameter("maHoaDon", maHoaDon)
                   .getResultStream()
                   .toList();
   }

   @Override
   public List<ChiTietHD_DichVu> findByMaDichVu(String maDichVu) {
      return em.createNamedQuery("ChiTietHD_DichVu.findByMaDichVu", ChiTietHD_DichVu.class)
                   .setParameter("maDichVu", maDichVu)
                   .getResultStream()
                   .toList();
   }
}
