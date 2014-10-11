package edu.upenn.cis350.comegysbehavior;


import java.util.HashMap;
import java.util.Map;

import com.parse.ParseObject;

public class StrategyReport {
	public boolean strategy_plannedIgnoring = false;
	public boolean strategy_behaviorLog = false;
	public boolean strategy_reteachReviewExpectations = false;
	public boolean strategy_restorativeAction = false;
	public boolean strategy_apologyVerbalAndOrWritten = false;
	public boolean strategy_scholarPairingTimeOut = false;
	public boolean strategy_timeOut = false;
	public boolean strategy_ageAppropriateWritingActivity = false;
	public boolean strategy_behaviorProcessingForm = false;
	public boolean strategy_teacherScholarConversationOutsideClassroom = false;
	public boolean strategy_conversationWithFamily = false;
	public boolean strategy_conference = false;
	public boolean strategy_lossOfPriveleges = false;
	
	public Map<String, Boolean> StrategyMap = new HashMap<String, Boolean>();
	
	public StrategyReport(){
		StrategyMap.put("strategy_plannedIgnoring", strategy_plannedIgnoring);
        StrategyMap.put("strategy_behaviorLog", strategy_behaviorLog);
        StrategyMap.put("strategy_reteachReviewExpectations", strategy_reteachReviewExpectations);
        StrategyMap.put("strategy_restorativeAction", strategy_restorativeAction);
        StrategyMap.put("strategy_apologyVerbalAndOrWritten", strategy_apologyVerbalAndOrWritten);
        StrategyMap.put("strategy_scholarPairingTimeOut", strategy_scholarPairingTimeOut);
        StrategyMap.put("strategy_timeOut", strategy_timeOut);
        StrategyMap.put("strategy_ageAppropriateWritingActivity", strategy_ageAppropriateWritingActivity);
        StrategyMap.put("strategy_behaviorProcessingForm", strategy_behaviorProcessingForm);
        StrategyMap.put("strategy_teacherScholarConversationOutsideClassroom", strategy_teacherScholarConversationOutsideClassroom);
        StrategyMap.put("strategy_conversationWithFamily", strategy_conversationWithFamily);
        StrategyMap.put("strategy_conference", strategy_conference);
        StrategyMap.put("strategy_lossOfPriveleges", strategy_lossOfPriveleges);
	}
	
	public void retrieveStrategyData(ParseObject parseObject){
		this.strategy_plannedIgnoring = parseObject.getBoolean("strategy_plannedIgnoring");
        this.strategy_behaviorLog = parseObject.getBoolean("strategy_behaviorLog");
        this.strategy_reteachReviewExpectations = parseObject.getBoolean("strategy_reteachReviewExpectations");
        this.strategy_restorativeAction = parseObject.getBoolean("strategy_restorativeAction");
        this.strategy_apologyVerbalAndOrWritten = parseObject.getBoolean("strategy_apologyVerbalAndOrWritten");
        this.strategy_scholarPairingTimeOut = parseObject.getBoolean("strategy_scholarPairingTimeOut");
        this.strategy_timeOut = parseObject.getBoolean("strategy_timeOut");
        this.strategy_ageAppropriateWritingActivity = parseObject.getBoolean("strategy_ageAppropriateWritingActivity");
        this.strategy_behaviorProcessingForm = parseObject.getBoolean("strategy_behaviorProcessingForm");
        this.strategy_teacherScholarConversationOutsideClassroom = parseObject.getBoolean("strategy_teacherScholarConversationOutsideClassroom");
        this.strategy_conversationWithFamily = parseObject.getBoolean("strategy_conversationWithFamily");
        this.strategy_conference = parseObject.getBoolean("strategy_conference");
        this.strategy_lossOfPriveleges = parseObject.getBoolean("strategy_lossOfPriveleges");
	}
}

