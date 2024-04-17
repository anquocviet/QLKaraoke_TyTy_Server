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

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "TaiKhoan.findByMaTaiKhoan", query = "SELECT tk FROM TaiKhoan tk WHERE tk.maTaiKhoan = :maTaiKhoan"),
      @NamedQuery(name = "TaiKhoan.findAll", query = "SELECT tk FROM TaiKhoan tk"),
      @NamedQuery(name = "TaiKhoan.findByName", query = "SELECT tk FROM TaiKhoan tk WHERE tk.tenDangNhap = :tenDangNhap"),
      @NamedQuery(name = "TaiKhoan.findByMaNhanVien", query = "SELECT tk FROM TaiKhoan tk WHERE tk.nhanVien.maNhanVien = :maNhanVien"),
      @NamedQuery(name = "TaiKhoan.login", query = "SELECT tk FROM TaiKhoan tk WHERE tk.tenDangNhap = :tenDangNhap AND tk.matKhau = :matKhau")
})
public class TaiKhoan implements Serializable {
   @Serial
   private static final long serialVersionUID = -3066844908200436449L;

   @Id
   @Column(name = "MaTaiKhoan", nullable = false)
   private String maTaiKhoan;

   @Column(name = "TenDangNhap")
   private String tenDangNhap;

   @Column(name = "MatKhau")
   private String matKhau;

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaNhanVien")
   @ToString.Exclude
   private NhanVien nhanVien;

   public TaiKhoan(String maTaiKhoan, String tenDangNhap, String matKhau, NhanVien nhanVien) {
      this.maTaiKhoan = maTaiKhoan;
      this.tenDangNhap = tenDangNhap;
      this.matKhau = matKhau;
      this.nhanVien = nhanVien;
   }
}