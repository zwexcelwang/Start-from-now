package zui.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {
//    将java类型转换成数据库需要的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        Long time = date.getTime();
        preparedStatement.setLong(i, time);
    }

//    将数据库中类型转换成java类型
//    String参数：要转换的字段名称
//    ResultSet 查询出的结果集
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        结果集中的long要转换成date
        long l = resultSet.getLong(s);
        Date date = new Date(l);
        return date;
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long l = resultSet.getLong(i);
        Date date = new Date(l);
        return date;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long l = callableStatement.getLong(i);
        Date date = new Date(l);
        return date;
    }


}
