package cn.data.laoluo.ormlite_project;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by luoliwen on 16/4/28.
 * 完成对数据库的CRUD的操作
 */
public class UserDao {
    private Context context;
    private Dao<User, Integer> userDao;
    private DataBaseHelper helper;

    public UserDao(Context context) {
        this.context = context;
        helper = DataBaseHelper.getInstance(context);
        try {
            userDao = helper.getDao(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addUser(User user) {
        try {
            userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * update user_info set name=XX  where id = XX
     *
     * @param user
     * @param id
     */
    public void updateUserByID(User user, Integer id) {
        try {
            userDao.updateId(user, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     * update user_info set name=XX   where id = XX
     *
     * @param user
     */
    public void updateUserByBuilder(User user) {
        try {
            UpdateBuilder builder = userDao.updateBuilder();
            builder.updateColumnValue("name", user.getName()).where().eq("id", 1);
            builder.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try {
            userDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMulUser(List<User> users) {
        try {
            userDao.delete(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByIDs(List<Integer> ids) {
        try {
            userDao.deleteIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * select * from user_info
     *
     * @return
     */
    public List<User> listAll() {
        try {
            return userDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询条件 一
     * select * from user_info where  (age> 23 and name like ? ) and XXXX
     *
     * @return
     */
    public List<User> queryBuilder1() {
        List<User> list = null;
        QueryBuilder<User, Integer> queryBuilder = userDao.queryBuilder();
        //声明的是一个where 条件
        Where<User, Integer> where = queryBuilder.where();
        try {
            where.eq("name", "刘德华");
            where.and();
            where.eq("desc", "香港");
            where.prepare();
            //select * from user_info where name ='刘德华' and desc='香港'
            list = queryBuilder.query();

            //list = userDao.queryBuilder().where().eq("name", "刘德华").and().eq("desc", "香港").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<User> queryBuilder2() {
        List<User> list = null;

        QueryBuilder<User, Integer> queryBuilder = userDao.queryBuilder();
        Where<User, Integer> where = queryBuilder.where();
        try {
            list = where.or(where.and(where.eq("name", "jack"), where.eq("desc", "湖北")),
                    where.and(where.eq("name", "刘德华"), where.eq("id", "2"))).query();

            where.and(where.in("name","jack","刘德华"),where.eq("name","jack"));
            // where (name in("jack","刘德华")) and name = "jack"
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
