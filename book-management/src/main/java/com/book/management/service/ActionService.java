package com.book.management.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.book.management.util.IntentUtil;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;

@Service
public class ActionService extends DialogflowApp {

	private Logger logger = LoggerFactory.getLogger(ActionService.class);

	@ForIntent(IntentUtil.LIST_ACTIONS)
	public ActionResponse listActions(ActionRequest request) {
		logger.info("Executing intent - " + IntentUtil.LIST_ACTIONS);

		StringBuilder response = new StringBuilder();
		response.append("I can tell you what authors I know about or you can tell me which"
				+ " type of book you are interested in.");
		response.append("Based on your selection, we can go forward with the process.");
		response.append("So, please tell me, what would you like to know?");

		// build the response and send it back
		ResponseBuilder responseBuilder = getResponseBuilder(request).add(response.toString());
		ActionResponse actionResponse = responseBuilder.build();
		return actionResponse;
	}

	public String getIntentName(String body) throws Exception {
		// convert body to JSONObject
		JSONObject bodyJsonObject = new JSONObject(body);
		// get queryResult
		JSONObject queryResultObject = bodyJsonObject.getJSONObject("queryResult");
		// retrieve intent object
		JSONObject intentObject = queryResultObject.getJSONObject("intent");
		// get displayName
		return intentObject.get("displayName").toString();
	}
}
