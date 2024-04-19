package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@NamedQueries({
      @NamedQuery(name = "HoaDonThanhToan.findAll", query = "SELECT h FROM HoaDonThanhToan h"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaHoaDon", query = "SELECT h FROM HoaDonThanhToan h WHERE h.maHoaDon = :maHoaDon"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaKhachHang", query = "SELECT h FROM HoaDonThanhToan h WHERE h.khachHang.maKhachHang = :maKhachHang"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaNhanVien", query = "SELECT h FROM HoaDonThanhToan h WHERE h.nhanVien.maNhanVien = :maNhanVien"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaKhuyenMai", query = "SELECT h FROM HoaDonThanhToan h WHERE h.khuyenMai.maKhuyenMai = :maKhuyenMai"),
      @NamedQuery(name = "HoaDonThanhTOan.findByNgayLap", query = "SELECT h FROM HoaDonThanhToan h WHERE h.ngayLap = :ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.findByNgayLap", query = "SELECT h FROM HoaDonThanhToan h WHERE h.ngayLap = :ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.findByMaPhong", query = "SELECT h FROM HoaDonThanhToan h JOIN h.listCTP ct WHERE ct.phong.maPhong = :maPhong"),
      @NamedQuery(name = "HoaDonThanhToan.countBill", query = "SELECT COUNT(h) FROM HoaDonThanhToan h"),
      @NamedQuery(name = "HoaDonThanhToan.countBillByMaKhachHang", query = "SELECT COUNT(h) FROM HoaDonThanhToan h WHERE h.khachHang.maKhachHang = :maKhachHang"),
      @NamedQuery(name = "HoaDonThanhToan.getBillsByDay", query = "SELECT h.ngayLap, COUNT(h) FROM HoaDonThanhToan h WHERE h.ngayLap = :ngayLap GROUP BY h.ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.getBillsByMonth",
            query = "SELECT h.ngayLap, COUNT(h) FROM HoaDonThanhToan h " +
                          "WHERE MONTH(h.ngayLap) = :ngayLap AND YEAR(h.ngayLap) = YEAR(:ngayLap) AND MONTH(h.ngayLap) = MONTH(:ngayLap) GROUP BY h.ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.getBillsByQuarter",
            query = "SELECT h.ngayLap, COUNT(h) FROM HoaDonThanhToan h " +
                          "WHERE QUARTER(h.ngayLap) = QUARTER(:ngayLap) AND YEAR(h.ngayLap) = YEAR(:ngayLap) AND QUARTER(h.ngayLap) = QUARTER(:ngayLap) GROUP BY h.ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.getBillsByYear",
            query = "SELECT h.ngayLap, COUNT(h) FROM HoaDonThanhToan h " +
                          "WHERE YEAR(h.ngayLap) = YEAR(:ngayLap) GROUP BY h.ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.calcMoney", query = "SELECT SUM(h.tongTien) FROM HoaDonThanhToan h"),
      @NamedQuery(name = "HoaDonThanhToan.calcMoneyByMaKhachHang", query = "SELECT SUM(h.tongTien) FROM HoaDonThanhToan h WHERE h.khachHang.maKhachHang = :maKhachHang"),
      @NamedQuery(name = "HoaDonThanhToan.calcMoneyByNgayLap", query = "SELECT SUM(h.tongTien) FROM HoaDonThanhToan h WHERE h.ngayLap = :ngayLap"),
      @NamedQuery(name = "HoaDonThanhToan.calcMoneyByMonth", query = "SELECT SUM(h.tongTien) FROM HoaDonThanhToan h WHERE MONTH(h.ngayLap) = MONTH(:ngayLap) AND YEAR(h.ngayLap) = YEAR(:ngayLap)"),
      @NamedQuery(name = "HoaDonThanhToan.calcMoneyByQuarter", query = "SELECT SUM(h.tongTien) FROM HoaDonThanhToan h WHERE QUARTER(h.ngayLap) = QUARTER(:ngayLap) AND YEAR(h.ngayLap) = YEAR(:ngayLap)"),
      @NamedQuery(name = "HoaDonThanhToan.calcMoneyByYear", query = "SELECT SUM(h.tongTien) FROM HoaDonThanhToan h WHERE YEAR(h.ngayLap) = YEAR(:ngayLap)")
})
public class HoaDonThanhToan implements Serializable {
   @Serial
   private static final long serialVersionUID = 4024898374929922829L;

   @Id
   @Column(name = "MaHoaDon", nullable = false)
   private String maHoaDon;

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

   @ManyToOne(fetch = FetchType.LAZY)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JoinColumn(name = "MaKhuyenMai")
   @ToString.Exclude
   private CT_KhuyenMai khuyenMai;

   @Column(name = "NgayLap")
   private Instant ngayLap;

   @Column(name = "TongTien")
   private Integer tongTien;

   @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
   @ToString.Exclude
   private Set<ChiTietHD_DichVu> listCTDV = new HashSet<>();

   @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
   @ToString.Exclude
   private Set<ChiTietHD_Phong> listCTP = new HashSet<>();

   public HoaDonThanhToan(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, CT_KhuyenMai khuyenMai, Instant ngayLap, Integer tongTien) {
      this.maHoaDon = maHoaDon;
      this.khachHang = khachHang;
      this.nhanVien = nhanVien;
      this.khuyenMai = khuyenMai;
      this.ngayLap = ngayLap;
      this.tongTien = tongTien;
   }
}