package repositories;

import entities.PhieuDatPhong;

import java.time.Instant;
import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface PhieuDatPhongRepository {
   public List<PhieuDatPhong> findAll();

   public List<PhieuDatPhong> findByMaPhieuDat(String maPhieuDat);

   public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong);

   public boolean updatePhieuDatPhong(PhieuDatPhong phieuDatPhong);

   public boolean deletePhieuDatPhong(String maPhieuDat);

   public PhieuDatPhong findBookingTicketByRoomID(String roomID);

   public List<PhieuDatPhong> findBookingTicketByCustomerID(String customerID);

   public List<PhieuDatPhong> findAllBookingTicketNotUsed();

   public Long countBookingTicketInDate(Instant date);
}
