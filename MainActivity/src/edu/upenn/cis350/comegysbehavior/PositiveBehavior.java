package edu.upenn.cis350.comegysbehavior;

import java.util.HashMap;
import java.util.Map;

import com.parse.ParseObject;

public class PositiveBehavior {
	public boolean behaviorRespectForSelfAndOthers = false;
	public boolean behaviorFollowingDirections = false;
	public boolean behaviorPositiveConflictResolution = false;
	public boolean behaviorPeerMediation = false;
	public boolean behaviorHelpingPeerOrStaff = false;
	public boolean behaviorLeadership = false;
	public boolean behaviorDealingWithAdversityPositively = false;
	public boolean behaviorGoingAboveAndBeyond = false;
	public Map<String, Boolean> PosBehaviorMap = new HashMap<String, Boolean>();
	
	public PositiveBehavior(){
        PosBehaviorMap.put("behaviorRespectForSelfAndOthers", behaviorRespectForSelfAndOthers);
        PosBehaviorMap.put("behaviorFollowingDirections", behaviorFollowingDirections);
        PosBehaviorMap.put("behaviorPositiveConflictResolution", behaviorPositiveConflictResolution);
        PosBehaviorMap.put("behaviorPeerMediation", behaviorPeerMediation);
        PosBehaviorMap.put("behaviorHelpingPeerOrStaff", behaviorHelpingPeerOrStaff);
        PosBehaviorMap.put("behaviorLeadership", behaviorLeadership);
        PosBehaviorMap.put("behaviorDealingWithAdversityPositively", behaviorDealingWithAdversityPositively);
        PosBehaviorMap.put("behaviorGoingAboveAndBeyond", behaviorGoingAboveAndBeyond);
	}
	
	public void retrievePositiveData(ParseObject parseObject){
		this.behaviorRespectForSelfAndOthers = parseObject.getBoolean("behaviorRespectForSelfAndOthers");
        this.behaviorFollowingDirections = parseObject.getBoolean("behaviorFollowingDirections");
        this.behaviorPositiveConflictResolution = parseObject.getBoolean("behaviorPositiveConflictResolution");
        this.behaviorPeerMediation = parseObject.getBoolean("behaviorPeerMediation");
        this.behaviorHelpingPeerOrStaff = parseObject.getBoolean("behaviorHelpingPeerOrStaff");
        this.behaviorLeadership = parseObject.getBoolean("behaviorLeadership");
        this.behaviorDealingWithAdversityPositively = parseObject.getBoolean("behaviorDealingWithAdversityPositively");
        this.behaviorGoingAboveAndBeyond = parseObject.getBoolean("behaviorGoingAboveAndBeyond");
	}
	
}
