/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Thạch
 */
public enum Enum_LoaiPhong {

   THUONG,
   VIP;

   public static Enum_LoaiPhong getEnum(int value) {
      return Enum_LoaiPhong.values()[value];
   }

   public static int getValue(Enum_LoaiPhong loaiPhong) {
      return loaiPhong.ordinal();
   }

   public static String getTenLoaiPhong(Enum_LoaiPhong loaiPhong) {
      return loaiPhong == THUONG ? "Thường" : "VIP";
   }

   public static Enum_LoaiPhong getLoaiPhong(String tenLoaiPhong) {
      return tenLoaiPhong.equals("Thường") ? THUONG : VIP;
   }

   public static List<Enum_LoaiPhong> getEnumList(List<Integer> arrType) {
      return Stream.of(Enum_LoaiPhong.values()).filter(e -> arrType.contains(Enum_LoaiPhong.getValue(e))).toList();
   }
}
