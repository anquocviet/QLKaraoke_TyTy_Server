package services;

import entities.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import repositories.KhachHangRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class KhachHangService implements KhachHangRepository {
   private EntityManager em = null;
   EntityTransaction transaction = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public KhachHangService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
      transaction = em.getTransaction();
   }


   @Override
   public List<KhachHang> findAll() {
      return em.createNamedQuery("KhachHang.findAll", KhachHang.class).getResultList();
   }

   @Override
   public List<KhachHang> findByMaKhachHang(String maKhachHang) {
      return em.createNamedQuery("KhachHang.findByMaKhachHang", KhachHang.class)
                   .setParameter("maKhachHang", "%" + maKhachHang + "%")
                   .getResultStream()
                   .toList();
   }

   @Override
   public List<KhachHang> findByTenKhachHang(String tenKhachHang) {
      List<KhachHang> khachHangs = em.createNamedQuery("KhachHang.findByTenKhachHang", KhachHang.class)
                                         .setParameter("tenKhachHang", "%" + tenKhachHang + "%")
                                         .getResultList();
      return khachHangs;
   }

   @Override
   public List<KhachHang> findBySoDienThoai(String soDienThoai) {
      return em.createNamedQuery("KhachHang.findBySoDienThoai", KhachHang.class)
                   .setParameter("soDienThoai", "%" + soDienThoai + "%")
                   .getResultList();
   }

   @Override
   public boolean addCustomer(KhachHang khachHang) {
//        maKhachHang tu phat sinh bat dau voi 2 ki tu KH va 4 ki so tang tu dong
      List<KhachHang> khachHangs = findAll();
      if (khachHangs.size() == 0) {
         khachHang.setMaKhachHang("KH0001");
      } else {
         String maKhachHang = khachHangs.get(khachHangs.size() - 1).getMaKhachHang();
         int count = Integer.parseInt(maKhachHang.substring(2)) + 1;
         String newMaKhachHang = "KH" + String.format("%04d", count);
         khachHang.setMaKhachHang(newMaKhachHang);
         try {
            transaction.begin();
            em.persist(khachHang);
            transaction.commit();
            return true;
         } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
         }
      }

      return false;
   }

   @Override
   public boolean updateCustomer(KhachHang khachHang) {
      try {
         transaction.begin();
         em.createNamedQuery("KhachHang.update")
               .setParameter("maKhachHang", khachHang.getMaKhachHang())
               .setParameter("tenKhachHang", khachHang.getTenKhachHang())
               .setParameter("soDienThoai", khachHang.getSoDienThoai())
               .setParameter("namSinh", khachHang.getNamSinh())
               .setParameter("gioiTinh", khachHang.getGioiTinh())
               .executeUpdate();
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

}
