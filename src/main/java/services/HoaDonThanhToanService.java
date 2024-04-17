package services;

import entities.HoaDonThanhToan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import repositories.HoaDonThanhToanRepository;

import java.time.LocalDate;
import java.util.List;

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
      return false;
   }

   @Override
   public boolean updateHoaDonThanhToan(HoaDonThanhToan bill) {
      return false;
   }

   @Override
   public boolean deleteHoaDonThanhToan(String billID) {
      return false;
   }

   @Override
   public List<HoaDonThanhToan> findAll() {
      return em.createNamedQuery("HoaDonThanhToan.findAll").getResultList();
   }

   @Override
   public HoaDonThanhToan findBill(String billID) {
      return null;
   }

   @Override
   public List<HoaDonThanhToan> findBill(LocalDate date) {
//      return em.createNamedQuery("HoaDonThanhToan.findByNgayLap").setParameter("ngayLap", date).getResultList();
      return List.of();
   }

   @Override
   public List<HoaDonThanhToan> findByRoomID() {
      return List.of();
   }

   @Override
   public List<HoaDonThanhToan> findByCustomerID() {
      return List.of();
   }

   @Override
   public List<HoaDonThanhToan> findByEmployeeID() {
      return List.of();
   }

   @Override
   public List<HoaDonThanhToan> statistical(LocalDate date, String type) {
      return List.of();
   }

   @Override
   public int countBill() {
      return 0;
   }

   @Override
   public int countBill(String customerID) {
      return 0;
   }

   @Override
   public int countBill(LocalDate date, String type) {
      return 0;
   }

   @Override
   public long calcMoney() {
      return 0;
   }

   @Override
   public long calcMoney(LocalDate date, String type) {
      return 0;
   }

   @Override
   public long calcMoney(String customerID) {
      return 0;
   }
}
