package cn.data.laoluo.ormlite_project;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private Button addButton;
    private UserDao userDao;
    private Button updateButton;
    private Button queryButton;

    private final static  String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = (Button) this.findViewById(R.id.button);
        updateButton = (Button) this.findViewById(R.id.button2);
        queryButton  =(Button)this.findViewById(R.id.button3);
        userDao = new UserDao(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName("张三");
                user.setDesc("北京人");

                User user1 = new User();
                user1.setName("jack");
                user1.setDesc("湖北");

                User user2 = new User();
                user2.setName("tom");
                user2.setDesc("广西");

                userDao.addUser(user);
                userDao.addUser(user1);
                userDao.addUser(user2);
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setId(1);
                user.setName("刘德华");
                user.setDesc("香港");
                userDao.updateUser(user);
            }
        });

        //查询按钮
        queryButton.setOnClickListener(new View.OnClickListener(){
            @Override
           public  void onClick(View v){

             // List<User> list =  userDao.queryBuilder1();
                List<User> list = userDao.queryBuilder2();
                Log.i(TAG,list.toString());
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
