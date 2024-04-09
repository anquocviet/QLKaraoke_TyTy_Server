package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "PhieuDatPhong.findByMaPhieuDat", query = "SELECT pdp FROM PhieuDatPhong pdp WHERE pdp.maPhieuDat = :maPhieuDat"),
      @NamedQuery(name = "PhieuDatPhong.findAll", query = "SELECT pdp FROM PhieuDatPhong pdp")

})
public class PhieuDatPhong {
   @Id
   @Column(name = "MaPhieuDat", nullable = false)
   private String maPhieuDat;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaPhong")
   @ToString.Exclude
   private Phong maPhong;

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

   @Column(name = "ThoiGianLap")
   private Instant thoiGianLap;

   @Column(name = "ThoiGianNhan")
   private Instant thoiGianNhan;

   @Column(name = "GhiChu")
   private String ghiChu;

   @Column(name = "TinhTrang")
   private Integer tinhTrang;

   public PhieuDatPhong(String maPhieuDat, Phong maPhong, KhachHang maKhachHang, NhanVien maNhanVien, Instant thoiGianLap, Instant thoiGianNhan, String ghiChu, Integer tinhTrang) {
      this.maPhieuDat = maPhieuDat;
      this.maPhong = maPhong;
      this.maKhachHang = maKhachHang;
      this.maNhanVien = maNhanVien;
      this.thoiGianLap = thoiGianLap;
      this.thoiGianNhan = thoiGianNhan;
      this.ghiChu = ghiChu;
      this.tinhTrang = tinhTrang;
   }
}