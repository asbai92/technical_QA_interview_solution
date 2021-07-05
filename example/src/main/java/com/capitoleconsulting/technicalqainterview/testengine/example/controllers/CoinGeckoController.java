package com.capitoleconsulting.technicalqainterview.testengine.example.controllers;

import com.capitoleconsulting.technicalqainterview.testengine.core.DefaultService;
import com.capitoleconsulting.technicalqainterview.testengine.example.model.CoinGeckoInfo;
import com.capitoleconsulting.technicalqainterview.testengine.models.ServiceInformation;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CoinGeckoController extends DefaultService {

    /**
     * Constructor
     *
     * @param serviceInformation
     */
    protected CoinGeckoController(ServiceInformation serviceInformation) {
        super(serviceInformation);
    }

    @SneakyThrows
    public Response pingService() {
        return super.sendGetToService("/api/v3/ping", new HashMap<>(), new HashMap<>());
    }

    @SneakyThrows
    public List<CoinGeckoInfo> getListeCoin(){
        Response response = super.sendGetToService("/api/v3/coins/list", new HashMap<>(), new HashMap<>());
        String jsonString = response.body().string();
        JSONArray json = new JSONArray(jsonString);
        List<CoinGeckoInfo> coinGeckoInfos = new ArrayList<>();
        for (int i=0; i<json.length();i++){
            JSONObject e = json.getJSONObject(i);
            CoinGeckoInfo coinGeckoInfo = new CoinGeckoInfo();
            coinGeckoInfo.setId(e.getString("id"));
            coinGeckoInfo.setName(e.getString("name"));
            coinGeckoInfo.setSymbol(e.getString("symbol"));
            coinGeckoInfos.add(coinGeckoInfo);
        }
        return coinGeckoInfos;
    }

    public Boolean containsCoin(String coin){
        Boolean contain = false;
        for (CoinGeckoInfo coinGecko: this.getListeCoin()) {
            if(coinGecko.getName().equalsIgnoreCase(coin))
                contain = true;
        }
        return contain;
    }
}
