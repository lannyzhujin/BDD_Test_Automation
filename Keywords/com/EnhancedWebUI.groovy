package com


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration
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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.ElementNotVisibleException
import org.openqa.selenium.ElementNotInteractableException
import org.openqa.selenium.ElementNotSelectableException

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.exception.StepFailedException

import com.Utility

public class EnhancedWebUI {
	def static highlightObject(TestObject to){
		WebElement element = WebUiCommonHelper.findWebElement(to, 30)
		3.times{
			WebUI.executeJavaScript("arguments[0].setAttribute('style', 'border: 3px solid orange;');", Arrays.asList(element))
			sleep(100)
			WebUI.executeJavaScript("arguments[0].setAttribute('style','border: solid 3px white');", Arrays.asList(element))
			sleep(100)
		}
	}

	def static catchExceptionAndRetry(Closure operations) {
		int count = 0;
		int maxTries = 5;
		while(true) {
			try {
				// Example: operations = { WebUI.click(to) }
				return operations.call()
				// break while loop if operation executed without any exception
				break
			} catch (Exception e) {
				// handle exception including ElementNotVisibleException and StaleElementReferenceException
				WebUI.delay(1)
				println "=================================="
				println "          Caught Exception:       "
				println "=================================="
				println e
				println "=================================="
				println "          Retrying ......         "
				println "=================================="
				// Mark failed and stop execution if maxTries
				if (++count == maxTries) KeywordUtil.markFailedAndStop("retry ${count} times Failed")
			} finally {
				// Log retried times
				if (count>0) {
					println "=================================="
					println "   Click retried ${count} times"
					println "=================================="
				}
			}
		}
	}
	def static protectedClick(TestObject to, int timeout=30) {
		catchExceptionAndRetry({
			WebUI.waitForElementPresent(to, timeout)
			WebUI.waitForElementVisible(to, timeout)
			WebUI.waitForElementClickable(to, timeout)
			highlightObject(to)
			WebUI.click(to)
		})
	}
	def static protectedGetText(TestObject to, int timeout=30) {
		String webText = catchExceptionAndRetry({
			//Wait for link exist
			WebUI.waitForElementPresent(to, timeout)
			highlightObject(to)
			String resultText = WebUI.getText(to)
			return resultText
		})
		return webText
	}
	def static protectedSetText(TestObject to, String text, int timeout=30) {
		catchExceptionAndRetry({
			WebUI.waitForElementPresent(to, timeout)
			WebUI.waitForElementVisible(to, timeout)
			highlightObject(to)
			WebUI.setText(to, text)
		})
	}
	/**
	 * Click Element With Dynamic Xpath
	 */
	@Keyword
	def static clickByXpath(String strXpath, int timeout=30) {
		protectedClick(Utility.xpathToTestObject(strXpath))
	}
	/**
	 * Set Text Element With Dynamic Xpath
	 */
	@Keyword
	def static setTextByXpath(String strXpath, String strText) {
		protectedSetText(Utility.xpathToTestObject(strXpath), strText)
	}
	/**
	 * Scroll To Element With Dynamic Xpath
	 */
	@Keyword
	def static scrollToElementByXpath(String strXpath) {
		catchExceptionAndRetry({
			WebUI.waitForElementPresent(Util.xpathToTestObject(strXpath), 30)
			WebUI.waitForElementVisible(Util.xpathToTestObject(strXpath), 30)
			//scroll to element
			WebUI.scrollToElement(Util.xpathToTestObject(strXpath), 60)
		})
	}
	/**
	 * Scroll To Element With Dynamic Xpath
	 */
	@Keyword
	def static scrollToElement(TestObject to) {
		catchExceptionAndRetry({
			WebUI.waitForElementPresent(to, 30)
			WebUI.waitForElementVisible(to, 30)
			//scroll to element
			WebUI.scrollToElement(to, 60)
			// break out of loop, or return, on success
		})
	}
	/**
	 * Get Text with Dynamic Xpath
	 */
	@Keyword
	def static getElementTextByXpath(String strXpath) {
		return protectedGetText(Utility.xpathToTestObject(strXpath))
	}
	/**
	 * Wait Exists and Click Element
	 */
	@Keyword
	def static waitExistsAndClick(TestObject to, int timeout=30) {
		//Wait for TestObject exist
		WebUI.verifyElementPresent(to, 30)
		//Click link
		protectedClick(to)
	}
	/**
	 * Wait Exists and Set Text Element
	 */
	@Keyword
	def static waitExistsAndSetText(TestObject to, String strText, int timeout=30) {
		protectedSetText(to, strText, timeout)
	}
	/**
	 * Wait page exists with page title and verify if page contain text
	 */
	@Keyword
	def static verifyPageOpened(String strPageTitle,String strText) {
		//Wait for TestObject exist
		TestObject toPageTitle = new TestObject("objectName")
		toPageTitle.addProperty("xpath", ConditionType.EQUALS, "//*[contains(text(),'" + strPageTitle + "')]")
		//Wait for TestObject exist
		WebUI.waitForElementPresent(toPageTitle, 30)
		WebUI.waitForElementVisible(toPageTitle, 30)
		WebUI.verifyTextPresent(strText, false)
	}
	/**
	 * Wait page exists with page title and verify if page contain text
	 */
	@Keyword
	def static verifyTextExists(String strText) {
		//Wait for TestObject exist
		TestObject to = new TestObject("objectName")
		to.addProperty("xpath", ConditionType.EQUALS, "//*[contains(text(),'" + strText + "')]")
		//Wait for TestObject exist
		WebUI.waitForElementPresent(to, 30)
		WebUI.waitForElementVisible(to, 30)		
		highlightObject(to)
		WebUI.verifyElementPresent(to, 30)
	}
	/**
	 * Download Files.
	 */
	@Keyword
	def static downloadFiles = { sourceUrls->
		def stagingDir = RunConfiguration.getProjectDir() + "/Data Files/"
		new File(stagingDir).mkdirs()
		sourceUrls.each { sourceUrl ->
			def filename = sourceUrl.tokenize('/')[-1]
			def file = new FileOutputStream("$stagingDir/$filename")
			def out = new BufferedOutputStream(file)
			out << new URL(sourceUrl).openStream()
			out.close()
		}
	}
	/**
	 * Focus Element With Xpath
	 */
	@Keyword
	def static focusElementByXpath(String strXpath, int timeout=30) {
		TestObject to = new TestObject("objectName")
		to.addProperty("xpath", ConditionType.EQUALS, strXpath)
		//Wait for link exist
		WebUI.verifyElementPresent(to, 30)
		WebUI.focus(to)
	}
}
