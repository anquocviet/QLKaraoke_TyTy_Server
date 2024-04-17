package services;

import entities.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
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
      return null;
   }

   @Override
   public List<TaiKhoan> findAll() {
      return List.of();
   }

   @Override
   public TaiKhoan findByName(String tenDangNhap) {
      return null;
   }

   @Override
   public TaiKhoan findByMaNhanVien(String maNhanVien) {
      return null;
   }

   @Override
   public boolean addTaiKhoan(TaiKhoan taiKhoan) {
      return false;
   }

   @Override
   public boolean updateTaiKhoan(TaiKhoan taiKhoan) {
      return false;
   }

   @Override
   public boolean deleteTaiKhoan(String maTaiKhoan) {
      return false;
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
