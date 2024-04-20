package services;

import entities.Phong;
import enums.Enum_LoaiPhong;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhongServiceTest {

    @Test
    void addPhong() {
        boolean result = new PhongService().addPhong(new Phong("P02", Enum_LoaiPhong.THUONG, 0, 10, 100000));
        System.out.println(result);
    }

    @Test
    void updatePhong() {
        boolean result = new PhongService().updatePhong(new Phong("P02", Enum_LoaiPhong.THUONG, 0, 10, 99999));
        System.out.println(result);
    }

    @Test
    void deletePhong() {
        boolean result = new PhongService().deletePhong("P02");
        System.out.println(result);
    }

    @Test
    void findAll() {
        List<Phong> rooms = new PhongService().findAll();
        rooms.forEach(System.out::println);
    }

    @Test
    void findByMaPhong() {
        Phong room = new PhongService().findByMaPhong("P01");
        System.out.println(room);
    }

    @Test
    void countRoomStatus() {
        long count = new PhongService().countRoomStatus(0);
        System.out.println(count);
    }

    @Test
    void findRoomByTypeStatusCapacity() {
        List<Integer> arrType = List.of(0, 1);
        List<Integer> arrStatus = List.of(0, 1);
        List<Phong> rooms = new PhongService().findRoomByTypeStatusCapacity(arrType, arrStatus, 10);
        rooms.forEach(System.out::println);
    }

    @Test
    void findRoomByStatus() {
        List<Phong> rooms = new PhongService().findRoomByStatus(0);
        rooms.forEach(System.out::println);
    }
}