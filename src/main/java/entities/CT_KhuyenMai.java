package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CT_KhuyenMai")
@NamedQueries({
      @NamedQuery(name = "CT_Khuyenmai.findAll", query = "SELECT k FROM CT_KhuyenMai k"),
      @NamedQuery(name = "CT_Khuyenmai.findByMaKhuyenMai", query = "SELECT k FROM CT_KhuyenMai k WHERE k.maKhuyenMai = :maKhuyenMai"),
      @NamedQuery(name = "CT_Khuyenmai.findByTenKhuyenMai", query = "SELECT k FROM CT_KhuyenMai k WHERE k.tenKhuyenMai = :tenKhuyenMai"),
      @NamedQuery(name = "CT_Khuyenmai.deleteByMaKhuyenMai", query = "DELETE FROM CT_KhuyenMai k WHERE k.maKhuyenMai = :maKhuyenMai"),
      @NamedQuery(name = "CT_Khuyenmai.updateLuotSuDungConLai", query = "UPDATE CT_KhuyenMai k SET k.luotSuDungConLai = :luotSuDungConLai WHERE k.maKhuyenMai = :maKhuyenMai")

})
public class CT_KhuyenMai implements Serializable {
   @Serial
   private static final long serialVersionUID = 2109524451725521643L;

   @Id
   @Column(name = "MaKhuyenMai", nullable = false)
   @EqualsAndHashCode.Include
   private String maKhuyenMai;

   @Column(name = "TenKhuyenMai")
   private String tenKhuyenMai;

   @Column(name = "NgayBatDau")
   private Instant ngayBatDau;

   @Column(name = "NgayKetThuc")
   private Instant ngayKetThuc;

   @Column(name = "LuotSuDungConLai")
   private Integer luotSuDungConLai;

   @Column(name = "ChietKhau")
   private Integer chietKhau;

   @OneToMany(mappedBy = "khuyenMai")
   @ToString.Exclude
   private Set<HoaDonThanhToan> listHoaDonThanhToan;

   public CT_KhuyenMai(String maKhuyenMai, String tenKhuyenMai, Instant ngayBatDau, Instant ngayKetThuc, Integer luotSuDungConLai, Integer chietKhau) {
      this.maKhuyenMai = maKhuyenMai;
      this.tenKhuyenMai = tenKhuyenMai;
      this.ngayBatDau = ngayBatDau;
      this.ngayKetThuc = ngayKetThuc;
      this.luotSuDungConLai = luotSuDungConLai;
      this.chietKhau = chietKhau;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CT_KhuyenMai that = (CT_KhuyenMai) o;
      return Objects.equals(maKhuyenMai, that.maKhuyenMai);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(maKhuyenMai);
   }
}