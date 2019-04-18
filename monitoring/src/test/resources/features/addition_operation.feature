Feature: Addition operation

    Scenario Outline: <num1> + <num2>
        Given calculator app
        When user sum up <num1> and <num2>
        Then the result must be <total>

        Examples:
         | num1 | num2 | total |
         | 1 | 3 | 4 |
         | 2 | 5 | 7 |
         | 4 | 4 | 10 |
