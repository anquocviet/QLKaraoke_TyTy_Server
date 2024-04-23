package services;

import entities.ChiTietHD_Phong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import repositories.ChiTietHD_PhongRepository;

import java.time.Instant;
import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class ChiTietHD_PhongService implements ChiTietHD_PhongRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public ChiTietHD_PhongService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   @Override
   public List<ChiTietHD_Phong> getCTPhongTheoMaHD(String maHD) {
      return em.createNamedQuery("ChiTietHD_Phong.findByMaHoaDon", ChiTietHD_Phong.class)
                   .setParameter("maHoaDon", maHD)
                   .getResultList();
   }

   @Override
   public boolean addChiTietHD_Phong(ChiTietHD_Phong ct) {
      try {
         em.getTransaction().begin();
         ct.setHoaDon(em.merge(ct.getHoaDon()));
         ct.setPhong(em.merge(ct.getPhong()));
         em.persist(ct);
         em.getTransaction().commit();
         return true;
      } catch (Exception e) {
         em.getTransaction().rollback();
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean updateChiTietHD_Phong(ChiTietHD_Phong ct) {
      try {
         em.getTransaction().begin();
         em.merge(ct);
         em.getTransaction().commit();
         return true;
      } catch (Exception e) {
         em.getTransaction().rollback();
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean deleteChiTietHD_Phong(ChiTietHD_Phong ct) {
      try {
         em.getTransaction().begin();
         em.remove(ct);
         em.getTransaction().commit();
         return true;
      } catch (Exception e) {
         em.getTransaction().rollback();
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public List<ChiTietHD_Phong> findByMaHoaDon(String maHoaDon) {
      return em.createNamedQuery("ChiTietHD_Phong.findByMaHoaDon", ChiTietHD_Phong.class)
                   .setParameter("maHoaDon", maHoaDon)
                   .getResultList();
   }

   @Override
   public List<ChiTietHD_Phong> findByMaPhong(String maPhong) {
      return em.createNamedQuery("ChiTietHD_Phong.findByMaPhong", ChiTietHD_Phong.class)
                   .setParameter("maPhong", maPhong)
                   .getResultList();
   }

   @Override
   public ChiTietHD_Phong findByMaPhongAndMaHoaDon(String maPhong, String maHoaDon) {
      return em.createNamedQuery("ChiTietHD_Phong.findByMaPhongAndMaHoaDon", ChiTietHD_Phong.class)
                   .setParameter("maPhong", maPhong)
                   .setParameter("maHoaDon", maHoaDon)
                   .getSingleResult();
   }

   @Override
   public List<ChiTietHD_Phong> findByDate(Instant start, Instant end) {
      return em.createNamedQuery("ChiTietHD_Phong.findByDate", ChiTietHD_Phong.class)
                   .setParameter("start", start)
                   .setParameter("end", end)
                   .getResultList();
   }

   @Override
   public long calcTotalHoursOfUseOfCustomer(String maKhachHang) {
      return em.createNamedQuery("ChiTietHD_Phong.calcTotalHoursOfUseOfCustomer", Long.class)
                   .setParameter("maKhachHang", maKhachHang)
                   .getSingleResult();
   }
}
