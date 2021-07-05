Feature: We want to test if our favorite coin is listed on CoinPaprika

  Scenario: Check if Monero coin is listed
    # Check this by verifying /global returns a 200 status
    Given Connexion to CoinPaprika is ok
    When I call the list of listen coin on Coin Paprika
    Then Verify "Monero" is listed