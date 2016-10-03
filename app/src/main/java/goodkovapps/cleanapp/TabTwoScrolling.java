package goodkovapps.cleanapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static goodkovapps.cleanapp.R.id.card;

/**
 * вторая вкладка в активити заказа
 */
public class TabTwoScrolling extends Fragment {
    private final static String BROADCAST_ACTION = "makeOrder";
    BroadcastReceiver receiver;
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<Integer> priceInteger = new ArrayList<Integer>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    private String [] titleItem, descriptionItem, price;
    private CheckBox checkBox;
    private CardView card;
    private ArrayList<DataRecyclerViewProvider> arrayList = new ArrayList<DataRecyclerViewProvider>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_tab_two_scrolling, container, false);

        card = (CardView) v.findViewById(R.id.card);
        checkBox = new CheckBox(this.getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        titleItem = getResources().getStringArray(R.array.titleItem);
        price = getResources().getStringArray(R.array.price);
        descriptionItem = getResources().getStringArray(R.array.descriptionItem);


        int i = 0;
        for (String title : titleItem) {
            DataRecyclerViewProvider provider = new DataRecyclerViewProvider(title, descriptionItem[i], price[i], checkBox, false, card);
            arrayList.add(provider);
            i++;
        }

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Обнаружено нажатие на кнопку!",
                        Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(context, FinalOrder.class);
                getChoosed();
                myIntent.putIntegerArrayListExtra("price", priceInteger);
                myIntent.putStringArrayListExtra("title", title);
                context.startActivity(myIntent);
            }
        };

        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        getActivity().registerReceiver(receiver, intentFilter);

        /**
         * Передаётся через intent заголовки выбранных услуг и их цены
         */
        Intent intent = getActivity().getIntent();
        intent.putStringArrayListExtra("title", title);
        intent.putIntegerArrayListExtra("price", priceInteger);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // дерегистрируем (выключаем) BroadcastReceiver
        getActivity().unregisterReceiver(receiver);
    }

    /**
     * достаёт выбранные услуги
     */
    public void getChoosed () {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).isSelected()){
                title.add(arrayList.get(i).getTitleItem());
                priceInteger.add(Integer.parseInt(arrayList.get(i).getPrice()));
                System.out.println("меня кто-то вызвал");
            }
        }
    }
}
