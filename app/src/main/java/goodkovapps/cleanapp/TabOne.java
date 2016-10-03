package goodkovapps.cleanapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by sillybird on 21.09.2016.
 */

public class TabOne extends Fragment {

    private TextView mCountRooms;
    private TextView mCountToilet;
    private TextView mArea;

    private SeekBar mSeekRooms;
    private SeekBar mSeekToilet;
    private SeekBar mSeekArea;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_one,container,false);

        mCountRooms = (TextView) v.findViewById(R.id.countRooms);
        mCountToilet = (TextView) v.findViewById(R.id.countToilet);
        mArea = (TextView) v.findViewById(R.id.area);

        mSeekRooms = (SeekBar) v.findViewById(R.id.seekBarRooms);
        mSeekToilet = (SeekBar) v.findViewById(R.id.seekBarToilet);
        mSeekArea = (SeekBar) v.findViewById(R.id.seekBarArea);

        mSeekRooms.setMax(100);
        mSeekToilet.setMax(100);
        mSeekArea.setMax(500);

        mSeekRooms.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCountRooms.setText(String.valueOf(mSeekRooms.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekToilet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCountToilet.setText(String.valueOf(mSeekToilet.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekArea.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mArea.setText(String.valueOf(mSeekArea.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;
    }
}
