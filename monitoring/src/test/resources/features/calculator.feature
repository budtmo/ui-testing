Feature: Addition operation

    Scenario: User want to sum up between 2 numbers
        Given calculator app
        When user sum up 3 and 7
        Then the result must be 10
