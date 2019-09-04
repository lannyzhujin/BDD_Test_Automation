package login
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable

import com.EnhancedWebUI
import com.Utility

class LoginSteps {
	@Given('User login successfully')
	def User_login_successfully() {
		WebUI.callTestCase(findTestCase('Test Cases/Web UI Tests/SFDC Tests/Start Web UI Testing'), [:], FailureHandling.STOP_ON_FAILURE)
		WebUI.callTestCase(findTestCase('Test Cases/Web UI Tests/SFDC Tests/Login with username and password'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@When('User goes to page "(.*)"')
	def User_goes_to_page(String module) {
		new EnhancedWebUI().clickByXpath('//span[text()="'+module+'"]/../..')
	}

}