package entities;

import enums.Enum_LoaiPhong;
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

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "Phong.findByMaPhong", query = "SELECT p FROM Phong p WHERE p.maPhong = :maPhong"),
      @NamedQuery(name = "Phong.findAll", query = "SELECT p FROM Phong p")
})
public class Phong {
   @Id
   @Column(name = "MaPhong", nullable = false)
   private String maPhong;

   @Column(name = "LoaiPhong", columnDefinition = "int default 0") // 0: THUONG, 1: VIP
   private Enum_LoaiPhong loaiPhong;

   @Column(name = "TinhTrang")
   private Integer tinhTrang;

   @Column(name = "SucChua")
   private Integer sucChua;

   @Column(name = "GiaPhong")
   private Integer giaPhong;

   @OneToMany(mappedBy = "maPhong")
   @ToString.Exclude
   private Set<ChiTietHD_Phong> chiTietHD_Phongs;

   @OneToMany(mappedBy = "maPhong")
   @ToString.Exclude
   private Set<PhieuDatPhong> phieuDatPhongs;

   public Phong(String maPhong, Enum_LoaiPhong loaiPhong, Integer tinhTrang, Integer sucChua, Integer giaPhong) {
      this.maPhong = maPhong;
      this.loaiPhong = loaiPhong;
      this.tinhTrang = tinhTrang;
      this.sucChua = sucChua;
      this.giaPhong = giaPhong;
   }
}