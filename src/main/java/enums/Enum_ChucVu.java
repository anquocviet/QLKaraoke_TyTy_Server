package enums;

/**
 * @author vie
 */
public enum Enum_ChucVu {
   QUANLY,
   NHANVIENTIEPTAN,
   NHANVIENPHUCVU,
   BAOVE;


   public String getTenChucVu() {
      switch (this) {
         case QUANLY:
            return "Quản lý";
         case NHANVIENTIEPTAN:
            return "Nhân viên tiếp tân";
         case NHANVIENPHUCVU:
            return "Nhân viên phục vụ";
         case BAOVE:
            return "Bảo vệ";
         default:
            return null;
      }
   }
}
