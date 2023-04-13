package HealthyLife;

import java.util.ArrayList;

import CodeBase.Provider;
import CodeBase.ProviderDB;

public class MedicineProviderDB implements ProviderDB {

    private ArrayList<Provider> providers;

    public MedicineProviderDB() {
        createProviders();
    }

    public void createProviders() {
        providers = new ArrayList<Provider>();
        providers.add( new MedicineProvider( "Pharmacy Health" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/pha1.png" ) );
        providers.add( new MedicineProvider( "Pharmacy Cankaya" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/pha2.png" ) );
        providers.add( new MedicineProvider( "Pharmacy Bilkent" , "/Users/ezgicakir/NetBeansProjects/cs415proje/src/images/pha3.png" ) );
    }

    public ArrayList<Provider> getProviders() {
        return providers;
    }

}