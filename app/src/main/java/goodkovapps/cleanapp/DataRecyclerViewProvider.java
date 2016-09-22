package goodkovapps.cleanapp;

import android.widget.CheckBox;

/**Класс содержит сеттеры и геттеры элементов ресайклервью
 * Created by sillybird on 23.09.2016.
 */

public class DataRecyclerViewProvider {
    private String titleItem;
    private String descriptionItem;
    private String price;
    private CheckBox checkBox;

    public DataRecyclerViewProvider (String titleItem, String descriptionItem, String price) {
        this.setTitleItem(titleItem);
        this.setDescriptionItem(descriptionItem);
        this.setPrice(price);
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
