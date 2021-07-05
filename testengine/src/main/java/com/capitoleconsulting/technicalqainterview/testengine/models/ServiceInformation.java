package com.capitoleconsulting.technicalqainterview.testengine.models;

import io.vavr.control.Option;
import lombok.Data;

@Data
public class ServiceInformation {
    private String serviceUrl;
    private Option<String> servicePort;
}
