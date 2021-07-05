package com.capitoleconsulting.technicalqainterview.testengine.example.libraries;

import com.capitoleconsulting.technicalqainterview.testengine.example.controllers.CommonController;
import com.capitoleconsulting.technicalqainterview.testengine.example.model.CoinGeckoInfo;
import com.sun.source.tree.AssertTree;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;

public class CommonLibrary {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final CommonController commonController;

    private final CoinGeckoInfo coinGeckoInfo;

    public CommonLibrary(){
        this.commonController = CommonController.getINSTANCE();
        this.coinGeckoInfo = new CoinGeckoInfo();
    }

    @Before
    public void beforeTests(Scenario scenario){
        LOGGER.info("Hello, here is the beginning of our coin gecko test : {}", scenario.getName());
    }

    @Given("Connexion to CoinGecko ok")
    public void connexionToCoinGeckoOk()
    {
        LOGGER.info("checking if we can access to CoinGecko");
        Assert.assertEquals(200 , this.commonController.getCoinGeckoController().pingService().code());
    }

    @When("Open {string}")
    public void open(String url) {
        this.commonController.uiController.open(url);
    }

    @When("I call the list of listed coin on CoinGecko")
    public void iCallTheListOfListedCoinOnCoinGecko() {
        this.commonController.getCoinGeckoController().getListeCoin();
    }

    @Then("Verify {string} is listed on CoinGecko")
    public void verifyIsListedOnCoinGecko(String arg0) {
        Assert.assertTrue(this.commonController.getCoinGeckoController().containsCoin(arg0));
    }
}
