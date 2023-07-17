package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;

public class ShoppingCartPage extends PredefinedActions {
    private static ShoppingCartPage shoppingCartPage;
    private final PropertyReading shoppingCartPageProp;


    private ShoppingCartPage() {
        //Private Constructor for Singleton Design Pattern
        shoppingCartPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "ShoppingCartPageProp.properties");
    }

    public static ShoppingCartPage getShoppingCartPage() {
        if (shoppingCartPage == null)
            shoppingCartPage = new ShoppingCartPage();
        return shoppingCartPage;
    }

    public String getShoppingCartPageHeading(){
        return getElementText(getElement(shoppingCartPageProp.getValue("ShoppingCartHeading"),true));
    }


    public Double getSubTotal() {
        return Double.parseDouble(getElementText(getElement(shoppingCartPageProp.getValue("subTotal"), true)).replace("$", "").replace(",", ""));
    }

    public int getItemPrice(String productName)
    {
        String s = String.format(shoppingCartPageProp.getValue("itemPrice"),productName);
       return Integer.parseInt(getElementText(getElement(s,true)).replace("$","").replace(".00",""));
    }
    public void updateQuantityOfProduct(String productName, String quantity)
    {
        String s = String.format(shoppingCartPageProp.getValue("updateQuantity"),productName);
        clickOnElement(s,true);
        clearElementField(s,true);
        enterText(getElement(s,true),quantity);
        clickOnElement(shoppingCartPageProp.getValue("updateBtn"),true);
    }


    public void deleteItemByName(String brandName)
    {
        String s = String.format(shoppingCartPageProp.getValue("ItemDeleteBtn"),brandName);
        clickOnElement(s,true);
    }
    public String getShoppingCartEmptyMsg(){
        return getElementText(getElement(shoppingCartPageProp.getValue("ShoppingCartPageTitle"),true));
    }

    public void logoutUser(){
        clickOnElement(getElement(shoppingCartPageProp.getValue("AccountLink"),true),true);
        clickOnElement(getElement(shoppingCartPageProp.getValue("AccountLogoutLink"),true),true);
    }



}
