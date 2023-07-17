package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;

public class DashboardPage extends PredefinedActions {
    private static DashboardPage dashboardPage;
    private final PropertyReading dashboardPageProp;

    private DashboardPage() {
        //Private Constructor for Singleton Design Pattern
        dashboardPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "DashboardPageProp.properties");
    }

    public static DashboardPage getDashboardPage() {
        if (dashboardPage == null)
            dashboardPage = new DashboardPage();
        return dashboardPage;
    }

    public String getTitle() {
        return getWebpageTitle();
    }

    public void clickOnMyAccountLink()
    {
        clickOnElement(dashboardPageProp.getValue("myAccountLink"),true);
    }


}
