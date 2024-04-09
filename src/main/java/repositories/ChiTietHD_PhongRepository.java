package repositories;

import entities.ChiTietHD_Phong;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface ChiTietHD_PhongRepository {
   public List<ChiTietHD_Phong> getCTPhongTheoMaHD(String maHD);

   public boolean addChiTietHD_Phong(ChiTietHD_Phong ct);

   public boolean updateChiTietHD_Phong(ChiTietHD_Phong ct);

   public boolean deleteChiTietHD_Phong(ChiTietHD_Phong ct);

   public List<ChiTietHD_Phong> findByMaHoaDon(String maHoaDon);

   public List<ChiTietHD_Phong> findByMaPhong(String maPhong);

   public ChiTietHD_Phong findByMaPhongAndMaHoaDon(String maHoaDon, String maPhong);

   public List<ChiTietHD_Phong> findByDate(String start, String end);

   public long calcTotalHoursOfUseOfCustomer(String maKhachHang);
}
