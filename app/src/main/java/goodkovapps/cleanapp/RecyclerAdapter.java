package goodkovapps.cleanapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**Адаптер для ресайклервью
 * Created by sillybird on 23.09.2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private ArrayList<DataRecyclerViewProvider> arrayList = new ArrayList<DataRecyclerViewProvider>();
    boolean[] checked;

    public RecyclerAdapter (ArrayList<DataRecyclerViewProvider> arrayList) {
        this.arrayList = arrayList;
        checked = new boolean[arrayList.size()];
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        DataRecyclerViewProvider provider = arrayList.get(position);
        holder.checkBox.setChecked(arrayList.get(position).isSelected());
        holder.checkBox.setTag(arrayList.get(position));
        holder.card.setTag(arrayList.get(position)+"card");
        holder.titleItem.setText(provider.getTitleItem());
        holder.descriptionItem.setText(provider.getDescriptionItem());
        holder.price.setText(provider.getPrice());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CardView cardView = (CardView) v;
                CheckBox checkBox = (CheckBox) cardView.getRootView().findViewWithTag(cardView.getTag().toString().substring(0, cardView.getTag().toString().length() - 4));

                DataRecyclerViewProvider viewProvider = (DataRecyclerViewProvider) checkBox.getTag();

                viewProvider.setSelected(checkBox.isChecked());
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                DataRecyclerViewProvider viewProvider = (DataRecyclerViewProvider) cb.getTag();

                viewProvider.setSelected(cb.isChecked());
            }
        });
    }

    public boolean[] getChecked() {
        return checked;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView titleItem, descriptionItem, price;
        public CheckBox checkBox;
        public CardView card;

        public RecyclerViewHolder (View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.card);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            titleItem = (TextView) view.findViewById(R.id.titleItem);
            descriptionItem = (TextView) view.findViewById(R.id.descriptionItem);
            price = (TextView) view.findViewById(R.id.price);
        }
    }
}
