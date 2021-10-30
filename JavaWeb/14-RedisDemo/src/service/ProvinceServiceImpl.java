package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.IProvinceDao;
import dao.ProvinceDaoImpl;
import domain.Province;
import redis.clients.jedis.Jedis;
import utils.JedisPoolUtils;

import java.util.List;

public class ProvinceServiceImpl implements IProvinceService{
    private IProvinceDao dao = new ProvinceDaoImpl();
    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAllJson() throws JsonProcessingException {
        //先从redis中查询数据
        //获取客户端连接
        Jedis jedis = JedisPoolUtils.getJedis();
        String provinceJson = jedis.get("province");
        //判断provinceJson数据是否为null
        if(provinceJson == null || provinceJson.length() == 0) {
            System.out.println("redis中没数据，查询数据库。。");
            List<Province> list = dao.findAll();
            ObjectMapper objectMapper = new ObjectMapper();
            provinceJson = objectMapper.writeValueAsString(list);

            jedis.set("province", provinceJson);
            jedis.close();
        }else{
            System.out.println("redis中有数据，查询Redis。。");
        }
        return provinceJson;
    }
}
