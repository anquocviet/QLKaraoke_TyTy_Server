package services;

import entities.HoaDonThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import repositories.HoaDonThanhToanRepository;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public class HoaDonThanhToanService implements HoaDonThanhToanRepository {
   private EntityManager em = null;
   private final String PERSISTENCE_UNIT_NAME = "MariaDB Karaoke";

   public HoaDonThanhToanService() {
      em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   @Override
   public boolean addHoaDonThanhToan(HoaDonThanhToan bill) {
      EntityTransaction transaction = em.getTransaction();
      try {
         transaction.begin();
         em.persist(bill);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public boolean updateHoaDonThanhToan(HoaDonThanhToan bill) {
      EntityTransaction transaction = em.getTransaction();
      try {
         transaction.begin();
         if (em.find(HoaDonThanhToan.class, bill.getMaHoaDon()) == null) {
            return false;
         }
         em.merge(bill);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public boolean deleteHoaDonThanhToan(String billID) {
      EntityTransaction transaction = em.getTransaction();
      try {
         transaction.begin();
         HoaDonThanhToan bill = em.find(HoaDonThanhToan.class, billID);
         if (bill == null) {
            return false;
         }
         em.remove(bill);
         transaction.commit();
         return true;
      } catch (Exception e) {
         transaction.rollback();
         e.printStackTrace();
      }
      return false;
   }

   @Override
   public List<HoaDonThanhToan> findAll() {
      return em.createNamedQuery("HoaDonThanhToan.findAll").getResultList();
   }

   @Override
   public List<HoaDonThanhToan> findBill(LocalDate date) {
      return em.createNamedQuery("HoaDonThanhToan.findByNgayLap")
                   .setParameter("ngayLap", date)
                   .getResultList();
   }

   @Override
   public List<HoaDonThanhToan> findByRoomID(String roomID) {
      return em.createNamedQuery("HoaDonThanhToan.findByMaPhong")
                   .setParameter("maPhong", roomID)
                   .getResultList();
   }

   @Override
   public List<HoaDonThanhToan> findByCustomerID(String customerID) {
      return em.createNamedQuery("HoaDonThanhToan.findByMaKhachHang")
                   .setParameter("maKhachHang", customerID)
                   .getResultList();
   }

   @Override
   public List<HoaDonThanhToan> findByEmployeeID(String employeeID) {
      return em.createNamedQuery("HoaDonThanhToan.findByMaNhanVien")
                   .setParameter("maNhanVien", employeeID)
                   .getResultList();
   }

   @Override
   public int countBill() {
      return em.createNamedQuery("HoaDonThanhToan.countBill")
                   .getFirstResult();
   }

   @Override
   public int countBill(String customerID) {
      return em.createNamedQuery("HoaDonThanhToan.countBillByMaKhachHang")
                   .setParameter("maKhachHang", customerID)
                   .getFirstResult();
   }

   @Override
   public Map<Integer, Integer> getBillsByDate(LocalDate date, String type) {
      TypedQuery<Object[]> namedQuery = switch (type) {
         case "day" -> em.createNamedQuery("HoaDonThanhToan.getBillsByDay", Object[].class);
         case "month" -> em.createNamedQuery("HoaDonThanhToan.getBillsByMonth", Object[].class);
         case "quarter" -> em.createNamedQuery("HoaDonThanhToan.getBillsByQuarter", Object[].class);
         case "year" -> em.createNamedQuery("HoaDonThanhToan.getBillsByYear", Object[].class);
         default -> throw new IllegalStateException("Unexpected value: " + type);
      };
      return namedQuery.setParameter("ngayLap", date)
                   .getResultStream()
                   .collect(Collectors.toMap(
                         o -> (Integer) ((Object[]) o)[0],
                         o -> (Integer) ((Object[]) o)[1],
                         (a, b) -> a,
                         LinkedHashMap::new
                   ));
   }

   @Override
   public long calcMoney() {
      return em.createNamedQuery("HoaDonThanhToan.calcMoney")
                   .getFirstResult();
   }

   @Override
   public long calcMoney(LocalDate date, String type) {
      TypedQuery<Long> namedQuery = switch (type) {
         case "day" -> em.createNamedQuery("HoaDonThanhToan.calcMoneyByNgayLap", Long.class);
         case "month" -> em.createNamedQuery("HoaDonThanhToan.calcMoneyByMonth", Long.class);
         case "quarter" -> em.createNamedQuery("HoaDonThanhToan.calcMoneyByQuarter", Long.class);
         case "year" -> em.createNamedQuery("HoaDonThanhToan.calcMoneyByYear", Long.class);
         default -> throw new IllegalStateException("Unexpected value: " + type);
      };
      return namedQuery.setParameter("ngayLap", date)
                   .getFirstResult();
   }

   @Override
   public long calcMoney(String customerID) {
      return em.createNamedQuery("HoaDonThanhToan.calcMoneyByMaKhachHang")
                   .setParameter("maKhachHang", customerID)
                   .getFirstResult();
   }
}
