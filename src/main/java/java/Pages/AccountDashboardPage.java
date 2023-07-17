package Pages;//import java.util.*;

import Base.PredefinedActions;
import Constants.ConstantPaths;
import Utils.PropertyReading;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class AccountDashboardPage extends PredefinedActions {
    private static AccountDashboardPage accountDashboardPage;
    private final PropertyReading accountDashboardPageProp;
    static Logger log = Logger.getLogger(AccountDashboardPage.class);

    private AccountDashboardPage() {
        //Private Constructor for Singleton Design Pattern
        accountDashboardPageProp = new PropertyReading(ConstantPaths.PROP_PATH + "AccountDashboardPageProp.properties");
    }

    public static AccountDashboardPage getAccountDashboardPage() {
        if (accountDashboardPage == null)
            accountDashboardPage = new AccountDashboardPage();
        return accountDashboardPage;
    }

    public String getAccountDashboardHeadingMsg(){
        return getElementText(getElement(accountDashboardPageProp.getValue("MyDashBoardHeading"),true));
    }

    public void EnterItemNameAndSearch(String name){
        enterText(getElement(accountDashboardPageProp.getValue("searchBox"),true),name);
        clickOnElement(getElement(accountDashboardPageProp.getValue("SearchBoxBtn"),true),false);
    }
    public void navigateToShoppingCartLink()
    {
        clickOnElement(accountDashboardPageProp.getValue("cartLink"),true);
        clickOnElement(accountDashboardPageProp.getValue("viewShoppingCartLink"),true);
    }
}
