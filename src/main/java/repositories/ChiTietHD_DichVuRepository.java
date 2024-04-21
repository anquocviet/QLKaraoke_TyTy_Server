package repositories;

import entities.ChiTietHD_DichVu;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface ChiTietHD_DichVuRepository {

   public boolean addChiTietHD_DichVu(ChiTietHD_DichVu ct);

   public boolean updateChiTietHD_DichVu(ChiTietHD_DichVu ct);

   public boolean deleteChiTietHD_DichVu(ChiTietHD_DichVu ct);

   public List<ChiTietHD_DichVu> findByMaHoaDon(String maHoaDon);

   public List<ChiTietHD_DichVu> findByMaDichVu(String maDichVu);
}
