package repositories;

import entities.PhieuDatPhong;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface PhieuDatPhongRepository {
   public List<PhieuDatPhong> findAll();

   public PhieuDatPhong findByMaPhieuDat(String maPhieuDat);

   public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong);

   public boolean updatePhieuDatPhong(PhieuDatPhong phieuDatPhong);

   public boolean deletePhieuDatPhong(String maPhieuDat);

   public PhieuDatPhong findBookingTicketByRoomID(String roomID);
}
