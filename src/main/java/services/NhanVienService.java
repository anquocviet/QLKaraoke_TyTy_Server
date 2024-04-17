package services;

import entities.NhanVien;
import jakarta.persistence.EntityManager;
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
      return null;
   }

   @Override
   public NhanVien findByCCCD(String cccd) {
      return null;
   }

   @Override
   public NhanVien findBySoDienThoai(String soDienThoai) {
      return null;
   }

   @Override
   public boolean addEmployee(NhanVien nhanVien) {
      return false;
   }

   @Override
   public boolean updateEmployee(NhanVien nhanVien) {
      return false;
   }

   @Override
   public boolean deleteEmployee(String maNhanVien) {
      return false;
   }

   public void close() {
      em.close();
   }
}
