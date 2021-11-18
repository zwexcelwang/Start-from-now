package zui.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import zui.domain.Category;
import zui.domain.ResultInfo;
import zui.domain.User;
import zui.service.CategoryService;
import zui.service.ICategoryService;
import zui.service.IUserService;
import zui.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private ICategoryService service = new CategoryService();

    /**
     * 查询所有
     * @param req
     * @param resp
     * @throws IOException
     */
    public void findAllCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.调用service查询所有
        List<Category> cs = service.findAll();
        //2.序列化json返回
       /* ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);*/
        writeValue(cs, resp);
    }


}
