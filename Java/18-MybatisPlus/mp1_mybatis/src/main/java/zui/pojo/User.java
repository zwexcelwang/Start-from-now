package zui.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

@TableName("tb_user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private String email;


}

