package services;

import entities.CT_KhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import repositories.CT_KhuyenMaiRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class CT_KhuyenMaiService implements CT_KhuyenMaiRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";
   EntityTransaction transaction = null;

    public CT_KhuyenMaiService() {
         em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
         transaction = em.getTransaction();
    }

    @Override
   public List<CT_KhuyenMai> findAll() {
      return List.of();
   }

   @Override
   public boolean addCT_KhuyenMai(CT_KhuyenMai ct_khuyenMai) {
      EntityTransaction transaction = em.getTransaction();
      try {

         transaction.begin();
         em.persist(ct_khuyenMai);
         transaction.commit();
         return true;
      } catch (Exception e) {
         if (transaction.isActive()) {
            transaction.rollback();
         }
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean updateCT_KhuyenMai(CT_KhuyenMai ct_khuyenMai) {
      EntityTransaction transaction = em.getTransaction();
        try {
             transaction.begin();
             if (em.find(CT_KhuyenMai.class, ct_khuyenMai.getMaKhuyenMai()) == null) {
                 return false;
             }
             em.merge(ct_khuyenMai);
             transaction.commit();
             return true;
        } catch (Exception e) {
             if (transaction.isActive()) {
                transaction.rollback();
             }
             e.printStackTrace();
             return false;
        }

   }

   @Override
   public boolean deleteCT_KhuyenMai(String maKhuyenMai) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CT_KhuyenMai ct_khuyenMai = em.find(CT_KhuyenMai.class, maKhuyenMai);
            if (ct_khuyenMai == null) {
                return false;
            }
            em.remove(ct_khuyenMai);
            transaction.commit();
            return true;
        } catch (Exception e) {
           if (transaction.isActive()) {
              transaction.rollback();
           }
           e.printStackTrace();
        }
      return false;
   }

   @Override
   public List<CT_KhuyenMai> findByMaKhuyenMai(String maKhuyenMai) {
      transaction = em.getTransaction();
      return em.createNamedQuery("CT_KhuyenMai.findByMaKhuyenMai", CT_KhuyenMai.class)
               .setParameter("maKhuyenMai", "%" + maKhuyenMai + "%")
               .getResultList();
   }

   @Override
   public List<CT_KhuyenMai> findByTenKhuyenMai(String tenKhuyenMai) {
      transaction = em.getTransaction();
        try {
             transaction.begin();
             List<CT_KhuyenMai> ct_khuyenMais = em.createNamedQuery("CT_KhuyenMai.findByTenKhuyenMai", CT_KhuyenMai.class)
                     .setParameter("tenKhuyenMai", "%" + tenKhuyenMai + "%")
                     .getResultList();
             transaction.commit();
             return ct_khuyenMais;
        } catch (Exception e) {
           transaction.rollback();
           e.printStackTrace();
        }
        return List.of();
   }


}
