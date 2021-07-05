Feature: We want to test if our favorite coin is listed on CoinGecko

  Scenario: Check if Monero coin is listed
    Given Connexion to CoinGecko ok
    When I call the list of listed coin on CoinGecko
    Then Verify "Monero" is listed on CoinGecko


