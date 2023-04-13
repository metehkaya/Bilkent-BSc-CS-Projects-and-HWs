package CodeBase;

import java.util.ArrayList;

public class Context {

    private ProviderDB providerDB;

    public Context( ProviderDB providerDB ) {
        this.providerDB = providerDB;
    }

    public ArrayList<Provider> getProviders() {
        return providerDB.getProviders();
    }

}