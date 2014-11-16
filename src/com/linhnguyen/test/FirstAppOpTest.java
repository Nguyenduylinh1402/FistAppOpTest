package com.linhnguyen.test;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class FirstAppOpTest extends UiAutomatorTestCase {
	// ------Global variable-------//
	// All App Tray
	UiObject appTray = new UiObject(new UiSelector().description("Apps"));

	// Get AppTray container
	UiScrollable appView = new UiScrollable(new UiSelector().className(
			"android.view.View").scrollable(true));

	// Apps Tab
	UiObject appsTab = new UiObject(new UiSelector().className(
			"android.widget.TextView").description("Apps"));

	// Verify the launched application by it's package name
	UiObject appFileString = new UiObject(new UiSelector().packageName(
			"com.filestring.lattedouble").text("FileString"));

	// Menu String file
	UiObject menuStringFile = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/menu_files_list_action_add")
			.description("Add"));

	// String a file
	UiObject stringFile = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/string_file").text("String a File"));

	// File Manager
	UiObject fileManager = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/source_title").text("File Manager"));

	// get Folder container//scroll add more
	UiScrollable appFolder = new UiScrollable(
			new UiSelector().className("android.widget.ListView").scrollable(true));

	// Send String File
	UiObject sendStringFile = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/menu_recipients_send").description(
			"Send"));

	// UiScrollable String file // Special thing when change to another screen//
	
	UiScrollable uiScrollableStringFile = new UiScrollable(new UiSelector()
			.resourceId("android:id/action_bar_overlay_layout")
			.className("android.view.View")
			.packageName("com.filestring.lattedouble").scrollable(false));

	// -------------Some variable-------------//
	UiObject file = null;
	UiObject data = null;
	UiObject emailEditText = null;

	// Test Start
	// @Before
	public void verifyFileStringApp() {
		assertTrue("FileString app not Launched", appFileString.exists());
	}

	// Launch FileString Application
	public void testLaunchFileString() throws RemoteException,
			UiObjectNotFoundException {
		// Get the device properties
		UiDevice myDevice = getUiDevice();

		// Wakeup the device if the screen is Off
		if (!myDevice.isScreenOn()) {
			myDevice.wakeUp();
		}

		// Press Home button
		myDevice.pressHome();
		// Set the swiping mode to horizontal
		appView.setAsHorizontalList();

		// Look for the FileString application
		UiObject applicationFileString = appView.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				"FileString", true);

		assertTrue("FileString App not launched", appFileString.exists());
		applicationFileString.clickAndWaitForNewWindow();
		for (int i = 0; i < 1; i++) {
			// Menu String File Click
			assertTrue("Click Menu Failed", menuStringFile.exists());
			menuStringFile.clickAndWaitForNewWindow();

			// String a File
			assertTrue("Click String a File Failed", stringFile.exists());
			stringFile.clickAndWaitForNewWindow();

			// File Manager
			assertTrue("Click File Manager Failed", fileManager.exists());
			fileManager.clickAndWaitForNewWindow();

			// Looking for data
			appFolder.setAsVerticalList();
			data = appFolder
					.getChildByText(
							new UiSelector().resourceId(
									"com.rhmsoft.fm:id/name").className(
									"android.widget.TextView"), "data", true);
			assertTrue("Click data Failed", data.exists());
			data.clickAndWaitForNewWindow();

			// //Looking for file
			appFolder.setAsVerticalList();

			if (i == 0) {
				file = appFolder.getChildByText(
						new UiSelector().resourceId("com.rhmsoft.fm:id/name")
								.className("android.widget.TextView"),
						"Chuyen Mua - Trung Quan Idol.mp3", true);

			} else if (i == 1) {
				file = appFolder.getChildByText(
						new UiSelector().resourceId("com.rhmsoft.fm:id/name")
								.className("android.widget.TextView"),
						"Can do list_softskills.docx", true);
			}
			assertTrue("Click data Failed", file.exists());
			file.clickAndWaitForNewWindow();
			// Fill email
			emailEditText = uiScrollableStringFile
					.getChildByText(
							new UiSelector()
									.className("android.widget.EditText")
									.resourceId(
											"com.filestring.lattedouble:id/auto_text_add_recipient"),
							"Type Names or Email Addresses");

			if (emailEditText.exists()) {
				emailEditText.click();
				emailEditText.setText("sta001@yopmail.com");
			}

			// Send String File
			assertTrue("Click send String File Failed", sendStringFile.exists());
			sendStringFile.clickAndWaitForNewWindow();
			
			//-------Notification Test--------------//
			
			// Performs a swipe from one coordinate to another using the number
			// of steps to determine smoothness and speed. Each step execution
			// is throttled to 5ms per step. So for a 100 steps, the swipe will
			// take about 1/2 second to complete.
			UiDevice deviceInstance = UiDevice.getInstance();
			deviceInstance.openNotification();// or

			// int dHeight = deviceInstance.getDisplayHeight();
			// int dWidth = deviceInstance.getDisplayWidth();
			// System.out.println("height =" + dHeight);
			// System.out.println("width =" + dWidth);
			// int xScrollPosition = dWidth / 2;
			// int yScrollStop = dHeight / 2;
			// UiDevice.getInstance().swipe(xScrollPosition, 0, xScrollPosition,
			// yScrollStop, 100);
			UiSelector selector = null;
			UiObject noti = new UiObject(selector);
			if (selector.equals("Upload file failed")) {

			}

		}
	}

}
