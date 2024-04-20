package services;

import entities.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import repositories.TaiKhoanRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class TaiKhoanService implements TaiKhoanRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public TaiKhoanService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   @Override
   public TaiKhoan findByMaTaiKhoan(String maTaiKhoan) {
      return em.find(TaiKhoan.class, maTaiKhoan);
   }

   @Override
   public List<TaiKhoan> findAll() {
        return em.createNamedQuery("TaiKhoan.findAll").getResultList();
   }

   @Override
   public TaiKhoan findByName(String tenDangNhap) {
      Query query = em.createQuery("SELECT t FROM TaiKhoan t WHERE t.tenDangNhap = :tenDangNhap");
      query.setParameter("tenDangNhap", tenDangNhap);
      List<TaiKhoan> resultList = query.getResultList();
      if (!resultList.isEmpty()) {
         return resultList.get(0);
      } else {
         return null;
      }
   }

   @Override
   public TaiKhoan findByMaNhanVien(String maNhanVien) {
        return (TaiKhoan) em.createNamedQuery("TaiKhoan.findByMaNhanVien")
                            .setParameter("maNhanVien", maNhanVien)
                            .getResultStream()
                            .findFirst().orElse(null);
   }

   @Override
   public boolean addTaiKhoan(TaiKhoan taiKhoan) {
        try {
             em.getTransaction().begin();
             em.persist(taiKhoan);
             em.getTransaction().commit();
             return true;
        } catch (Exception e) {
             em.getTransaction().rollback();
             return false;
        }
   }

   @Override
   public boolean updateTaiKhoan(TaiKhoan taiKhoan) {
      EntityTransaction transaction = null;
      try {
         transaction = em.getTransaction();
         transaction.begin();
         em.merge(taiKhoan); // Cập nhật thông tin tài khoản
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
   public boolean deleteTaiKhoan(String maTaiKhoan) {
        EntityTransaction transaction = null;
        try {
             transaction = em.getTransaction();
             transaction.begin();
             TaiKhoan taiKhoan = em.find(TaiKhoan.class, maTaiKhoan);
             em.remove(taiKhoan);
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
   public TaiKhoan login(TaiKhoan account) {
      return (TaiKhoan) em.createNamedQuery("TaiKhoan.login")
                              .setParameter("tenDangNhap", account.getTenDangNhap())
                              .setParameter("matKhau", account.getMatKhau())
                              .getSingleResult();
   }

   public void close() {
      em.close();
   }
}
