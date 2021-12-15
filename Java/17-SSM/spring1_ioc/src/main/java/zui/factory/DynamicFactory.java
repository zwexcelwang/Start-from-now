package zui.factory;

import zui.dao.UserDao;
import zui.dao.impl.UserDaoImpl;

public class DynamicFactory {

    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
