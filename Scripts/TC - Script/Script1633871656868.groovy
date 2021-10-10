import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.By

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariables
import net.bytebuddy.asm.Advice.Exit
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.SelectorMethod

//Post our comment, check status code, and save the comment ID
String videoId = commentedVideoURL.substring(commentedVideoURL.lastIndexOf('/') + 1).trim();
response = WS.sendRequest(findTestObject('Object Repository/Post comment', ['commentMsg' : comment, videoId : videoId]))
WS.verifyResponseStatusCode(response, statusCode)
JsonSlurper slurper = new JsonSlurper()
Map parsedJson = slurper.parseText(response.getResponseText())
String responseUri = parsedJson.get("uri")
commentId = responseUri.substring(responseUri.lastIndexOf('/') + 1).trim();

//Open Chrome and go to vimeo.com
WebUI.openBrowser(loginURL)

//Wait and make sure page loaded and elements present
WebUI.delay(delay)
WebUI.verifyElementPresent(findTestObject('Log in page to vimeo/input_Log in to Vimeo_email'), timeOut)

//Fill our usesr credentials
WebUI.setText(findTestObject('Log in page to vimeo/input_Log in to Vimeo_email'), username)
WebUI.setText(findTestObject('Log in page to vimeo/input_Log in to Vimeo_password'), password)

//Click to log in
WebUI.click(findTestObject('Log in page to vimeo/Log in button'))

//Go to commented video
WebUI.navigateToUrl(commentedVideoURL)

//Wait and make sure page loaded
WebUI.delay(delay)
WebUI.verifyElementPresent(findTestObject('Object Repository/Commented video page/span_J.I.D x VANS  SOMETHING OUT OF NOTHING'), timeOut)

//Refresh the view - sometimes some parts doesnt load otherwise
WebUI.mouseOver(findTestObject('Object Repository/Commented video page/span_J.I.D x VANS  SOMETHING OUT OF NOTHING'))

//Wait and verify the add comment section to make sure all comments loaded
WebUI.delay(delay)
WebUI.verifyElementPresent(findTestObject('Object Repository/Commented video page/textarea_Add a new comment_add_comment'), timeOut)

//Create new object using XPath by comment ID
TestObject myNewObject = new TestObject('ObjectID')
myNewObject.setSelectorMethod(SelectorMethod.BASIC)
myNewObject.setSelectorValue(SelectorMethod.XPATH,"//article[@id='comment_" + commentId + "']/div/div[2]/div/div/p")
myNewObject.setSelectorMethod(SelectorMethod.XPATH)

//Catch exception if not found / not correct
try {
	WebUI.verifyElementText(myNewObject, comment)
} catch(stepFailedException) {
	System.out.println("Error: Comment not found / corrupted")
	System.exit(0)
}

System.out.println("Comment found and ok")

//Go to another video
WebUI.navigateToUrl(anotherVideoURL)

//Wait and verify to make sure all loaded
WebUI.delay(delay)
WebUI.verifyElementPresent(findTestObject('Another video page/span_views'), timeOut)

//Hold values in our global and test case variables accordingly
numOfViews = WebUI.getText(findTestObject('Another video page/span_views'))
numOfLikes = WebUI.getText(findTestObject('Another video page/span_likes'))

//Save our results in a new file in the same directory as the project
File file = new File("output.txt")
file.write("Number of likes = " + numOfLikes + " (from test case variable numOfLikes)\n"
			+ "Number of views = " + numOfViews + " (from global variable numOfViews)")
