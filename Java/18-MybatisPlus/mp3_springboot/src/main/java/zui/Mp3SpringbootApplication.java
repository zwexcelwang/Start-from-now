package zui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("zui.mapper") //设置mapper接口的扫描包
@SpringBootApplication
public class Mp3SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mp3SpringbootApplication.class, args);
    }

}
