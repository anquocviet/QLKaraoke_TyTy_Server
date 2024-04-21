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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
        @NamedQuery(name = "DichVu.findAll", query = "SELECT d FROM DichVu d"),
        @NamedQuery(name = "DichVu.findByMaDichVu", query = "SELECT d FROM DichVu d WHERE d.maDichVu LIKE :maDichVu"),
        @NamedQuery(name = "DichVu.findByTenDichVu", query = "SELECT d FROM DichVu d WHERE d.tenDichVu = :tenDichVu"),
        @NamedQuery(name = "DichVu.countAll", query = "SELECT COUNT(d.maDichVu) FROM DichVu d"),
        @NamedQuery(name = "DichVu.findListDichVuByMaHoaDon", query = "SELECT c.dichVu FROM ChiTietHD_DichVu c WHERE c.hoaDon.maHoaDon = :maHoaDon"),
        @NamedQuery(name = "DichVu.updateSoLuongTon",
            query = "UPDATE DichVu dv SET dv.soLuongTon = :soLuongTon WHERE dv.maDichVu = :maDichVu"),
        @NamedQuery(name = "DichVu.updateThongTinDichVu",
            query = "UPDATE DichVu dv SET dv.tenDichVu = :tenDichVu, dv.soLuongTon = :soLuongTon, dv.donViTinh = :donViTinh, dv.donGia = :donGia, dv.anhMinhHoa = :anhMinhHoa WHERE dv.maDichVu = :maDichVu")
})
public class DichVu implements Serializable {
   @Serial
   private static final long serialVersionUID = 8467030042899669890L;

   @Id
   @EqualsAndHashCode.Include
   @Column(name = "MaDichVu", nullable = false)
   private String maDichVu;

   @Column(name = "TenDichVu")
   private String tenDichVu;

   @Column(name = "SoLuongTon")
   private Integer soLuongTon;

   @Column(name = "DonViTinh")
   private String donViTinh;

   @Column(name = "DonGia")
   private Integer donGia;

   @Column(name = "AnhMinhHoa")
   private String anhMinhHoa;

   @OneToMany(mappedBy = "dichVu")
   @ToString.Exclude
   private Set<ChiTietHD_DichVu> chiTietDichVus = new HashSet<>();

   public DichVu(String maDichVu, String tenDichVu, Integer soLuongTon, String donViTinh, Integer donGia, String anhMinhHoa) {
      this.maDichVu = maDichVu;
      this.tenDichVu = tenDichVu;
      this.soLuongTon = soLuongTon;
      this.donViTinh = donViTinh;
      this.donGia = donGia;
      this.anhMinhHoa = anhMinhHoa;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      DichVu dichVu = (DichVu) o;
      return Objects.equals(maDichVu, dichVu.maDichVu);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(maDichVu);
   }
}