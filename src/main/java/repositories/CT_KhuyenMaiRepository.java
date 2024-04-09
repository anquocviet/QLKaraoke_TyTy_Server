package repositories;

import entities.CT_KhuyenMai;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface CT_KhuyenMaiRepository {
   public List<CT_KhuyenMai> findAll();

   public boolean addCT_KhuyenMai(CT_KhuyenMai ct_khuyenMai);

   public boolean updateCT_KhuyenMai(CT_KhuyenMai ct_khuyenMai);

   public boolean deleteCT_KhuyenMai(String maKhuyenMai);

   public CT_KhuyenMai findByMaKhuyenMai(String maKhuyenMai);

   public CT_KhuyenMai findByTenKhuyenMai(String tenKhuyenMai);

   public boolean updateLuotSuDungConLai(String maKhuyenMai, int luotSuDungConLai);
}
