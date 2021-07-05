package com.capitoleconsulting.technicalqainterview.testengine.example.model;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;

@Getter @Setter
public class CoinGeckoInfo {

    Response healthResponse;
    String id;
    String symbol;
    String name;
}
