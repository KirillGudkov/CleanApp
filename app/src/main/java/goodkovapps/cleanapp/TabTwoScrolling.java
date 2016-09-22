package goodkovapps.cleanapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * вторая вкладка в активити заказа
 */
public class TabTwoScrolling extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    String [] titleItem, descriptionItem, price;
    ArrayList<DataRecyclerViewProvider> arrayList = new ArrayList<DataRecyclerViewProvider>();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_tab_two_scrolling, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        titleItem = getResources().getStringArray(R.array.titleItem);
        price = getResources().getStringArray(R.array.price);
        descriptionItem = getResources().getStringArray(R.array.descriptionItem);

        int i = 0;
        for (String title: titleItem) {
            DataRecyclerViewProvider provider = new DataRecyclerViewProvider(title, descriptionItem[i], price[i]);
            arrayList.add(provider);
            i++;
        }
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
