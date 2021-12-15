package zui.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

//标志该类是Spring的核心配置类
@Configuration
//<!--    配置组件扫描, 扫这个包及其子包-->
//<context:component-scan base-package="zui"></context:component-scan>
@ComponentScan("zui")
@Import(DataSourceConfiguration.class)

public class SpringConfiguration {

}
