package com.capitoleconsulting.technicalqainterview.testengine.models;

import com.capitoleconsulting.technicalqainterview.testengine.utils.XMLParser;

import java.util.HashMap;
import java.util.Map;

public class Configurator {

    private final HashMap<Class<?>, ServiceInformation> configList;
    private final XMLParser xmlParser;
    private final String featurePath;
    private final String reportPath;
    private final String gluedClassPath;

    public Configurator(String pathConfig, String featurePath, String reportPath, String gluedClassPath) {
        this.gluedClassPath = gluedClassPath;
        this.configList = new HashMap<>();
        this.xmlParser = new XMLParser();
        for (Map.Entry<Class<?>, ServiceInformation> service :  this.xmlParser.getConfigList(pathConfig).entrySet() ){
            configList.put(service.getKey(), service.getValue());
        }
        this.featurePath = featurePath;
        this.reportPath = reportPath;
    }

    public HashMap<Class<?>, ServiceInformation> getConfig() {
        return configList;
    }

    public String getFeaturePath() {
        return featurePath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public String getGluedClassPath() {
        return gluedClassPath;
    }

}
