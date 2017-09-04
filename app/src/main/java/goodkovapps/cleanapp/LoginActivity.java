/**
 * Создан sillybird 20.09.2016
 *
 * Активити регистрации номера телефона и подтверждения через смс
 * используется API сайта sms.ru
 * с помощью http отправляется GET запрос содержащий номер и текст ссообения
 *
 */

package goodkovapps.cleanapp;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private Button send;
    private Button done;
    private EditText phone;
    private EditText gettingCode;
    private PendingIntent pi;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        done = (Button) findViewById(R.id.btn_done);
        send = (Button) findViewById(R.id.sendSMS);
        phone = (EditText) findViewById(R.id.phone);
        gettingCode = (EditText) findViewById(R.id.view_for_code);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        pi = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        getSupportActionBar().setElevation(0);

        /**
         * Обработчик нажатия на кнопку "отправить код"
         * содержит вызов метода для отправки кода доступа
         */
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendMessage();
                    send.setText("Отправить снова");
                    done.setEnabled(true);
                    gettingCode.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            });

        /**
         * Обработчик проверит введённый код в editText на соответствие сгенерированному
         */
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gettingCode.getText().toString().equals(code)) {
                    Toast.makeText(LoginActivity.this, "code is valid", Toast.LENGTH_SHORT).show();

                    DBHelper dbHelper = new DBHelper(LoginActivity.this);
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(dbHelper.KEY_PHONE, phone.getText().toString());
                    database.insert(dbHelper.TABLE_USER, null, contentValues);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(LoginActivity.this, "code is not valid", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                    /**
                     * Обрабатывает конопку в аппбаре "домой"
                     * Прбивает текущую активность и стартует предыдущую
                     */
                    this.finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * Метод генерирует "случайное" 4-х значное число (код доступа)
     * @param code
     * @return возвращает строку
     */
    public String generateCode (String code) {
        return code = Integer.toString(new Random(
                      System.currentTimeMillis()).nextInt(10000 - 1000) + 1000);
    }

    /**
     * Метод отправляет сгенерированный 4-х значный код на указанный номер
     * @throws IOException
     */
    public void sendMessage () throws IOException {
        /**
         * этот фрагмент кода решает проблему возникающую при отправке http-запроса
         * понятия не имею что он делает, но без этого запрос не отправляется
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            code = generateCode(code);

            //TODO: удалить строку с sout, после отладки механизма авторизации
            System.out.println(code);

            HttpURLConnection connection = (HttpURLConnection) new URL
                    ("http://sms.ru/sms/send?api_id=B0DF083C-C411-51DA-D018-EF0C6FB3C9EB&to=7"+
                            phone.getText().toString()+
                            "&text="+code).openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();
        }
        catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Can't send. Check your Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

}

