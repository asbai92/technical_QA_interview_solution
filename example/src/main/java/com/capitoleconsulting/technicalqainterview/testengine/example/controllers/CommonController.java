package com.capitoleconsulting.technicalqainterview.testengine.example.controllers;

import com.capitoleconsulting.technicalqainterview.testengine.core.TestEngine;
import com.capitoleconsulting.technicalqainterview.testengine.example.libraries.CommonLibrary;
import lombok.Getter;

public class CommonController {

    /**
     * Singleton
     */
    private static final CommonController INSTANCE = new CommonController();
    @Getter private final CoinGeckoController coinGeckoController;
    @Getter public UiController uiController;

    public CommonController() {
        this.coinGeckoController = new CoinGeckoController(TestEngine.getINSTANCE().getConfigurator().getConfig().get(CoinGeckoController.class));
    }

    public static CommonController getINSTANCE() {
        return INSTANCE;
    }

}
