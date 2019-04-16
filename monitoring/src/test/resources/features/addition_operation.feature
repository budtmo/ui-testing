Feature: Addition operation

    Scenario: Sum 2 numbers with correct result
        Given calculator app
        When user sum up 3 and 7
        Then the result must be 10

    Scenario: Sum 2 numbers with wrong result
            Given calculator app
            When user sum up 3 and 6
            Then the result must be 10
