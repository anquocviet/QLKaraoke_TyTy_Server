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

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "PhieuDatPhong.findByMaPhieuDat", query = "SELECT pdp FROM PhieuDatPhong pdp WHERE pdp.maPhieuDat = :maPhieuDat"),
      @NamedQuery(name = "PhieuDatPhong.findAll", query = "SELECT pdp FROM PhieuDatPhong pdp"),
      @NamedQuery(name = "PhieuDatPhong.findBookingTicketByRoomID", query = "SELECT pdp FROM PhieuDatPhong pdp WHERE pdp.phong.maPhong = :roomID")

})
public class PhieuDatPhong implements Serializable {
   @Serial
   private static final long serialVersionUID = -6562395586172805484L;

   @Id
   @Column(name = "MaPhieuDat", nullable = false)
   private String maPhieuDat;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaPhong")
   @ToString.Exclude
   private Phong phong;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaKhachHang")
   @ToString.Exclude
   private KhachHang khachHang;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaNhanVien")
   @ToString.Exclude
   private NhanVien nhanVien;

   @Column(name = "ThoiGianLap")
   private Instant thoiGianLap;

   @Column(name = "ThoiGianNhan")
   private Instant thoiGianNhan;

   @Column(name = "GhiChu")
   private String ghiChu;

   @Column(name = "TinhTrang")
   private Integer tinhTrang;

   public PhieuDatPhong(String maPhieuDat, Phong phong, KhachHang khachHang, NhanVien nhanVien, Instant thoiGianLap, Instant thoiGianNhan, String ghiChu, Integer tinhTrang) {
      this.maPhieuDat = maPhieuDat;
      this.phong = phong;
      this.khachHang = khachHang;
      this.nhanVien = nhanVien;
      this.thoiGianLap = thoiGianLap;
      this.thoiGianNhan = thoiGianNhan;
      this.ghiChu = ghiChu;
      this.tinhTrang = tinhTrang;
   }
}