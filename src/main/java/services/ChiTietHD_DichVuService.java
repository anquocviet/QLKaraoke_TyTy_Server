package services;

import entities.ChiTietHD_DichVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import repositories.ChiTietHD_DichVuRepository;

import java.util.ArrayList;
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
         ct.setHoaDon(em.merge(ct.getHoaDon()));
         ct.setDichVu(em.merge(ct.getDichVu()));
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
         ct.setId(em.merge(ct).getId());
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
      List<ChiTietHD_DichVu> list = em.createNamedQuery("ChiTietHD_DichVu.findByMaHoaDon")
                        .setParameter("maHoaDon", maHoaDon)
                        .getResultList();
      return list == null ? new ArrayList<>() : list;
   }

   @Override
   public List<ChiTietHD_DichVu> findByMaDichVu(String maDichVu) {
      return em.createNamedQuery("ChiTietHD_DichVu.findByMaDichVu", ChiTietHD_DichVu.class)
                   .setParameter("maDichVu", maDichVu)
                   .getResultList();
   }
}
