package com.dunshan.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallMonitorApplicationTests {

    @Test
    public void contextLoads() {

        String sql = "insert into t1(id) values ('1', '4', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'windir', '18061581849', '1', '2018-08-02 10:35:44', NULL, '1', '2009-06-01', '上海', '学生', 'test', '1', '5000', '1', '1', '1')";
    }

}
