@test  @regression @ui
Feature: Wiki home page

  Scenario Outline: Add three pages to watchlist
    Given User opens wiki login page in "<browser>"
    When User login wikipedia
    And User navigates to "<page1>"
    And User adds page to watchlist
    And User navigates to "<page2>"
    And User adds page to watchlist
    And User navigates to "<page1>"
    And User removes page "<articleTitle>" from watchlist
    And User navigates to watchlist
    Then User finds all saved articles in watchlist
    Then User finds all saved articles excluding "<articleTitle>"

    Examples:
      | browser      | page1              | page2            | articleTitle |
      | local_chrome | /wiki/Nikola_Tesla | /wiki/Niels_Bohr | Nikola Tesla |