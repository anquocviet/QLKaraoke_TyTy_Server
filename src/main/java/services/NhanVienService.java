package services;

import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repositories.NhanVienRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class NhanVienService implements NhanVienRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public NhanVienService() {
      em = jakarta.persistence.Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   @Override
   public List<NhanVien> findAll() {
      return em.createNamedQuery("NhanVien.findAll", NhanVien.class).getResultList();
   }

   @Override
   public NhanVien findByMaNhanVien(String maNhanVien) {
      return em.createNamedQuery("NhanVien.findByMaNhanVien", NhanVien.class)
                   .setParameter("maNhanVien", maNhanVien)
                   .getResultStream()
                   .findFirst()
                   .orElse(null);
   }

   @Override
   public NhanVien findByHoTen(String hoTen) {
        return em.createNamedQuery("NhanVien.findByHoTen", NhanVien.class)
                .setParameter("hoTen", hoTen)
                .getResultStream()
                .findFirst()
                .orElse(null);
   }

   @Override
   public NhanVien findByCCCD(String cccd) {
        return em.createNamedQuery("NhanVien.findByCCCD", NhanVien.class)
                .setParameter("cccd", cccd)
                .getResultStream()
                .findFirst()
                .orElse(null);
   }

   @Override
   public NhanVien findBySoDienThoai(int soDienThoai) {
        return em.createNamedQuery("NhanVien.findBySoDienThoai", NhanVien.class)
                .setParameter("soDienThoai", soDienThoai)
                .getResultStream()
                .findFirst()
                .orElse(null);
   }

   @Override
   public boolean addEmployee(NhanVien nhanVien) {
      EntityTransaction transaction = null;
      try {
         transaction = em.getTransaction();
         transaction.begin();
         em.persist(nhanVien); // Thêm mới nhân viên vào cơ sở dữ liệu
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
   public boolean updateEmployee(NhanVien nhanVien) {
      EntityTransaction transaction = null;
      try {
         transaction = em.getTransaction();
         transaction.begin();
         em.merge(nhanVien); // Cập nhật thông tin nhân viên trong cơ sở dữ liệu
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
   public boolean deleteEmployee(String maNhanVien) {
      EntityTransaction transaction = null;
      try {
         transaction = em.getTransaction();
         transaction.begin();
         NhanVien nhanVien = em.find(NhanVien.class, maNhanVien); // Tìm nhân viên theo mã nhân viên
         if (nhanVien != null) {
            em.remove(nhanVien); // Xóa nhân viên khỏi cơ sở dữ liệu
         }
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

   public void close() {
      em.close();
   }
}
