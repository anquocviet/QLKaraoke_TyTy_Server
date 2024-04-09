package entities;

import enums.Enum_ChucVu;
import enums.Enum_TrangThaiLamViec;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
@NamedQueries({
      @NamedQuery(name = "NhanVien.findAll", query = "SELECT nv FROM NhanVien nv"),
      @NamedQuery(name = "NhanVien.findByMaNhanVien", query = "SELECT nv FROM NhanVien nv WHERE nv.maNhanVien = :maNhanVien"),
      @NamedQuery(name = "NhanVien.findByHoTen", query = "SELECT nv FROM NhanVien nv WHERE nv.hoTen = :hoTen"),
      @NamedQuery(name = "NhanVien.findByCCCD", query = "SELECT nv FROM NhanVien nv WHERE nv.cccd = :cccd"),
      @NamedQuery(name = "NhanVien.findBySoDienThoai", query = "SELECT nv FROM NhanVien nv WHERE nv.soDienThoai = :soDienThoai"),

})
public class NhanVien {
   @Id
   @Column(name = "MaNhanVien", nullable = false)
   private String maNhanVien;

   @Column(name = "HoTen")
   private String hoTen;

   @Column(name = "CCCD")
   private String cccd;

   @Column(name = "SoDienThoai")
   private Integer soDienThoai;

   @Column(name = "NgaySinh")
   private Instant ngaySinh;

   @Column(name = "DiaChi")
   private String diaChi;

   @Column(name = "GioiTinh")
   private Integer gioiTinh;

   @Column(name = "ChucVu") // 0: QUANLY, 1: NHANVIENTIEPTAN, 2: NHANVIENPHUCVU, 3: BAOVE
   private Enum_ChucVu chucVu;

   @Column(name = "TrangThai") // 0: CONLAMVIEC, 1: DANGHI
   private Enum_TrangThaiLamViec trangThai;

   @Column(name = "AnhDaiDien")
   private String anhDaiDien;

   @OneToMany(mappedBy = "maNhanVien")
   @ToString.Exclude
   private Set<HoaDonThanhToan> listHoaDonThanhToan;

   @OneToMany(mappedBy = "maNhanVien")
   @ToString.Exclude
   private Set<PhieuDatPhong> listPhieuDatPhong;

   public NhanVien(String maNhanVien, String hoTen, String cccd, Integer soDienThoai, Instant ngaySinh, String diaChi, Integer gioiTinh, Enum_ChucVu chucVu, Enum_TrangThaiLamViec trangThai, String anhDaiDien) {
      this.maNhanVien = maNhanVien;
      this.hoTen = hoTen;
      this.cccd = cccd;
      this.soDienThoai = soDienThoai;
      this.ngaySinh = ngaySinh;
      this.diaChi = diaChi;
      this.gioiTinh = gioiTinh;
      this.chucVu = chucVu;
      this.trangThai = trangThai;
      this.anhDaiDien = anhDaiDien;
   }
}