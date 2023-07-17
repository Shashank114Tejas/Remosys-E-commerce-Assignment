package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;

import java.util.List;

public class TVPage extends PredefinedActions {
    private static TVPage tvPage;
    private final PropertyReading tvPageProp;


    private TVPage() {
        //Private Constructor for Singleton Design Pattern
        tvPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "TvPageProp.properties");
    }

    public static TVPage  getTvPage() {
        if (tvPage == null)
            tvPage = new TVPage();
        return tvPage;
    }

    public String getSearchSuccessMsg(){
        return getElementText(getElement(tvPageProp.getValue("SearchResultSuccessMsg"),true));
    }

    public List<String> getProductList(){
        return getWebElementListInString(tvPageProp.getValue("AllProductsName"),true);
    }
    public void addToCartByBrandName(String brand) {
        String element = String.format(tvPageProp.getValue("addToCartBtn"), brand);
        clickOnElement(element, true);
    }
}
