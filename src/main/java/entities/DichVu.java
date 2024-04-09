package entities;

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

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "DichVu.findAll", query = "SELECT d FROM DichVu d"),
      @NamedQuery(name = "DichVu.findByMaDichVu", query = "SELECT d FROM DichVu d WHERE d.maDichVu = :maDichVu"),
      @NamedQuery(name = "DichVu.findByTenDichVu", query = "SELECT d FROM DichVu d WHERE d.tenDichVu = :tenDichVu"),
      @NamedQuery(name = "DichVu.countAll", query = "SELECT COUNT(d) FROM DichVu d")
})
public class DichVu {
   @Id
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

   @OneToMany(mappedBy = "maDichVu")
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
}