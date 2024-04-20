package services;

import entities.DichVu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DichVuServiceTest {

    DichVuService dichVuService;

    @BeforeEach
    void setUp() {
        dichVuService = new DichVuService();
    }

    @AfterEach
    void tearDown() {
        dichVuService.close();
    }


    @Test
    void findDichVuById() {
        // Arrange
        String maDichVu = "02"; // replace with an ID that exists in your database

        // Act
        List<DichVu> result = dichVuService.findDichVuById(maDichVu);

        // Assert
        if (!result.isEmpty()) {
            System.out.println("Đã tìm thấy các đối tượng DichVu với ID có chứa maDichVu là " + maDichVu + ":");
            for (DichVu dichVu : result) {
                assertTrue(dichVu.getMaDichVu().contains(maDichVu), "Tất cả ID DichVu trả về đều phải chứa maDichVu " + maDichVu);
                System.out.println(dichVu);
            }
        } else {
            System.out.println("Không tìm thấy đối tượng DichVu nào với ID có chứa maDichVu là " + maDichVu);
        }
    }

    @Test
    void findAllDichVu() {
        // Act
        List<DichVu> result = dichVuService.findAllDichVu();

        // Assert
        if (!result.isEmpty()) {
            System.out.println("Đã tìm thấy các đối tượng DichVu:");
            for (DichVu dichVu : result) {
                System.out.println(dichVu);
            }
        } else {
            System.out.println("Không tìm thấy đối tượng DichVu nào");
        }
    }

    @Test
    void deleteDichVu() {
        // Arrange
        String maDichVu = "DV020"; // replace with an ID that exists in your database

        // Act
        DichVu dv = dichVuService.findDichVuById(maDichVu).get(0); // get the first DichVu with the given maDichVu
        boolean result = false;
        if (dv != null) {
            result = dichVuService.deleteDichVu(dv);
            if (result) {
                System.out.println("Xóa DichVu thành công");
            }
        } else {
            System.out.println("Không tìm thấy đối tượng DichVu với ID có maDichVu là " + maDichVu);
        }
    }

    @Test
    void addDichVu() {
        // Arrange
        DichVu dv = new DichVu();
        dv.setMaDichVu("DV020");
        dv.setTenDichVu("Bia Honey");
        dv.setSoLuongTon(99);
        dv.setDonViTinh("Lon");
        dv.setDonGia(30000);
        dv.setAnhMinhHoa("bia-honey.png");

        // Act
        boolean result = dichVuService.addDichVu(dv);

        // Assert
        assertTrue(result, "Thêm DichVu không thành công");
        if (result) {
            System.out.println("Thêm DichVu thành công");
        }
    }

    @Test
    void updateDichVu() {
        // Arrange
        String maDichVu = "DV020"; // replace with an ID that exists in your database
        DichVu dv = dichVuService.findDichVuById(maDichVu).get(0); // get the first DichVu with the given maDichVu
        dv.setTenDichVu("Bia Honey Updated");
        dv.setSoLuongTon(100);
        dv.setDonViTinh("Lon");
        dv.setDonGia(35000);
        dv.setAnhMinhHoa("bia-honey-updated.png");

        // Act
        boolean result = dichVuService.updateDichVu(dv);

        // Assert
        assertTrue(result, "Cập nhật DichVu không thành công");
        if (result) {
            System.out.println("Cập nhật DichVu thành công");
        }

    }
}
