Feature: Personal page

    Scenario: user visit my profile page
        Given my personal page
        When user press About
        Then user should see my profile page
