package com.avnet.ams.controller;

/**
 * @author Sivanandham
 */

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.avnet.ams.constants.AMSCommonConstants;
import com.avnet.ams.constants.LoggerConstants;
import com.avnet.ams.delegate.AMSAdminDelegate;
import com.avnet.ams.util.AMSJsonUtil;
import com.avnet.ams.util.AMSREPUtil;
import com.avnet.ams.util.AMSWebServiceReflectionUtil;
import com.avnet.ams.vto.AddAssetVTO;
import com.avnet.ams.vto.EditAssetVTO;
import com.avnet.ams.vto.ManageAssetVTO;
import com.avnet.ams.vto.ResolveVTO;
import com.avnet.assetportal.webservice.assetservice.AssetManagerServicePortProxy;
import com.avnet.assetportal.webservice.reportmanager.AssetReport;
import com.avnet.assetportal.webservice.reportmanager.ReportManagerPortProxy;
import com.avnet.assetportal.webservice.reportmanager.ReportResults;
import com.avnet.assetportal.webservice.request.RequestManagerPortProxy;
import com.avnet.assetportal.webservice.usermanager.EmployeeInfo;
import com.avnet.assetportal.webservice.usermanager.UserManagerPortProxy;
import com.ibm.json.java.JSONObject;

@Controller
@RequestMapping("/admin")
@SessionAttributes("userDetails")
public class AdminController {
	@Autowired
	AMSAdminDelegate adminDelegate;
	// WebService Objects
	private AssetManagerServicePortProxy webServiceAccess = null;
	private UserManagerPortProxy webServiceAccessApprover = null;
	private RequestManagerPortProxy webServiceRequestAccess = null;
	private ReportManagerPortProxy webServiceAccessReport = null;
	public static String url = AMSREPUtil
			.getProperty(AMSCommonConstants.WSDLURL);

	// Page Constants
	static final String LOGIN_PAGE = "jsp/admin/login";

	/**
	 * use this logger object for logging
	 */
	private static final String CLASS_NAME = AdminController.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	public ReportManagerPortProxy initWebServiceAccessReport() {
		if (webServiceAccessReport != null)
			return webServiceAccessReport;
		webServiceAccessReport = (ReportManagerPortProxy) AMSWebServiceReflectionUtil
				.getProxyObject(AMSCommonConstants.REPORTMANAGERSERVICE);
		return webServiceAccessReport;
	}

	public UserManagerPortProxy initwebServiceAccessApprover() {
		if (webServiceAccessApprover != null)
			return webServiceAccessApprover;

		webServiceAccessApprover = (UserManagerPortProxy) AMSWebServiceReflectionUtil
				.getProxyObject(AMSCommonConstants.USERMANAGERSERVICE);
		return webServiceAccessApprover;

	}

	public RequestManagerPortProxy initwebServiceRequestAccess() {
		if (webServiceRequestAccess != null)
			return webServiceRequestAccess;
		webServiceRequestAccess = (RequestManagerPortProxy) AMSWebServiceReflectionUtil
				.getProxyObject(AMSCommonConstants.USERREQUESTMANAGERSERVICE);
		return webServiceRequestAccess;
	}

	public AssetManagerServicePortProxy initWebServiceAccess() {
		if (webServiceAccess != null)
			return webServiceAccess;
		webServiceAccess = (AssetManagerServicePortProxy) AMSWebServiceReflectionUtil
				.getProxyObject(AMSCommonConstants.ASSETMANAGERSERVICE);
		return webServiceAccess;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "jsp/admin/login";
	}

	@RequestMapping("/home")
	public String showHome(ModelMap model) {
		EmployeeInfo response = (EmployeeInfo) model.get("userDetails");
	//	 if(response!=null)
		return "jsp/admin/home";
	/*	 else
		 return "redirect:/home.do";*/
	}

	@RequestMapping("/reports")
	public String showReports(ModelMap model) {
		EmployeeInfo response = (EmployeeInfo) model.get("userDetails");
	//	 if(response!=null)
		return "jsp/admin/reports";
	/*	 else
		 return "redirect:/home.do";*/

	}

	@RequestMapping("/assets")
	public String showAssets(ModelMap model) {
		EmployeeInfo response = (EmployeeInfo) model.get("userDetails");
	//	 if(response!=null)
		return "jsp/admin/assets";
	/*	 else
		 return "redirect:/home.do";*/

	}

	@RequestMapping("/users")
	public String showUsers(ModelMap model) {
		EmployeeInfo response = (EmployeeInfo) model.get("userDetails");
	//	 if(response!=null)
		return "jsp/admin/users";
		/* else
		 return "redirect:/home.do";*/

	}

	/**
	 * @RequestMapping(value = "/getEmpDetailsAutoComplete", headers =
	 *                       "Accept=application/json") public @ResponseBody
	 *                       JSONObject getRequests() { return
	 *                       adminDelegate.getEmpDetailsAutoCompleteJson(); }
	 **/

	@RequestMapping(value = "/requests", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getRequests(
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "length", required = false) String length,
			@RequestParam(value = "order[0][column]", required = false) String column,
			@RequestParam(value = "order[0][dir]", required = false) String dir

	) {
		webServiceRequestAccess = initwebServiceRequestAccess();
		return adminDelegate.getViewRequestsJson(webServiceRequestAccess, from,
				to, start, length, column, dir);

	}

	@RequestMapping(value = "/getTypeJson", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getTypesInJSON() {
		String METHOD_NAME = "getTypesInJSON";
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1004D"),
				METHOD_NAME));
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.getAssetTypeListJson(webServiceAccess);

	}

	@RequestMapping(value = "/getTypeWithAttributesJson", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getTypesWithAttributeInJSON() {
		String METHOD_NAME = "getTypesWithAttributeInJSON";
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1004D"),
				METHOD_NAME));
		webServiceAccess = initWebServiceAccess();
		return adminDelegate
				.getAssetTypeListWithAttributesJson(webServiceAccess);

	}

	@RequestMapping(value = "/getLifeTime", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getLifeTime(
			@RequestParam(value = "assetTypeId", required = true) String assetTypeId) {
		String METHOD_NAME = "getLifeTime";
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1004D"),
				METHOD_NAME));
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.getLifeTimeForAssetType(webServiceAccess,
				assetTypeId);

	}

	@RequestMapping(value = "/getReportTypeJson", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getReportTypesInJSON() {
		webServiceAccessReport = initWebServiceAccessReport();
		return adminDelegate.getReportTypeJson(webServiceAccessReport);

	}

	@RequestMapping(value = "/getOwnedByUsers", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getOwnedByUsers() {
		webServiceAccessApprover = initwebServiceAccessApprover();
		return adminDelegate.getOwnedByUser(webServiceAccessApprover);

	}

	@RequestMapping(value = "/getAttributesJson", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getAttributesJSON() {
		String METHOD_NAME = "getAttributesJSON";
		logger.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1004D"),
				METHOD_NAME));
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.getAttributesListJson(webServiceAccess);
	}

	@RequestMapping(value = "/addAsset", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject addAssetAdminVTO(
			@ModelAttribute("addAsset") AddAssetVTO addAssetVTO, ModelMap model) {
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.setAddAsset(addAssetVTO, webServiceAccess);
	}

	@RequestMapping(value = "/getReportDetails", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject addAssetAdminVTO(@RequestParam("reportType") String reportType,
			ModelMap model) {
		webServiceAccessReport = initWebServiceAccessReport();
		return adminDelegate.setReport(reportType, webServiceAccessReport);
	}

	@RequestMapping(value = "/getFinanceReportDetails", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getFinanceReport(
			@RequestParam("financeAssetTypeId") String financeAssetTypeId,
			@RequestParam("depreciationType") String depreciationtype,
			@RequestParam("percentage") String percentage, ModelMap model) {
		long startTime = System.currentTimeMillis();
		JSONObject financeReport = adminDelegate.getFinanceReport(
				financeAssetTypeId, depreciationtype, percentage,
				webServiceAccessReport);
		long endTime = System.currentTimeMillis();
		logger.log(Level.INFO,
				"Time taken is for generating finance report in milli seconds : "
						+ (endTime - startTime));

		return financeReport;
	}

	/**
	 * 
	 * 
	 * @param manageAssetVTO
	 * @param model
	 * @return JSON
	 */
	@RequestMapping(value = "/manageAsset", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject ManageAssetVTO(
			@ModelAttribute("manageAsset") ManageAssetVTO manageAssetVTO,
			ModelMap model) {

		webServiceAccess = initWebServiceAccess();
		return adminDelegate.manageAsset(manageAssetVTO, webServiceAccess);
	}

	@RequestMapping(value = "/editAsset", method = RequestMethod.GET)
	public String editAsset(@RequestParam("assetId") String assetId,
			ModelMap model) {
		webServiceAccess = initWebServiceAccess();
		EditAssetVTO editAssetVTOObject = adminDelegate
				.getEditAssetValuesFromService(webServiceAccess, assetId);
		model.put("editAssetVTOObject", editAssetVTOObject);
		if (editAssetVTOObject == null) {
			return "jsp/admin/assets";
		}
		return "jsp/admin/edit";
	}

	@RequestMapping(value = "/resolvedAction", method = RequestMethod.POST)
	public @ResponseBody
	String addAssetAdminVTO(
			@ModelAttribute("resolveObject") ResolveVTO resolveObject,
			ModelMap model) {
		webServiceRequestAccess = initwebServiceRequestAccess();
		return adminDelegate.sendResolveActionService(resolveObject,
				webServiceRequestAccess);
	}

	@RequestMapping(value = "/editAssetUpdate", method = RequestMethod.POST)
	public @ResponseBody
	String editAssetUpdate(
			@ModelAttribute("assetObject") EditAssetVTO editAssetVTOObject,
			ModelMap model) {
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.callServiceToEditAsset(webServiceAccess,
				editAssetVTOObject);
	}

	@RequestMapping(value = "/availableAssets", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject availableAssets(
			@RequestParam("searchString") String searchString,
			@RequestParam("searchIdType") String searchIdType,
			@RequestParam("categoryId") String categoryId) {
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.getAvailableAssetValuesFromService(
				webServiceAccess, searchString, searchIdType, categoryId);
	}

	@RequestMapping(value = "/getAssetsEmployee", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getAssetsEmployeeControllerMethod(
			@RequestParam("searchType") String searchType,
			@RequestParam("searchString") String searchString,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "length", required = false) String length,
			@RequestParam(value = "order[0][column]", required = false) String column,
			@RequestParam(value = "order[0][dir]", required = false) String dir) {
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.getAssetsOfEmployee(webServiceAccess, searchType,
				searchString, start, length, column, dir);
	}

	@RequestMapping(value = "/getEmployee", headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getEmployeeDetails(
			@RequestParam(value = "search", required = false) String search) {
		webServiceAccessApprover = initwebServiceAccessApprover();
		return adminDelegate.getEmployeeDetails(webServiceAccessApprover,
				search);
	}

	@RequestMapping(value = "/userAssetRemove", method = RequestMethod.GET)
	public @ResponseBody
	String userAssetRemove(
			@RequestParam(value = "assetId", required = false) String assetId,
			@RequestParam(value = "empId", required = false) String empId) {
		webServiceAccess = initWebServiceAccess();
		return adminDelegate.callServiceToUpdateUser(webServiceAccess, assetId,
				empId);
	}

	@RequestMapping(value = "/getNotification", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getNotification() {
		webServiceRequestAccess = initwebServiceRequestAccess();
		return adminDelegate.getNotificationTicker(webServiceRequestAccess);
	}

	/**
	 * Handle request to download the report
	 */
	@RequestMapping(value = "/downloadReport", method = RequestMethod.GET)
	public ModelAndView exportReport(
			@RequestParam("reportType") String reportType,
			HttpServletRequest request, HttpServletResponse response) {
		webServiceAccessReport = initWebServiceAccessReport();
		ReportResults reportResults = adminDelegate.exportReport(
				webServiceAccessReport, reportType);
		return new ModelAndView("reportsView", "reportResults", reportResults);
	}

	/**
	 * controller method to download assets history
	 * 
	 * @param searchType
	 * @param searchString
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/downloadAssetHistoryReport", method = RequestMethod.GET)
	public String exportAssetHistoryReport(
			@RequestParam("selectedSearchType") String searchType,
			@RequestParam("selectedSearchString") String searchString,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		final String METHOD_NAME = "exportAssetHistoryReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessReport = initWebServiceAccessReport();
		webServiceAccessApprover = initwebServiceAccessApprover();
		AssetReport assetReport = adminDelegate.exportAssetHistoryReport(
				webServiceAccessReport, webServiceAccessApprover, searchType,
				searchString);
		model.put("selectedSearchType", searchType);
		model.put("selectedSearchString", searchString);
		model.put("assetReport", assetReport);
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return "assetHistoryReportView";

	}

	@RequestMapping(value = "/help")
	public String doRenderHelp(ModelMap model) {

		return "jsp/admin/help";

	}

	/**
	 * controller for auto complete asset type in assets page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAssetsTypesList", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getAssetTypesList() {
		final String METHOD_NAME = "getAssetTypesList";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccess = initWebServiceAccess();
		JSONObject jsonObj = new JSONObject();
		jsonObj = adminDelegate.getAssetTypesList(webServiceAccess);
		if (jsonObj != null) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonObj;
		} else {
			JSONObject errorjsonObject = AMSJsonUtil.assetTypesError();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return errorjsonObject;
		}
	}

	/**
	 * controller for auto complete employee name/id in assets history page
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/assetsHistorySearch", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getUsersList() {
		final String METHOD_NAME = "getUsersList";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessApprover = initwebServiceAccessApprover();
		JSONObject jsonObj = new JSONObject();
		jsonObj = adminDelegate.getUsersList(webServiceAccessApprover);
		if (jsonObj != null) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonObj;
		} else {
			JSONObject errorjsonObject = AMSJsonUtil.usersListError();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return errorjsonObject;
		}
	}

	/**
	 * controller to get values for assets page
	 * 
	 * @param searchType
	 * @param searchString
	 * @param start
	 * @param length
	 * @param column
	 * @param dir
	 * @return
	 */
	@RequestMapping(value = "/getAssets", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getAssets(
			@RequestParam("searchType") String searchType,
			@RequestParam("searchString") String searchString,
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "length", required = true) String length,
			@RequestParam(value = "order[0][column]", required = true) String column,
			@RequestParam(value = "order[0][dir]", required = true) String dir) {
		final String METHOD_NAME = "getAssetTypesList";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccess = initWebServiceAccess();
		JSONObject jsonObj = new JSONObject();
		jsonObj = adminDelegate.getAssets(webServiceAccess, searchType,
				searchString, start, length, column, dir);
		if (jsonObj != null) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonObj;
		} else {
			JSONObject errorjsonObject = AMSJsonUtil.assetErrorJson();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return errorjsonObject;
		}
	}

	/**
	 * controller to get values for assets history page
	 * 
	 * @param searchType
	 * @param searchString
	 * @param start
	 * @param length
	 * @param column
	 * @param dir
	 * @return
	 */
	@RequestMapping(value = "/getAssetsHistoryReport", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject getAssetsHistoryReport(
			@RequestParam("searchType") String searchType,
			@RequestParam("searchString") String searchString,
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "length", required = true) String length,
			@RequestParam(value = "order[0][column]", required = true) String column,
			@RequestParam(value = "order[0][dir]", required = true) String dir) {
		final String METHOD_NAME = "getAssetsHistoryReport";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccessReport = initWebServiceAccessReport();
		JSONObject jsonObj = new JSONObject();
		jsonObj = adminDelegate.getAssetsHistoryReport(webServiceAccessReport,
				searchType, searchString, start, length, column, dir);
		if (jsonObj != null) {
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return jsonObj;
		} else {
			JSONObject errorjsonObject = AMSJsonUtil.assetHistoryErrorJson();
			logger.exiting(CLASS_NAME, METHOD_NAME);
			return errorjsonObject;
		}
	}

	/**
	 * Handle request to display custom report
	 */
	@RequestMapping(value = "/customReport", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody
	JSONObject getCustomReport(@RequestParam("assetTypeId") String assetTypeId,
			@RequestParam("attributesArray[]") String[] attributesArray) {
		webServiceAccessReport = initWebServiceAccessReport();
		return adminDelegate.getCustomReport(webServiceAccessReport,
				assetTypeId, attributesArray);
	}

	/**
	 * Handle request to save custom report query
	 */
	@RequestMapping(value = "/saveCustomReportQuery", method = RequestMethod.POST)
	public @ResponseBody
	String saveCustomReportQuery(
			@RequestParam("assetTypeId") String assetTypeId,
			@RequestParam("attributesArray[]") String[] attributesArray,
			@RequestParam("queryName") String queryName) {

		return adminDelegate.saveCustomReportQuery(webServiceAccessReport,
				assetTypeId, attributesArray, queryName);
	}

	/**
	 * Handle request to download custom report
	 */
	@RequestMapping(value = "/exportCustomReport", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView exportCustomReport(
			@RequestParam("assetTypeId") String assetTypeId,
			@RequestParam("attributesArray[]") String[] attributesArray) {
		webServiceAccessReport = initWebServiceAccessReport();
		ReportResults reportResults = adminDelegate.exportCustomReport(
				webServiceAccessReport, assetTypeId, attributesArray);
		return new ModelAndView("reportsView", "reportResults", reportResults);
	}

	/**
	 * Handle request to download finance report
	 */
	@RequestMapping(value = "/exportFinanceReport", method = RequestMethod.GET)
	public ModelAndView exportFinanceReport(
			@RequestParam("financeAssetTypeId") String financeAssetTypeId,
			@RequestParam("depreciationType") String depreciationtype,
			@RequestParam("percentage") String percentage) {
		webServiceAccessReport = initWebServiceAccessReport();
		ReportResults reportResults = adminDelegate.exportFinanceReport(
				financeAssetTypeId, depreciationtype, percentage,
				webServiceAccessReport);
		return new ModelAndView("reportsView", "reportResults", reportResults);
	}

	/*
	 * returns a JSON containing a JSON Array of AssetIdentity and AssetId
	 */
	@RequestMapping(value = "/getAssetIdentities")
	public @ResponseBody
	JSONObject getAssetIdentities(
			@RequestParam(value = "searchString", required = false) String searchString) {
		final String METHOD_NAME = "getAssetIdentities";
		logger.entering(CLASS_NAME, METHOD_NAME);
		webServiceAccess = initWebServiceAccess();
		logger.exiting(CLASS_NAME, METHOD_NAME);
		return adminDelegate.getAssetIdentityList(webServiceAccess,
				searchString);
	}
}
