@tag
Feature: The test case verifies whether create opportunituies funciton works

  Scenario Outline: Create Opportunituies Feature
    Given User login successfully
    When User goes to page Opportunities
    And Click "Opportunities/New Button"
    And Input "Opportunities/New Opportunity/Opportunity Name" with text "New Client"
    And Input "Opportunities/New Opportunity/Close Date" with text "2019-10-11"
    And Select "Opportunities/New Opportunity/Stage" item "Opportunities/New Opportunity/Proposal"
    And Click "Opportunities/New Opportunity/Save Button"
    Then Opportunity should be succesfully created.
