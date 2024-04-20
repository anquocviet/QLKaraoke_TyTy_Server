package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "KhachHang.findAll", query = "SELECT kh FROM KhachHang kh"),
      @NamedQuery(name = "KhachHang.findByMaKhachHang", query = "SELECT kh FROM KhachHang kh WHERE kh.maKhachHang = :maKhachHang"),
      @NamedQuery(name = "KhachHang.findByTenKhachHang", query = "SELECT kh FROM KhachHang kh WHERE kh.tenKhachHang = :tenKhachHang"),
      @NamedQuery(name = "KhachHang.findBySoDienThoai", query = "SELECT kh FROM KhachHang kh WHERE kh.soDienThoai = :soDienThoai"),
})
public class KhachHang implements Serializable {
   @Serial
   private static final long serialVersionUID = 2141924125267023221L;

   @Id
   @EqualsAndHashCode.Include
   @Column(name = "MaKhachHang", nullable = false)
   private String maKhachHang;

   @Column(name = "TenKhachHang")
   private String tenKhachHang;

   @Column(name = "SoDienThoai")
   private Integer soDienThoai;

   @Column(name = "NamSinh")
   private Integer namSinh;

   @Column(name = "GioiTinh")
   private Integer gioiTinh;

   @OneToMany(mappedBy = "khachHang")
   @ToString.Exclude
   private Set<HoaDonThanhToan> listHoaDonThanhToan;

   @OneToMany(mappedBy = "khachHang")
   @ToString.Exclude
   private Set<PhieuDatPhong> listPhieuDatPhong;

   public KhachHang(String maKhachHang, String tenKhachHang, Integer soDienThoai, Integer namSinh, Integer gioiTinh) {
      this.maKhachHang = maKhachHang;
      this.tenKhachHang = tenKhachHang;
      this.soDienThoai = soDienThoai;
      this.namSinh = namSinh;
      this.gioiTinh = gioiTinh;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      KhachHang khachHang = (KhachHang) o;
      return Objects.equals(maKhachHang, khachHang.maKhachHang);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(maKhachHang);
   }
}