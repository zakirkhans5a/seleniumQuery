package io.github.seleniumquery.globalfunctions.driver.builders;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.URL;

/**
 * Utilities for instantiating drivers.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
class DriverInstantiationUtils {

	static WebDriver instantiateDriverWithPath(String pathToDriverServerExecutable,
			String driverServerFileDescription, String driverServerDownloadPage,
				String alternativeMethod, String driverServerSystemPropertyPath,
					Class<? extends WebDriver> driverClass) {
		try {
			File driverServerExecutableFile = new File(pathToDriverServerExecutable);
			String driverSErverExecutableFilePath = driverServerExecutableFile.getCanonicalPath();
			if (!driverServerExecutableFile.exists() || driverServerExecutableFile.isDirectory()) {
				throw new RuntimeException("No " + driverServerFileDescription + " file was found at '" +
						driverSErverExecutableFilePath + "'. Download the latest release at " + driverServerDownloadPage
						+ " and place it there or specify a different path using " + alternativeMethod + ".");
			}
			System.setProperty(driverServerSystemPropertyPath, driverSErverExecutableFilePath);
			return driverClass.newInstance();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	static String getFullPathForFileInClassPath(String executableFileName) {
		String slashExecutableFileName = "/" + executableFileName;
		URL executableFileInTheClassPathUrl = DriverInstantiationUtils.class.getResource(slashExecutableFileName);
		if (executableFileInTheClassPathUrl == null) {
			return DriverInstantiationUtils.class.getResource("/").getPath() + slashExecutableFileName;
		}
		return executableFileInTheClassPathUrl.getPath();
	}

}