package top.conanan.studyproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class StudyProjectApplicationTests {

    @Test
    void contextLoads() {
    }


    public static void main(String[] args) {




        HashMap<String, Object> map = new HashMap<>();

        map.put("1", "a");
        map.put("1", "b");

        System.out.println(map);

    }

}
