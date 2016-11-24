Feature: HotelEntry

  Scenario: Create an entry
    Given a user is logged in:
    | username | password|
    | admin    | password|
    When he enters hotel details:
    | HotelName | Address    | Owner  | PhoneNumber  | email            |
    | Hiton     | London,SE1 | Hilton | 020797007080 | hilton@london.com|
    And clicks create
    Then hotel  details should be saved and displayed

  Scenario: Create an multiple entry
    Given a user is logged in:
      | username | password|
      | admin    | password|
    When he enters hotel details and clicks create:
      | HotelName | Address    | Owner  | PhoneNumber  | email            |
      | Hiton123    | London,SE1 | Hilton | 020797007080 | hilton@london.com|
      | Hiton234     | London,SE1 | Hilton | 020797007080 | hilton@london.com|
    Then multiple hotel  details should be saved and displayed

  Scenario: Delete a entry
    Given a user is logged in:
      | username | password|
      | admin    | password|
    When clicks delete on hotel with below details
      | HotelName | Address    | Owner  | PhoneNumber  | email            |
      | Hiton123    | London,SE1 | Hilton | 020797007080 | hilton@london.com|
    Then hotel should get deleted and removed from the list