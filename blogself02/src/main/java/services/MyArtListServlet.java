package services;

import dao.ArticleInfoDao;
import models.ArticleInfo;
import models.UserInfo;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HuYu
 * Date: 2021-04-08
 * Time: 12:56
 */
public class MyArtListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从前端获取参数
        int succ =-1;
        String msg = "";
        List<ArticleInfo> list = null;
        HttpSession session =request.getSession(false);
        if(session==null){
            //未登录
            msg="非法请求，请先登录!";
        }else {
            //2、业务处理操作,调用dao层的articleinfodao
            //查询数据库
            UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
            int uid = userInfo.getId();
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();

            try {
                list =articleInfoDao.getListByUID(uid);//查询到数据
                succ =1;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //3、返回结果
        HashMap<String, Object> result = new HashMap<>();
        result.put("succ", succ);
        result.put("msg", msg);
        result.put("list", list);
        ResultJSONUtils.write(response, result);
    }
}
