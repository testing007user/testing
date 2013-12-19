/**File name : TestLinkWrapper
 * Description: Wrapper class having TestLink Related functionalities
 * Author: kapil Aggarwal 
 * Version: Draft 1.0 
 * completed by 12/20/13
 * Version History
 */

package com.yesmail.qa.framework.testlink;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

class TestLinkWrapper {

	private String suiteName;
	private String planName;
	private String projectName;
	private String buildName;
	private String platformName;
	private String testLinkUserApiKey;
	private URL testLinkServerUrl;
	private String launchedFrom;
	private String launchedAgainst;
	private TestLinkAPI api;

	private TestPlan testPlan;
	private TestProject testProject;
	private Build build;
	private TestSuite testSuite;
	private Platform platform;
	private boolean checkPerformed;

	public TestLinkWrapper(

	String suiteName, String planName, String projectName, String buildName,
			String platformName, String testLinkUserApiKey,
			String testLinkServerUrl, String launchedAgainst) {
		// this.testName = testName;
		// this.testStatus = testStatus;
		// this.testDesc = testDesc;
		this.suiteName = suiteName;
		this.planName = planName;
		this.projectName = projectName;
		this.buildName = buildName;
		this.platformName = platformName;
		this.setTestLinkServerUrl(testLinkServerUrl);
		this.launchedAgainst = launchedAgainst;
		this.launchedFrom = "";
		this.testLinkUserApiKey = testLinkUserApiKey;
		api = new TestLinkAPI(this.testLinkServerUrl, this.testLinkUserApiKey);
	}

	/*
	 * public void setTestName(String testName) { this.testName = testName; }
	 */

	/*
	 * public void setTestStatus(String testStatus) { this.testStatus =
	 * testStatus; }
	 */

	/*
	 * public void setTestDesc(String testDesc) { this.testDesc = testDesc; }
	 */

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public void setTestLinkUserApiKey(String testLinkUserApiKey) {
		this.testLinkUserApiKey = testLinkUserApiKey;
	}

	public void setTestLinkServerUrl(String testLinkServerUrl) {
		try {
			this.testLinkServerUrl = new URL(testLinkServerUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public void setLaunchedAgainst(String launchedAgainst) {
		this.launchedAgainst = launchedAgainst;
	}

	private String generatePrefix(String name) {
		String prefix = new String();

		for (int i = 0; i < name.length(); i++)
			if (Character.isUpperCase(name.charAt(i)))
				prefix += name.charAt(i);

		return prefix;
	}

	public void setProject() {

		try {
			testProject = api.getTestProjectByName(projectName);
		} catch (TestLinkAPIException e) {
			String projectPrefix = generatePrefix(projectName);
			testProject = api.createTestProject(projectName, projectPrefix,
					"Automated Testing Project For " + projectName, false,
					true, true, true, true, true);
		}
	}

	public void setTestPlan() {

		try {

			testPlan = api.getTestPlanByName(planName, projectName);
		} catch (TestLinkAPIException e) {
			testPlan = api.createTestPlan(planName, projectName,
					"Automatically generated Test Plan for testing " + planName
							+ " for project " + projectName, true, true);
		}
	}

	public void setBuild() {

		Build[] builds = api.getBuildsForTestPlan(testPlan.getId());
		for (int i = 0; (builds != null) && (build == null)
				&& (i < builds.length); i++) {
			if (builds[i].getName().equalsIgnoreCase(buildName))
				build = builds[i];
		}
		if (build == null) {
			build = api.createBuild(testPlan.getId(), buildName, "New build : "
					+ buildName + " detected from automated deployment");
		}
	}

	public void setTestSuite() {
		TestSuite testSuites[] = api
				.getFirstLevelTestSuitesForTestProject(testProject.getId());
		for (int i = 0; ((testSuites != null) && (testSuite == null) && (i < testSuites.length)); i++) {
			if (testSuites[i].getName().equalsIgnoreCase(suiteName))
				testSuite = testSuites[i];
		}
		if (testSuite == null) {
			testSuite = api.createTestSuite(testProject.getId(), suiteName,
					"Automatically generated Test Suite : " + suiteName, null,
					0, true, ActionOnDuplicate.BLOCK);
		}
	}

	public void setPlatform() {
		Platform platforms[] = null;

		try {
			platforms = api.getTestPlanPlatforms(testPlan.getId());
			for (int i = 0; ((platforms != null) && (platform == null) && (i < platforms.length)); i++) {
				if (platforms[i].getName().equalsIgnoreCase(platformName))
					platform = platforms[i];
			}
		} catch (TestLinkAPIException e) {
			// swallowing this exception as it just means the platform isn't
			// linked in yet ...
		} finally {
			if (platform == null) {
				UUID id = UUID.randomUUID();
				platform = new Platform(id.hashCode(), platformName, "");
			}
		}

	}

	/**
	 * report test execution to TestLink API
	 * 
	 * 
	 * 
	 */

	public synchronized int report(String testName, String testDesc,
			String testStatus) {
		int testCaseId = -1;

		/*
		 * try {
		 * 
		 * 
		 * // see if the project exists, create if not found TestProject
		 * testProject; try { testProject =
		 * api.getTestProjectByName(projectName); } catch (TestLinkAPIException
		 * e) { String projectPrefix = generatePrefix(projectName); testProject
		 * = api.createTestProject(projectName, projectPrefix,
		 * "Automated Testing Project For " + projectName, false, true, true,
		 * true, true, true); }
		 * 
		 * // see if the plan exists in the project, create if not found
		 * TestPlan testPlan; try { testPlan = api.getTestPlanByName(planName,
		 * projectName); } catch (TestLinkAPIException e) { testPlan =
		 * api.createTestPlan(planName, projectName,
		 * "Automatically generated Test Plan for testing " + planName +
		 * " for project " + projectName, true, true); }
		 * 
		 * // see if the build number exists within the current plan // if not
		 * create the build id under the test plan Build build = null; Build[]
		 * builds = api.getBuildsForTestPlan(testPlan.getId()); for (int i = 0;
		 * (builds != null) && (build == null) && (i < builds.length); i++) { if
		 * (builds[i].getName().equalsIgnoreCase(buildName)) build = builds[i];
		 * } if (build == null) { build = api.createBuild(testPlan.getId(),
		 * buildName, "New build : " + buildName +
		 * " detected from automated deployment"); }
		 * 
		 * // handle test suite ... TestSuite testSuite = null; TestSuite
		 * testSuites[] =
		 * api.getFirstLevelTestSuitesForTestProject(testProject.getId()); for
		 * (int i = 0; ((testSuites != null) && (testSuite == null) && (i <
		 * testSuites.length)); i++) { if
		 * (testSuites[i].getName().equalsIgnoreCase(suiteName)) testSuite =
		 * testSuites[i]; } if (testSuite == null) { testSuite =
		 * api.createTestSuite(testProject.getId(), suiteName,
		 * "Automatically generated Test Suite : " + suiteName, null, 0, true,
		 * ActionOnDuplicate.BLOCK); }
		 * 
		 * // retrieve the platform we tested on // this is a little different
		 * cause we can't create a platform ... Platform platforms[] = null;
		 * Platform platform = null; try { platforms =
		 * api.getTestPlanPlatforms(testPlan.getId()); for (int i = 0;
		 * ((platforms != null) && (platform == null) && (i <
		 * platforms.length)); i++) { if
		 * (platforms[i].getName().equalsIgnoreCase(platformName)) platform =
		 * platforms[i]; } } catch (TestLinkAPIException e) { // swallowing this
		 * exception as it just means the platform isn't linked in yet ... }
		 * finally { if (platform == null) { UUID id = UUID.randomUUID();
		 * platform = new Platform(id.hashCode(), platformName, ""); } }
		 */

		// find the test case or create it
		if (!checkPerformed) {
			setProject();
			setTestPlan();
			setBuild();
			setTestSuite();
			setPlatform();
			checkPerformed = true;
		}
		Integer externalId = null;
		try {
			testCaseId = api.getTestCaseIDByName(testName, suiteName,
					projectName, null);
		} catch (TestLinkAPIException e) {
			UUID id = UUID.randomUUID();
			TestCase testCase = api.createTestCase(testName, testSuite.getId(),
					testProject.getId(), "admin", testDesc, null, "",
					TestImportance.HIGH, ExecutionType.AUTOMATED, 1,
					id.hashCode(), true, ActionOnDuplicate.BLOCK);
			testCaseId = testCase.getId();
			externalId = Integer.getInteger(testCase.getFullExternalId());
			api.addTestCaseToTestPlan(testProject.getId(), testPlan.getId(),
					testCaseId, 1, platform.getId(), 0, 0);
		}

		// a valid TL test case was located or created so report the result
		if (testCaseId != -1) {
			ExecutionStatus estatus;
			if (testStatus.toUpperCase().startsWith("P")) {
				estatus = ExecutionStatus.PASSED;
			} else if (testStatus.toUpperCase().startsWith("B"))
				estatus = ExecutionStatus.BLOCKED;
			else
				estatus = ExecutionStatus.FAILED;
			// testStatus.toUpperCase().startsWith("P") ? ExecutionStatus.PASSED
			// : ExecutionStatus.FAILED
			System.out.println("In the TestList Wrapper and Details are "
					+ "testcase:" + testName + "BuildName:" + buildName
					+ "Date of Execution will be"
					+ java.util.Calendar.getInstance().getTime().toString());
			// api.reportTCResult(testCaseId, testCaseExternalId, testPlanId,
			// status, buildId, testStatus, notes, guess, bugId, platformId,
			// testStatus, customFields, overwrite)
			api.reportTCResult(testCaseId, externalId, testPlan.getId(),
					estatus, build.getId(), buildName, "Executed on : "
							+ launchedAgainst
							+ "\nPlatform : "
							+ platformName
							+ "\nRequested by : "
							+ launchedFrom
							+ "\nDate : "
							+ java.util.Calendar.getInstance().getTime()
									.toString(), null, null, platform.getId(),
					platform.getName(), null, null);

		}

		/*
		 * } catch (TestLinkAPIException e) { testCaseId = -1;
		 * e.printStackTrace(); //To change body of catch statement use File |
		 * Settings | File Templates. }
		 */

		return testCaseId;

	}

}
