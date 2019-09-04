Feature: Opportunities
  The test case verifies whether create opportunituies funciton works
  
  Background:
    Given User login successfully
  
  Scenario Outline: Create Opportunituies Feature
    Given User goes to page Opportunities
    #When Image click "Opportunities Page/New"
    When Image click "Object Repository/web ui/Test Objects/SFDC/Opportunities Page/New"
    And Image input "Opportunities Page/New Opportunity/Opportunity Name" with text "New Client Apple Inc."
    And Image input "Opportunities Page/New Opportunity/Close Date" with text "2019-10-11"
    And Select "Opportunities Page/New Opportunity/Stage" item "Opportunities/New Opportunity/Proposal"
    And Click "Opportunities Page/New Opportunity/Save Button"
    Then Opportunity should be succesfully created
    
    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |