Feature: Booking

  Scenario: Choose destination departure and arrival
    Given user is on homepage
    When user choose destination departure
    And user choose destination arrival
    Then a dropdown list appears

  Scenario: Select tickets
    Given the user has already chosen a departure and arrival destination
    When user click on passengers dropdown list
    And click on the plus button's adults
    And click on the plus button's children
    And click on the Select dates button
    Then go to select dates page

