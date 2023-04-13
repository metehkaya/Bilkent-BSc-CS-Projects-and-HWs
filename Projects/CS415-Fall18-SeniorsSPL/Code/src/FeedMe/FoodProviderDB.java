package FeedMe;

import java.util.ArrayList;

import CodeBase.Provider;
import CodeBase.ProviderDB;

public class FoodProviderDB implements ProviderDB {

    private ArrayList<Provider> providers;

    public FoodProviderDB() {
        createProviders();
    }

    public void createProviders() {
        providers = new ArrayList<Provider>();
        providers.add( new FoodProvider( "Pizza House" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/res1.jpg" ) );
        providers.add( new FoodProvider( "Waffle In Love" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/res2.jpg" ) );
        providers.add( new FoodProvider( "BurgerKing" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/res3.jpg" ) );
    }

    public ArrayList<Provider> getProviders() {
        return providers;
    }

}