package dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HuYu
 * Date: 2021-04-07
 * Time: 17:32
 */
public class UserInfoDao {
    /**
     * 添加用户【注册功能】
     */
public static int add(String username, String password) throws SQLException {
    int result = 0;
    Connection connection = DBUtils.getConnection(); //连接到数据库
    String sql ="insert into userinfo(username,password) values(?,?)";
    PreparedStatement statement = connection.prepareStatement(sql);

    statement.setString(1, username);
    statement.setString(2, password);
    result = statement.executeUpdate();//数据库的执行
    // 关闭数据库连接
    DBUtils.close(connection, statement, null);

    return result;
}

    /**
     * 查询用户【登录功能】
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public UserInfo getUser(String username, String password) throws SQLException {
    UserInfo userInfo = new UserInfo();
    //查询数据库
    Connection connection = DBUtils.getConnection();
    String sql = "select * from userinfo where username=? and password=?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, username);
    statement.setString(2, password);
    // 查询数据库
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
        userInfo.setId(resultSet.getInt("id"));
        userInfo.setUsername(resultSet.getString("username"));
        userInfo.setPassword(resultSet.getString("password"));
        //..
    }
    // 关闭数据库连接
    DBUtils.close(connection, statement, resultSet);
    return userInfo;
}

}
