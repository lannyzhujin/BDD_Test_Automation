package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import com.EnhancedWebUI

class WebUIOperation {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@When('Click "(.*)"')
	def Click(String element) {
		new EnhancedWebUI().protectedClick(findTestObject(element))
	}
	
	@When('Image click "(.*)"')
	def Image_click(String element) {
		WebUI.clickImage(findTestObject(element))
	}

	@When('Input "(.*)" with text "(.*)"')
	def Input_with_text(String element, String text) {
		new EnhancedWebUI().protectedSetText(findTestObject("Object Repository/web ui/Test Objects/SFDC/"+element), text)
	}

	@When('Image input "(.*)" with text "(.*)"')
	def Image_input_with_text(String element, String text) {
		WebUI.typeOnImage(findTestObject("Object Repository/web ui/Test Objects/SFDC/"+element), text)
	}

	@When('Select "(.*)" item "(.*)"')
	def Select_item(String element, String itemText) {
	}
}