package repositories;

import entities.HoaDonThanhToan;

import java.time.LocalDate;
import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface HoaDonThanhToanRepository {
   public boolean addHoaDonThanhToan(HoaDonThanhToan bill);

   public boolean updateHoaDonThanhToan(HoaDonThanhToan bill);

   public boolean deleteHoaDonThanhToan(String billID);

   public List<HoaDonThanhToan> findAll();

   public HoaDonThanhToan findBill(String billID);

   public List<HoaDonThanhToan> findBill(LocalDate date);

   public List<HoaDonThanhToan> findByRoomID();

   public List<HoaDonThanhToan> findByCustomerID();

   public List<HoaDonThanhToan> findByEmployeeID();

   public List<HoaDonThanhToan> statistical(LocalDate date, String type);

   public int countBill();

   public int countBill(String customerID);

   public int countBill(LocalDate date, String type);

   public long calcMoney();

   public long calcMoney(LocalDate date, String type);

   public long calcMoney(String customerID);
}