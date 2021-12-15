package zui.factory;

import zui.dao.UserDao;
import zui.dao.impl.UserDaoImpl;

public class StaticFactory {
    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
