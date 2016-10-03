package goodkovapps.cleanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class FinalOrder extends AppCompatActivity{
    TextView listService;
    TextView sum;
    Intent intent;
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<Integer> priceInteger = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);
        intent = getIntent();
        title = intent.getStringArrayListExtra("title");
        priceInteger = intent.getIntegerArrayListExtra("price");
        listService = (TextView)findViewById(R.id.service);
        sum = (TextView)findViewById(R.id.sum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getData();
    }

    public void getData () {
        System.out.println("ВЫзвн метод getData()");
        for (int i = 0; i < title.size(); i++) {
            listService.setText("");
            sum.setText("");
            listService.setText(listService.getText()+"\n"+title.get(i));
            sum.setText(sum.getText()+"\n"+priceInteger.get(i));
        }
    }
}
