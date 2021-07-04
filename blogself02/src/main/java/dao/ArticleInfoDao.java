package dao;

import models.ArticleInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HuYu
 * Date: 2021-04-08
 * Time: 13:10
 */
public class ArticleInfoDao {

     //我的文章列表
    public List<ArticleInfo> getListByUID(int uid) throws SQLException {
        List<ArticleInfo> list = new ArrayList<>();
        //连接数据库
        Connection connection = DBUtils.getConnection();
        String sql = "select * from articleinfo where uid=?";
        PreparedStatement statement = connection.prepareStatement(sql);//执行sql语句；
        statement.setInt(1,uid);//得到uid的结果；
        // 查询数据库并返回结果
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setId(resultSet.getInt("id"));
            articleInfo.setRecount(resultSet.getInt("recount"));
            articleInfo.setTitle(resultSet.getString("title"));
            articleInfo.setContent(resultSet.getString("Content"));
            articleInfo.setCreatetime(resultSet.getDate("createtime"));
            list.add(articleInfo);
        }
        // 数据库的关闭
        DBUtils.close(connection, statement, resultSet);
        return list;
    }
    //文章的删除；
    public static int del(int id) throws SQLException {
        int result = 0;
        if(id>0){
            //1
            Connection connection = DBUtils.getConnection();
            String sql ="delete from articleinfo where id=?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setInt(1,id);
            //2、执行数据库的删除
            result = statement.executeUpdate();
            //3、删除
            DBUtils.close(connection,statement,null);

        }
        return result;
    }


}
