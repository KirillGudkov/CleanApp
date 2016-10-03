package goodkovapps.cleanapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.widget.CheckBox;

/**
 * Created by sillybird on 23.09.2016.
 */

public class DataRecyclerViewProvider {
    private String titleItem;
    private String descriptionItem;
    private String price;
    private boolean isSelected;
    private CheckBox checkBox;




    private CardView card;

    public DataRecyclerViewProvider (String titleItem, String descriptionItem, String price, CheckBox checkBox, boolean isSelected, CardView card) {
        this.setTitleItem(titleItem);
        this.setDescriptionItem(descriptionItem);
        this.setPrice(price);
        this.setCheckBox(checkBox);
        this.isSelected = isSelected;
        this.setCard(card);
    }

    public void setCard(CardView card) {
        this.card = card;
    }

    public CardView getCard() {
        return card;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {

        return price;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    public String getTitleItem() {

        return titleItem;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
