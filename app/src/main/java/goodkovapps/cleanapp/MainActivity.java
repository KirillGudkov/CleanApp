package goodkovapps.cleanapp;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHelper dbHelper;
    String userPhone;
    NavigationView navigationView;
    SQLiteDatabase sqLiteDatabase;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

           Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCollapse);
           setSupportActionBar(toolbar);

           DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
           ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
           drawer.addDrawerListener(toggle);
           toggle.syncState();

           navigationView = (NavigationView) findViewById(R.id.nav_view);
           navigationView.setNavigationItemSelectedListener(this);

           dbHelper = new DBHelper(this);
           sqLiteDatabase = dbHelper.getWritableDatabase();
           updateMenu(navigationView);
    }

    public void updateMenu (NavigationView navigationView) {
        if (checkUser(sqLiteDatabase) == null) {
            navigationView.getMenu().findItem(R.id.registration).setVisible(true);
            navigationView.getMenu().findItem(R.id.profile).setVisible(false);
            navigationView.getMenu().findItem(R.id.exit).setVisible(false);
        }
        else {
            userPhone = checkUser(sqLiteDatabase);
            navigationView.getMenu().findItem(R.id.profile).setVisible(true);
            navigationView.getMenu().findItem(R.id.exit).setVisible(true);
            navigationView.getMenu().findItem(R.id.profile).setTitle("+7 "+userPhone);
        }
    }
    public void deleteUser () {
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_USER, null, null);
    }
    /**
     * Метод проверяет наличие строк в таблице TABLE_USER
     * и при наличии возвращает номер телефона пользователя
     * иначе вернёт null
     * @param database
     * @return
     */
    public String checkUser (SQLiteDatabase database) {
        String phone = null;

        Cursor cursor = database.query (dbHelper.TABLE_USER, null, null, null, null, null, null);
        int phoneIndex = cursor.getColumnIndex(dbHelper.KEY_PHONE);
        if (cursor.moveToNext()) {
            phone = cursor.getString(phoneIndex);

        }
        return phone;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if (id == R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.registration) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.make_order) {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.history) {

        } else if (id == R.id.promo) {

        } else if (id == R.id.price) {

        } else if (id == R.id.act) {

        } else if (id == R.id.call) {

        }
        else if (id == R.id.exit) {
            deleteUser();
            updateMenu(navigationView);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
