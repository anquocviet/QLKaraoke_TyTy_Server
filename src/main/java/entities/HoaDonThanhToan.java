package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "HoaDonThanhToan.findAll", query = "SELECT h FROM HoaDonThanhToan h"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaHoaDon", query = "SELECT h FROM HoaDonThanhToan h WHERE h.maHoaDon = :maHoaDon"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaKhachHang", query = "SELECT h FROM HoaDonThanhToan h WHERE h.maKhachHang = :maKhachHang"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaNhanVien", query = "SELECT h FROM HoaDonThanhToan h WHERE h.maNhanVien = :maNhanVien"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaKhuyenMai", query = "SELECT h FROM HoaDonThanhToan h WHERE h.maKhuyenMai = :maKhuyenMai"),
})
public class HoaDonThanhToan {
   @Id
   @Column(name = "MaHoaDon", nullable = false)
   private String maHoaDon;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaKhachHang")
   @ToString.Exclude
   private KhachHang maKhachHang;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaNhanVien")
   @ToString.Exclude
   private NhanVien maNhanVien;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaKhuyenMai")
   @ToString.Exclude
   private CT_KhuyenMai maKhuyenMai;

   @Column(name = "NgayLap")
   private Instant ngayLap;

   @Column(name = "TongTien")
   private Integer tongTien;

   @OneToMany(mappedBy = "maHoaDon", fetch = FetchType.LAZY)
   @ToString.Exclude
   private Set<ChiTietHD_DichVu> listCTDV = new HashSet<>();

   @OneToMany(mappedBy = "maHoaDon", fetch = FetchType.LAZY)
   @ToString.Exclude
   private Set<ChiTietHD_Phong> listCTP = new HashSet<>();

   public HoaDonThanhToan(String maHoaDon, KhachHang maKhachHang, NhanVien maNhanVien, CT_KhuyenMai maKhuyenMai, Instant ngayLap, Integer tongTien) {
      this.maHoaDon = maHoaDon;
      this.maKhachHang = maKhachHang;
      this.maNhanVien = maNhanVien;
      this.maKhuyenMai = maKhuyenMai;
      this.ngayLap = ngayLap;
      this.tongTien = tongTien;
   }
}