package repositories;

import entities.TaiKhoan;

import java.util.List;

/**
 * @description
 * @author: vie
 * @date: 9/4/24
 */
public interface TaiKhoanRepository {
   public TaiKhoan findByMaTaiKhoan(String maTaiKhoan);

   public List<TaiKhoan> findAll();

   public TaiKhoan findByName(String tenDangNhap);

   public TaiKhoan findByMaNhanVien(String maNhanVien);

   public boolean addTaiKhoan(TaiKhoan taiKhoan);

   public boolean updateTaiKhoan(TaiKhoan taiKhoan);

   public boolean deleteTaiKhoan(String maTaiKhoan);

   public TaiKhoan login(TaiKhoan account);
}
