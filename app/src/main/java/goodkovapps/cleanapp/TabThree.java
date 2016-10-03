/**
 * Created by sillybird on 21.09.2016.
 *
 * Класс TabThree описывает поведение третей вкладки в активности OrderActivity (окно заказа)
 * Вкладки содержит элементы для добавления фото к заказу,
 * комментария к заказу
 * а так же в этом окне завершается процесс оформления заказа и по нажатию
 * кнопки "готово" выполняется намерение которые вещает для BroadcastReceiver'а о том что нужно
 * достать данные с предыдущих вкладок
 *
 */
package goodkovapps.cleanapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import static android.app.Activity.*;



public class TabThree extends Fragment {
    public final static String BROADCAST_ACTION = "makeOrder";
    public final static int REQUEST_IMAGE_CAPTURE = 1;

    private EditText commentForOrder;
    private Button getData;
    private ImageView preview;
    private FloatingActionButton floatingCamera;
    private Button deletePhoto;
    private Bitmap photo;
    private Bundle extras;

    /**
     * Возникла проблема с onDestroy для фрагмента, при переключении на первую вкладку
     * по этому пришлось сохранять extras через onSaveInstanceState();
     * но код ниже настолько говнокод, что мне аж стыдно
     * при первом запуске активити, когда фото ещё не сделано, после onDestroy вылетает NullPointerException
     * по этому пришлось обрабатывать так, другого варианта не придумал
     * @param savedInstanceState
     */

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.getBundle("photo") != null)
                extras = savedInstanceState.getBundle("photo");
                    if (extras != null) {
                        photo = (Bitmap) extras.get("data");
                        if (photo != null) {
                            preview.setImageBitmap(photo);
                            deletePhoto.setVisibility(View.VISIBLE);
                        }
            }
        }
    }

    /**
     * при дестрое фрагмента сохраняем фото outState, чтобы при onResume() его опять достать
     * @param outState
     */
    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState (outState);
        outState.putBundle ("photo", extras);
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.tab_three, container, false);
        getData = (Button) v.findViewById (R.id.getData);
        commentForOrder = (EditText) v.findViewById(R.id.commentForOrder);
        preview = (ImageView) v.findViewById (R.id.preview);
        deletePhoto = (Button) v.findViewById (R.id.deletePhoto);
        floatingCamera = (FloatingActionButton) v.findViewById (R.id.floatingCamera);

        /**
         * При нажатии кнопки, очевидно, что из превью удаляется фото
         * вьюха делается невидимой и подчищается outState
         */

        deletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview.setImageDrawable(null);
                deletePhoto.setVisibility(View.INVISIBLE);
                savedInstanceState.getBundle("photo").remove("data");
            }
        });

        /**
         * При старте нужно чекнть наличие камеры на устройсте
         * и в случает её отсутствия задизейблить кнопку "прикрепить фото"
         */
        if (!checkCamera()) floatingCamera.setEnabled (false);

        floatingCamera.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera (view);
            }
        });
        return v;
    }

    @Override
    public void onStart () {
        super.onStart();
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(BROADCAST_ACTION);
                intent.addFlags (Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                getActivity().sendBroadcast (intent);
            }
        });
    }

    /**
     * чекает есть ли камера на устройстве и возвращает true\false
     * @return
     */
    public boolean checkCamera () {
        return getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }


    /**
     * Метод запускает камеру
     * @param view
     */
    public void launchCamera (View view) {
        /**
         * В эмуляторе вывалился ексепшн, не знаю что он означает, но на всякий случай добавил try\catch
         */
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
        catch (SecurityException e) {
            Toast.makeText(this.getContext(), "Camera can't run", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * После сделанной фотки запускается этот метод, он достёт данные из Extras
     * и закидывает в превью
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            preview.setImageBitmap(photo);
            deletePhoto.setVisibility(View.VISIBLE);
        }
    }
}
