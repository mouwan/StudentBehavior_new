package edu.upenn.cis350.comegysbehavior;

import java.util.HashMap;
import java.util.Map;

import com.parse.ParseObject;

public class NegativeBehavior {
	public boolean behavior_refusalToFollowDirectionsOrParticipate = false;
	public boolean behavior_disruptionOfClassOrActivity = false;
	public boolean behavior_disrespectOfStaffOrScholars = false;
	public boolean behavior_inappropriateLanguageOrGestures = false;
	public boolean behavior_inappropriatePhysicalContactOrFighting = false;
	public boolean behavior_teasingOrInstigatingConflict = false;
	public boolean behavior_runningInCommonSpaces = false;
	public boolean behavior_leavingSupervisionUnattended = false;
	public boolean behavior_failingToFollowRules = false;
	public Map<String, Boolean> NegBehaviorMap = new HashMap<String, Boolean>();
	
	public NegativeBehavior(){
		NegBehaviorMap.put("behavior_refusalToFollowDirectionsOrParticipate", behavior_refusalToFollowDirectionsOrParticipate);
        NegBehaviorMap.put("behavior_disruptionOfClassOrActivity", behavior_disruptionOfClassOrActivity);
        NegBehaviorMap.put("behavior_disrespectOfStaffOrScholars", behavior_disrespectOfStaffOrScholars);
        NegBehaviorMap.put("behavior_inappropriateLanguageOrGestures", behavior_inappropriateLanguageOrGestures);
        NegBehaviorMap.put("behavior_inappropriatePhysicalContactOrFighting", behavior_inappropriatePhysicalContactOrFighting);
        NegBehaviorMap.put("behavior_teasingOrInstigatingConflict", behavior_teasingOrInstigatingConflict);
        NegBehaviorMap.put("behavior_runningInCommonSpaces", behavior_runningInCommonSpaces);
        NegBehaviorMap.put("behavior_leavingSupervisionUnattended", behavior_leavingSupervisionUnattended);
        NegBehaviorMap.put("behavior_failingToFollowRules", behavior_failingToFollowRules);
	}
	
	public void retrieveNegativeData(ParseObject parseObject){
		this.behavior_refusalToFollowDirectionsOrParticipate = parseObject.getBoolean("behavior_refusalToFollowDirectionsOrParticipate");
        this.behavior_disruptionOfClassOrActivity = parseObject.getBoolean("behavior_disruptionOfClassOrActivity");
        this.behavior_disrespectOfStaffOrScholars = parseObject.getBoolean("behavior_disrespectOfStaffOrScholars");
        this.behavior_inappropriateLanguageOrGestures = parseObject.getBoolean("behavior_inappropriateLanguageOrGestures");
        this.behavior_inappropriatePhysicalContactOrFighting = parseObject.getBoolean("behavior_inappropriatePhysicalContactOrFighting");
        this.behavior_teasingOrInstigatingConflict = parseObject.getBoolean("behavior_teasingOrInstigatingConflict");
        this.behavior_runningInCommonSpaces = parseObject.getBoolean("behavior_runningInCommonSpaces");
        this.behavior_leavingSupervisionUnattended = parseObject.getBoolean("behavior_leavingSupervisionUnattended");
        this.behavior_failingToFollowRules = parseObject.getBoolean("behavior_failingToFollowRules");
	}
}

