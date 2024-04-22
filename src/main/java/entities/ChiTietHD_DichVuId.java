package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ChiTietHD_DichVuId implements Serializable {
   @Serial
   private static final long serialVersionUID = -8611069352975663509L;

   @Column(name = "MaHoaDon", nullable = false)
   private String maHoaDon;

   @Column(name = "MaDichVu", nullable = false)
   private String maDichVu;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
      ChiTietHD_DichVuId entity = (ChiTietHD_DichVuId) o;
      return Objects.equals(this.maDichVu, entity.maDichVu) &&
                   Objects.equals(this.maHoaDon, entity.maHoaDon);
   }

   @Override
   public int hashCode() {
      return Objects.hash(maDichVu, maHoaDon);
   }

}