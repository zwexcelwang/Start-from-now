package zui.dao;

import zui.domain.RouteImg;

import java.util.List;

public interface IRouteImgDao {
    /**
     * 根据route的id查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
