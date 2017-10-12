Feature: Google search

    Scenario: user search selenium in google
        Given google search page
        When user search for selenium
        Then user should see result page
