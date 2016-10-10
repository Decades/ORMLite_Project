package cn.data.laoluo.ormlite_project;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by luoliwen on 16/4/27.
 */
@DatabaseTable(tableName = "user_info")
public class User {
    @DatabaseField(generatedId = true)
    private int id;//数据库的主键 primary key
    @DatabaseField(columnName = "name")
    private  String name;
    @DatabaseField(columnName = "desc")
    private String desc;

    public User(){

    }

    public User(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
