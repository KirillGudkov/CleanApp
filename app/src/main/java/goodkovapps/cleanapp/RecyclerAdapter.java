package goodkovapps.cleanapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**Адаптер для ресайклервью
 * Created by sillybird on 23.09.2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private ArrayList<DataRecyclerViewProvider> arrayList = new ArrayList<DataRecyclerViewProvider>();

    public RecyclerAdapter (ArrayList<DataRecyclerViewProvider> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        DataRecyclerViewProvider provider = arrayList.get(position);

        holder.titleItem.setText(provider.getTitleItem());
        holder.descriptionItem.setText(provider.getDescriptionItem());
        holder.price.setText(provider.getPrice());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView titleItem, descriptionItem, price;

        public RecyclerViewHolder (View view) {
            super(view);
            titleItem = (TextView) view.findViewById(R.id.titleItem);
            descriptionItem = (TextView) view.findViewById(R.id.descriptionItem);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
}
