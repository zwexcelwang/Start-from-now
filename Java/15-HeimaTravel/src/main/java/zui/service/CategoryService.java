package zui.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import zui.dao.CategoryDao;
import zui.dao.ICategoryDao;
import zui.domain.Category;
import zui.utils.JedisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryService implements ICategoryService {
    private ICategoryDao categoryDao = new CategoryDao();

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1获取jedis客户端
        Jedis jedis = JedisUtils.getJedis();
        //1.2可使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs = null;
        //2.判断查询的集合是否为空
        cs = categoryDao.findAll();
//        if (categorys == null || categorys.size() == 0) {
//
//            System.out.println("从数据库查询....");
//            //3.如果为空,需要从数据库查询,在将数据存入redis
//            //3.1 从数据库查询
//            cs = categoryDao.findAll();
//            //3.2 将集合数据存储到redis中的 category的key
//            for (int i = 0; i < cs.size(); i++) {
//
//                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
//            }
//        } else {
//            System.out.println("从redis中查询.....");
//
//            //4.如果不为空,将set的数据存入list
//            cs = new ArrayList<Category>();
//            for (Tuple tuple : categorys) {
//                Category category = new Category();
//                category.setCname(tuple.getElement());
//                category.setCid((int) tuple.getScore());
//                cs.add(category);
//
//            }
//        }
        return cs;
    }
}