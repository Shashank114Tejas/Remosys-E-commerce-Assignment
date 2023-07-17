package java.TestScripts;//import java.util.*;

import Pages.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyTechPandaTest extends TestBase {
    static Logger log = Logger.getLogger(VerifyTechPandaTest.class);


    @Test(priority = 0,description = "Verify user is on able to access the url and lands on the DashboardPage")
    public void getDashboardPageTitle() {
        DashboardPage dashboardPage=getDashboardObj();
        Assert.assertEquals(dashboardPage.getTitle(),"Home page","User is not able to access the url");
    }


    @Test(priority = 1,description = "verify after clicking on MyAccountLink user navigates to login page")
    public void navigateToLoginPage(){
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        Assert.assertEquals(loginOrCreateAccountPage.getLoginPageUrl(),"http://live.techpanda.org/index.php/customer/account/login/","user is not on login page");
    }



    @Test(priority = 3,description = "enter valid credentials for login")
    public void enterValidCredentialsForLogin(){
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        loginOrCreateAccountPage.loginIntoAccount(loginOrCreateAccountPage.getCredList().get(0),loginOrCreateAccountPage.getCredList().get(1));
        loginOrCreateAccountPage.clickOnLoginBtn();
        AccountDashboardPage accountDashboardPage=getAccountDashboardPageObj();
        Assert.assertEquals(accountDashboardPage.getAccountDashboardHeadingMsg(),"MY DASHBOARD","AccountDashboard Heading didn't matched");
    }



    @Test(priority = 4,description = "navigate to accountDashboardPage and Search for a product")
    public void navigateToAccountDashboardPageAndSearchAnItem(){
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        loginOrCreateAccountPage.loginIntoAccount(loginOrCreateAccountPage.getCredList().get(0),loginOrCreateAccountPage.getCredList().get(1));
        loginOrCreateAccountPage.clickOnLoginBtn();
        AccountDashboardPage accountDashboardPage=getAccountDashboardPageObj();
        accountDashboardPage.EnterItemNameAndSearch("Tv");
        TVPage tvPage=getTvPageObj();
        Assert.assertEquals(tvPage.getSearchSuccessMsg(),"SEARCH RESULTS FOR 'TV'","Success msg didn't match");
    }



    @Test(priority = 5,description = "print all the products displayed of the searched item")
    public void getAllProductsList(){
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        loginOrCreateAccountPage.loginIntoAccount(loginOrCreateAccountPage.getCredList().get(0),loginOrCreateAccountPage.getCredList().get(1));
        loginOrCreateAccountPage.clickOnLoginBtn();
        AccountDashboardPage accountDashboardPage=getAccountDashboardPageObj();
        accountDashboardPage.EnterItemNameAndSearch("Tv");
        TVPage tvPage=getTvPageObj();
        List<String> productNames = tvPage.getProductList();
        for(String name:productNames){
            System.out.println(name);
        }
    }


    @Test(priority = 6,description = "Add a product to cart by name {'Samsung LCD' or 'LG LCD'}")
    public void addProductToCartByName(){
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        loginOrCreateAccountPage.loginIntoAccount(loginOrCreateAccountPage.getCredList().get(0),loginOrCreateAccountPage.getCredList().get(1));
        loginOrCreateAccountPage.clickOnLoginBtn();
        AccountDashboardPage accountDashboardPage=getAccountDashboardPageObj();
        accountDashboardPage.EnterItemNameAndSearch("Tv");
        TVPage tvPage=getTvPageObj();
        tvPage.addToCartByBrandName("LG LCD");
        ShoppingCartPage shoppingCartPage=getShoppingCartPageObj();
        Assert.assertEquals(shoppingCartPage.getShoppingCartPageHeading(),"SHOPPING CART","Headings didn't matched");
    }



    @Test(priority = 7,description = "Update product quantity in cart by name {'Samsung LCD' or 'LG LCD'}")
    public void updateProductQuantityPresentInTheCar(){
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        loginOrCreateAccountPage.loginIntoAccount(loginOrCreateAccountPage.getCredList().get(0),loginOrCreateAccountPage.getCredList().get(1));
        loginOrCreateAccountPage.clickOnLoginBtn();
        AccountDashboardPage accountDashboardPage=getAccountDashboardPageObj();
        accountDashboardPage.EnterItemNameAndSearch("Tv");
        TVPage tvPage=getTvPageObj();
        tvPage.addToCartByBrandName("LG LCD");
        ShoppingCartPage shoppingCartPage=getShoppingCartPageObj();
        shoppingCartPage.updateQuantityOfProduct("LG LCD","10");
        int singleItemPrice=shoppingCartPage.getItemPrice("LG LCD");
        Assert.assertEquals(singleItemPrice*10,shoppingCartPage.getSubTotal(),"Product quantity not updated Successfully");
    }


    @Test(priority = 8,description = "Delete the product already present in the cart and Logout")
    public void deleteProductFromTheCartAndLogout() throws InterruptedException {
        DashboardPage dashboardPage=getDashboardObj();
        dashboardPage.clickOnMyAccountLink();
        LoginOrCreateAccountPage loginOrCreateAccountPage=getLoginOrCreatePageObj();
        loginOrCreateAccountPage.loginIntoAccount(loginOrCreateAccountPage.getCredList().get(0),loginOrCreateAccountPage.getCredList().get(1));
        loginOrCreateAccountPage.clickOnLoginBtn();
        AccountDashboardPage accountDashboardPage=getAccountDashboardPageObj();
        accountDashboardPage.navigateToShoppingCartLink();
        ShoppingCartPage shoppingCartPage=getShoppingCartPageObj();
        shoppingCartPage.deleteItemByName("LG LCD");
        Assert.assertEquals(shoppingCartPage.getShoppingCartEmptyMsg(),"SHOPPING CART IS EMPTY","Shopping cart is not empty even after deleting the product from the cart");
        shoppingCartPage.logoutUser();
        Thread.sleep(5000);
        Assert.assertEquals(dashboardPage.returnCurrentUrl(),"http://live.techpanda.org/index.php/","user is not logged out from his account");
    }
}