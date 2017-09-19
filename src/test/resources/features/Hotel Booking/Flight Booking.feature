@booking
Feature: Flight Booking

  As a user
  I want to be able to book flights online
  So that I can make an informed decision

  Background:
    Given I am on the 'Home' page

  Scenario Outline: Book a one way flight as an existing user
    And I select a one way flight with the following details
      | Origin   | Destination   | Departing Date  |
      | <origin> | <destination> | <departingdate> |
    And I skip all the optional extras
    When I login with the following valid details on the 'Checkout' page
      | Username   | Password   |
      | <username> | <password> |
    And I enter the Passenger details on the 'Checkout' page
      | Reason for travel | Title   | First Name  | Last Name  | Age   |
      | <reasonfortravel> | <title> | <firstname> | <lastname> | <age> |
    Then the booking details are displayed correctly on the 'Checkout' page

    Examples:
      | origin             | destination    | departingdate | username               | password | reasonfortravel | title | firstname | lastname | age |
      | London Luton (LTN) | Alicante (ALC) | 2018-03-12    | aashish.modi@gmail.com | K1rishna | Business        | Mrs   | Priyanka  | Seth     | 18+ |
