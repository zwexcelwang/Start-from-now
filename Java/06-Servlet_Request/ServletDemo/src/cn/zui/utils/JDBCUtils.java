package cn.zui.utils;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类，使用Durid连接池
 */
public class JDBCUtils {

    private static DataSource ds;


    static {
        //1.加载配置文件
        Properties prop = new Properties();
        //2. 使用ClassLoader加载配置文件，获取字节输入流
        InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            prop.load(inputStream);
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /**
     * 获取连接池对象
     */
    public static DataSource getDataSource() {
        return ds;
    }
    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
