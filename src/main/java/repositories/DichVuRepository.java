package repositories;

import entities.DichVu;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */

public interface DichVuRepository {
   public boolean addDichVu(DichVu dv);

   public boolean updateDichVu(DichVu dv);

   public boolean deleteDichVu(DichVu dv);

   public List<DichVu> findAllDichVu();

   public DichVu findDichVu(String maDichVu);

   public List<DichVu> findListDichVuByMaHoaDon(String maHoaDon);

   public Long countDichVu();
}
