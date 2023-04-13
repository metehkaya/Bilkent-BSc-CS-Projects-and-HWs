package MaturePear;

import java.util.ArrayList;

import CodeBase.Provider;
import CodeBase.ProviderDB;

public class ServiceProviderDB implements ProviderDB {

    private ArrayList<Provider> providers;

    public ServiceProviderDB() {
        createProviders();
    }

    public void createProviders() {
        providers = new ArrayList<Provider>();
        providers.add( new ServiceProvider( "Bihter Yöreoðlu" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/bihter.jpg" ) );
        providers.add( new ServiceProvider( "Adnan Ziyagil" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/adnan.jpg" ) );
        providers.add( new ServiceProvider( "Behlül Haznedar" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/behlul.jpg" ) );
    }

    public ArrayList<Provider> getProviders() {
        return providers;
    }

}
