package zui.service;

import zui.dao.FavoriteDao;
import zui.dao.IFavoriteDao;
import zui.domain.Favorite;

public class FavoriteService implements IFavoriteService{

    private IFavoriteDao favoriteDao = new FavoriteDao();

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;//如果对象有值，则为true，反之，则为false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }

}
