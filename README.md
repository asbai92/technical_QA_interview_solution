# technical_QA_interview

Capitole Consulting technical QA interview (Java/Selenium/Cucumber)

This test aims to evaluate your skills and what are you capable of implementing using provided tools.

Be aware that you must be comfortable with OOP concepts. 

Regarding Cucumber, you will be requested to implement simple ????? and make API calls using provided tools.

Regarding Selenium, you will be requested to interact with a simple page and verify the presence of a specific element.

## Understand the topic

This exercise is a maven project which has already been partially implemented and you are requested to implement the steps under `technical_QA_interview/example/features/`.

This is composed of 2 differents modules:

- The testengine, which you should not edit since everything was already implemented to make your development experience more simple
- The example module is the one where you will want to implement the features

You will have to implement:

2 steps for the "test" on the CoinGecko API
3 steps on the coinpaprika API
4 steps for the google automation

## CoinGeckoFeature.feature

Here, we will try to implement one very simple test which will verify if a specific crypto-currency is listed on CoinGecko. The first step has already been implemented so that you can understand how we use the testengine to run our tests.

Here is the CoinGecko documentation you might want to read: [Coingecko API](https://www.coingecko.com/en/api).

To implement the `CoinGeckoStep`, be aware that you should call the API using `CoinGeckoController.java`.

## CoinPaprikaFeature.feature

For this feature, you shall do the same thing as you did in the previous feature, but you will have to implement it all by yourself, and create a specific controller for this service, which also extends `DefaultService.java`.

Do not forget to add the `service_url` , `service_port` and `service_name` (which is the reference of the controller you will be calling the API with).

Here is the coinpaprika documentation you might want to read: [coinparika API](https://api.coinpaprika.com/).

> Tip: do not hesitate to check the [CoinGecko feature implementation](#coingeckofeature-feature) if you feel unsure on how to implement this one.

## GoogleCheck.feature

This feature is about researching something on google and check if a wikipedia link exists on the first page.

For the Google implementation steps, try using the `UiController.java`.

Here we will check whether you are confortable using Selenium. The `UiController` already exists, and a step has already been implemented so that you can get familiar with the usage.

You must use the `PageObject` model.

Here is the documentation you might want to read: [Selenium documentation on `PageObject` model](https://www.selenium.dev/documentation/en/guidelines_and_recommendations/page_object_models/).

## Run the project

You will just want to run the main in the `example` module with specifics environments variable:

```
feature=example/features;report=example/report;configuration=example/configuration/services.xml;CUCUMBER_PUBLISH_ENABLED=true
```

Those represents:
- `feature`: where your features are stored
- `report`: where the report will be generated
- `configuration`: where the configuration (`service_url` etc ..) should be retrieved 

## Do not feel bad !

If there is something not clear for you, do not hesitate to come back to me, I will be adding some clarification.

# Good luck !

![image](https://user-images.githubusercontent.com/26595978/121817763-99ea8e00-cc83-11eb-97c9-fdc056bfce3f.png)
