package enums;

/**
 * @author vie
 */
public enum Enum_TrangThaiLamViec {
   CONLAMVIEC,
   DANGHIVIEC;

   public static Enum_TrangThaiLamViec getEnum(int index) {
      return Enum_TrangThaiLamViec.values()[index];
   }

   public static String getTrangThaiLamViec(Enum_TrangThaiLamViec trangThai) {
      return trangThai == CONLAMVIEC ? "Còn làm việc" : "Đã nghỉ";
   }
}
