# Aniview QA Project (Vimeo)

## Overview
This repository contains a complete katalon project for basic manual QA testing in Vimeo. (katalon studio can be downloaded [here](https://www.katalon.com/download/))
It contains one main test case which includes API testing as well as test script for various functions.
It interacts with Vimeo's API, using a pre-defined access token that was created on a new app on Vimeo's developer site.

## Installation
The installation is pretty easy and straight forward :
- First, download the code files here.
- Unzip the file to get a folder with all the code files.
- Go to "Katalon Studio" -> "file" -> "Open Project" and import the entire folder.
Once done, you will have the entire project open and ready to use. Hooray!

## Dependencies
* latest version of Katalon Studio
* Chrome browser up to date
* Katalon's WebDrivers up to date (you can update it via Katalon Studio -> "tools" -> "Update WebDrivers" -> "Chrome")

## Usage
* Before starting to use this project, make sure you are not logged in to any Vimeo account on your Chrome browser!<br/>

The project contains of one main script for running the tests called "TC - Script" which is found in the "Test Explorer" under "Test Cases". This is the only script you will need to run via the play button on the top bar.
you can simply run it, and it will run the API testing as well as other functionalities using WebUI mostly. Please make sure to chose testing in chrome (under the little black triangle next to the play button) if it's not the default option already.
The script comes with a set of pre-defined variables which some can be changed (for example, if your connection is slow, set the delay to a higher value).
Make sure not to change anything important (!) though, as it might break the test and you'll have to download the files again to recover the correct values.
Either way, all your changes should be made in the "TC-Script" variables only.

## Object Repository
* API testing
In the Object Repository you can see the API post call used to leave the comment on [this video](https://vimeo.com/615614447) (you can see and change the url in the variables, as well as the comment content).
This is the first stage of the scripts.

* Script Testing
The object repository contains many other items too, used for the script testing with the WebUI. There is no need to touch those. Expect your Chrome browser to pop up and start the automation testings as the script runs. Don't close it during the run!

## Test Case
There is only one single test case you need to run which includes all tests. You can play with the variables inside it but make sure not to touch the first 3 variables, which are the login page url, and the username & password. Those are necessary for the beginning of the testing and there is no reason in changing those as they are all configured with the required authorizations as well.

## Profiles
There is only one default profile, required for the global variable, which holds the number of views during runtime.

* In the end you can follow the links in the project, check the results, and see the variables values saved in the folder of your project, inside a file named "output.txt".



