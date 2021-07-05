package com.capitoleconsulting.technicalqainterview.testengine.example.core;

import com.capitoleconsulting.technicalqainterview.testengine.core.TestEngine;
import com.capitoleconsulting.technicalqainterview.testengine.example.libraries.CommonLibrary;
import com.capitoleconsulting.technicalqainterview.testengine.models.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Setting all the configuration for the test batch ...");
        if (System.getenv().containsKey("feature") && System.getenv().containsKey("configuration") && System.getenv().containsKey("report"))
            TestEngine.start(new Configurator(System.getenv().get("configuration"),
                    System.getenv().get("feature"),
                    System.getenv().get("report"),
                    CommonLibrary.class.getPackageName()));
        else
            LOGGER.error("Please provide the necessary information");
    }
}
