package goodkovapps.cleanapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sillybird on 21.09.2016.
 */

public class TabOne extends Fragment {

    private Button addRoom;
    private Button addToilet;
    private Button addArea;

    private Button removeRoom;
    private Button removeToilet;
    private Button removeArea;

    private TextView countRooms;
    private TextView countToilet;
    private TextView area;

    private int rooms = 1;
    private int toilets = 1;
    private int areas = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_one,container,false);

        addRoom = (Button) v.findViewById(R.id.addRoom);
        addToilet = (Button) v.findViewById(R.id.addToilet);
        addArea = (Button) v.findViewById(R.id.addArea);

        removeRoom = (Button) v.findViewById(R.id.removeRoom);
        removeToilet = (Button) v.findViewById(R.id.removeToilet);
        removeArea = (Button) v.findViewById(R.id.removeArea);

        countRooms = (TextView) v.findViewById(R.id.countRooms);
        countToilet = (TextView) v.findViewById(R.id.countToilet);
        area = (TextView) v.findViewById(R.id.area);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.addRoom:
                        countRooms.setText(Integer.toString(++rooms));
                        break;
                    case R.id.addToilet:
                        countToilet.setText(Integer.toString(++toilets));
                        break;
                    case R.id.addArea:
                        area.setText(Integer.toString(++areas));
                        break;
                    case R.id.removeRoom:
                        countRooms.setText(Integer.toString(--rooms));
                        break;
                    case R.id.removeToilet:
                        countToilet.setText(Integer.toString(--toilets));
                        break;
                    case R.id.removeArea:
                        area.setText(Integer.toString(--areas));
                        break;
                }
            }
        };
        addRoom.setOnClickListener(onClickListener);
        addToilet.setOnClickListener(onClickListener);
        addArea.setOnClickListener(onClickListener);
        removeRoom.setOnClickListener(onClickListener);
        removeToilet.setOnClickListener(onClickListener);
        removeArea.setOnClickListener(onClickListener);

        return v;
    }
}
