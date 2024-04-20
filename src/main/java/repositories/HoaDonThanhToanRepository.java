package repositories;

import entities.HoaDonThanhToan;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

   public List<HoaDonThanhToan> findBill(LocalDate date);

   public HoaDonThanhToan findByRoomUsing(String roomID);

   public List<HoaDonThanhToan> findByCustomerID(String customerID);

   public HoaDonThanhToan findByCustomerUsingRoom(String customerID);

   public List<HoaDonThanhToan> findByEmployeeID(String employeeID);

   public int countBill();

   public int countBill(String customerID);

   public Map<Integer, Integer> getBillsByDate(LocalDate date, String type);

   public long calcMoney();

   public long calcMoney(LocalDate date, String type);

   public long calcMoney(String customerID);
}