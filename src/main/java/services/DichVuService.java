package services;

import entities.DichVu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import repositories.DichVuRepository;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class DichVuService implements DichVuRepository {
   private EntityManager em = null;
   EntityTransaction transaction = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public DichVuService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   @Override
   public boolean addDichVu(DichVu dv) {
    EntityTransaction transaction = em.getTransaction();
    try {

        transaction.begin();
        em.persist(dv);
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

//   @Override
//   public boolean capNhatSoLuongDichVu(DichVu dv) {
//      try {
//         transaction.begin();
//         em.createNamedQuery("DichVu.updateSoLuongTon")
//                 .setParameter("soLuongTon", dv.getSoLuongTon())
//                 .setParameter("maDichVu", dv.getMaDichVu())
//                 .executeUpdate();
//         transaction.commit();
//         return true;
//      } catch (Exception e) {
//         transaction.rollback();
//         e.printStackTrace();
//      }
//        return false;
//   }
   @Override
   public boolean updateDichVu(DichVu dv) {
       EntityTransaction transaction = em.getTransaction();
        try {
             transaction.begin();
             em.createNamedQuery("DichVu.updateThongTinDichVu")
                     .setParameter("tenDichVu", dv.getTenDichVu())
                     .setParameter("soLuongTon", dv.getSoLuongTon())
                     .setParameter("donViTinh", dv.getDonViTinh())
                     .setParameter("donGia", dv.getDonGia())
                     .setParameter("anhMinhHoa", dv.getAnhMinhHoa())
                     .setParameter("maDichVu", dv.getMaDichVu())
                     .executeUpdate();
             transaction.commit();
             return true;
        } catch (Exception e) {
             transaction.rollback();
             e.printStackTrace();
        }
        return false;
   }


   @Override
   public List<DichVu> findAllDichVu() {
      return em.createNamedQuery("DichVu.findAll", DichVu.class).getResultList();
   }

   @Override
   public List<DichVu> findDichVuById(String maDichVu) {
      return em.createNamedQuery("DichVu.findByMaDichVu", DichVu.class)
               .setParameter("maDichVu","%" + maDichVu + "%")
               .getResultList();
   }

   @Override
   public List<DichVu> findListDichVuByMaHoaDon(String maHoaDon) {
      return em.createNamedQuery("DichVu.findListDichVuByMaHoaDon", DichVu.class)
               .setParameter("maHoaDon", maHoaDon)
               .getResultList();
   }



@Override
    public Long countDichVu() {
        return em.createNamedQuery("DichVu.countAll", Long.class).getSingleResult();
    }

    @Override
    public boolean deleteDichVu(DichVu dv) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.find(DichVu.class, dv));
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
