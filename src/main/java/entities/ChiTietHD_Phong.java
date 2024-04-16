package entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@Entity(name = "ChiTietHD_Phong")
@NamedQueries({
      @NamedQuery(name = "ChiTietHD_Phong.findByMaHoaDon",
            query = "SELECT c FROM ChiTietHD_Phong c WHERE c.id.maHoaDon = :maHoaDon"),
      @NamedQuery(name = "ChiTietHD_Phong.findByMaPhong",
            query = "SELECT c FROM ChiTietHD_Phong c WHERE c.id.maPhong = :maPhong"),
      @NamedQuery(name = "ChiTietHD_Phong.findByMaHoaDonAndMaPhong",
            query = "SELECT c FROM ChiTietHD_Phong c WHERE c.id.maHoaDon = :maHoaDon AND c.id.maPhong = :maPhong"),
      @NamedQuery(name = "ChiTietHD_Phong.findByDate",
            query = "SELECT c FROM ChiTietHD_Phong c WHERE c.gioVao >= :start AND c.gioVao <= :end"),
      @NamedQuery(name = "ChiTietHD_Phong.calcTotalHoursOfUseOfCustomer",
            query = "SELECT SUM(c.tongGioSuDung) FROM ChiTietHD_Phong c WHERE c.hoaDon.khachHang.maKhachHang = :maKhachHang"),
})
public class ChiTietHD_Phong implements Serializable {
   @Serial
   private static final long serialVersionUID = 5168275571603568690L;

   @EmbeddedId
   private ChiTietHD_PhongId id;

   @MapsId("maHoaDon")
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaHoaDon", nullable = false)
   @ToString.Exclude
   private HoaDonThanhToan hoaDon;

   @MapsId("maPhong")
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaPhong", nullable = false)
   @ToString.Exclude
   private Phong phong;

   @Column(name = "GioVao")
   private Instant gioVao;

   @Column(name = "GioRa")
   private Instant gioRa;

   @Column(name = "TongGioSuDung")
   private Double tongGioSuDung;

   @Column(name = "ThanhTien")
   private Integer thanhTien;

   public ChiTietHD_Phong(ChiTietHD_PhongId id, HoaDonThanhToan hoaDon, Phong phong, Instant gioVao, Instant gioRa, Double tongGioSuDung, Integer thanhTien) {
      this.id = id;
      this.hoaDon = hoaDon;
      this.phong = phong;
      this.gioVao = gioVao;
      this.gioRa = gioRa;
      this.tongGioSuDung = tongGioSuDung;
      this.thanhTien = thanhTien;
   }
}