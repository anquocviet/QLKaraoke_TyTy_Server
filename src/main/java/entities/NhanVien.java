package entities;

import enums.Enum_ChucVu;
import enums.Enum_TrangThaiLamViec;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
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

public class NhanVien implements Serializable {
   @Serial
   private static final long serialVersionUID = 4231211857730583614L;

   public NhanVien(String maNhanVien) {
      this.maNhanVien = maNhanVien;
   }

   @Id
   @EqualsAndHashCode.Include
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

   @Column(name = "ChucVu")
   @Enumerated(EnumType.STRING)
   private Enum_ChucVu chucVu;

   @Column(name = "TrangThai")
   @Enumerated(EnumType.STRING)
   private Enum_TrangThaiLamViec trangThai;

   @Column(name = "AnhDaiDien")
   private String anhDaiDien;

   @OneToMany(mappedBy = "nhanVien")
   @ToString.Exclude
   private Set<HoaDonThanhToan> listHoaDonThanhToan;

   @OneToMany(mappedBy = "nhanVien")
   @ToString.Exclude
   private Set<PhieuDatPhong> listPhieuDatPhong;

   public NhanVien(String maNhanVien) {
      this.maNhanVien = maNhanVien;
   }

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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      NhanVien nhanVien = (NhanVien) o;
      return Objects.equals(maNhanVien, nhanVien.maNhanVien);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(maNhanVien);
   }
}