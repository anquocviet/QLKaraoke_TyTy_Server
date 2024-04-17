package entities;

import enums.Enum_LoaiPhong;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "Phong.findByMaPhong", query = "SELECT p FROM Phong p WHERE p.maPhong = :maPhong"),
      @NamedQuery(name = "Phong.findAll", query = "SELECT p FROM Phong p"),
      @NamedQuery(name = "Phong.countRoomStatus", query = "SELECT COUNT(p) FROM Phong p WHERE p.tinhTrang = :status"),
      @NamedQuery(name = "Phong.findRoomByTypeStatusCapacity",
            query = "SELECT p FROM Phong p WHERE p.loaiPhong IN (:arrType) AND p.tinhTrang IN (:arrStatus) AND p.sucChua >= :capacity"),
      @NamedQuery(name = "Phong.findRoomByStatus", query = "SELECT p FROM Phong p WHERE p.tinhTrang = :status")

})
public class Phong implements Serializable {
   @Serial
   private static final long serialVersionUID = 293080990014196469L;

   @Id
   @Column(name = "MaPhong", nullable = false)
   private String maPhong;

   @Column(name = "LoaiPhong", columnDefinition = "int default 0") // 0: THUONG, 1: VIP
   @Enumerated(EnumType.ORDINAL)
   private Enum_LoaiPhong loaiPhong;

   @Column(name = "TinhTrang")
   private Integer tinhTrang;

   @Column(name = "SucChua")
   private Integer sucChua;

   @Column(name = "GiaPhong")
   private Integer giaPhong;

   @OneToMany(mappedBy = "phong")
   @ToString.Exclude
   private Set<ChiTietHD_Phong> chiTietHD_Phongs;

   @OneToMany(mappedBy = "phong")
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