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

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "ChiTietHD_DichVu")
@NamedQueries({
      @NamedQuery(name = "ChiTietHD_DichVu.findByMaHoaDon",
            query = "SELECT c FROM ChiTietHD_DichVu c WHERE c.id.maHoaDon = :maHoaDon"),
      @NamedQuery(name = "ChiTietHD_DichVu.findByMaDichVu",
            query = "SELECT c FROM ChiTietHD_DichVu c WHERE c.id.maDichVu = :maDichVu"),

})
public class ChiTietHD_DichVu {
   @EmbeddedId
   private ChiTietHD_DichVuId id;

   @MapsId("maHoaDon")
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaHoaDon", nullable = false)
   @ToString.Exclude
   private HoaDonThanhToan maHoaDon;

   @MapsId("maDichVu")
   @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaDichVu", nullable = false)
   @ToString.Exclude
   private DichVu maDichVu;

   @Column(name = "SoLuong")
   private Integer soLuong;

   @Column(name = "ThanhTien")
   private Integer thanhTien;

   public ChiTietHD_DichVu(ChiTietHD_DichVuId id, HoaDonThanhToan maHoaDon, DichVu maDichVu, Integer soLuong, Integer thanhTien) {
      this.id = id;
      this.maHoaDon = maHoaDon;
      this.maDichVu = maDichVu;
      this.soLuong = soLuong;
      this.thanhTien = thanhTien;
   }
}