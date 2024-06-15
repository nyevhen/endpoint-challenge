@test  @regression @ui
Feature: Wiki home page

  Scenario Outline: Add three pages to watchlist
    Given User opens wiki login page in "<browser>"
    When User login wikipedia
    And User navigates to "<page1>"
    And User adds page to watchlist
    And User navigates to "<page2>"
    And User adds page to watchlist
    And User navigates to "<page3>"
    And User adds page to watchlist
    And User navigates to watchlist
    Then User finds all saved articles in watchlist
    Examples:
      | browser | page1 | page2 | page3 |
      | local_chrome     | /wiki/Nikola_Tesla | /wiki/Niels_Bohr | /wiki/Special:Random |

  Scenario Outline: Remove one article from watchlist
    Given User opens wiki login page in "<browser>"
    When User login wikipedia
    And User navigates to watchlist
    And User removes "<articleTitle>" article from watchlist
    Then User finds all saved articles excluding "<articleTitle>"
    Examples: Browser
      | browser | articleTitle |
      | local_chrome     | Nikola Tesla |