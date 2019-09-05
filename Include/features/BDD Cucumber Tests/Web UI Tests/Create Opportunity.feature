Feature: Opportunities
  The test case verifies whether create opportunituies funciton works
  
  Background:
    Given User login successfully
   
  Scenario: Create Opportunituies Feature
    Given User goes to page "Opportunities"
    When Image click "Opportunities Page/New"
    And Input "Opportunities Page/New Opportunity/Opportunity Name" with text "New Client Apple Inc."
    And Input "Opportunities Page/New Opportunity/Account Name" with text "Apple Inc."
    And Click "Opportunities Page/New Opportunity/Account Name Item"
    And Input "Opportunities Page/New Opportunity/Close Date" with text "2019-10-11"
    And Select "Opportunities Page/New Opportunity/Stage" item "Proposal"
    And Click "Opportunities Page/New Opportunity/Save"
    Then Verify "New Client Apple Inc." text exists on page.
