Feature: Google search

    Scenario: user search appiumconf in google
        Given google search page
        When user search for appiumconf
        Then user should see result page
