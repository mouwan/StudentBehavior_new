/**
 * 
 */
package edu.upenn.cis350.comegysbehavior;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseObject;

/**
 * @author Ronald Martin
 * Object describing a behavior report
 */
public class Report implements Parcelable {
	
	public String objectID = "";
	// Subject Info
	public String studentName       = "";
	public String studentGrade      = "";
	public String reportCreatedDate = "";
	
	// Summaries
	public String behaviorSummary = "";
	public String academicSummary = "";
	public String strategySummary = "";
	
	/* BEHAVIOR REPORT */
	
	// Behavior Report - Positive
	/*public boolean behavior_respectForSelfAndOthers = false;
	public boolean behavior_followingDirections = false;
	public boolean behavior_positiveConflictResolution = false;
	public boolean behavior_peerMediation = false;
	public boolean behavior_helpingPeerOrStaff = false;
	public boolean behavior_leadership = false;
	public boolean behavior_dealingWithAdversityPositively = false;
	public boolean behavior_goingAboveAndBeyond = false;
	  */
	public PositiveBehavior PB = new PositiveBehavior();
	// Behavior Report - Negative
	
	public NegativeBehavior NB = new NegativeBehavior();
	/*public boolean behavior_refusalToFollowDirectionsOrParticipate = false;
	public boolean behavior_disruptionOfClassOrActivity = false;
	public boolean behavior_disrespectOfStaffOrScholars = false;
	public boolean behavior_inappropriateLanguageOrGestures = false;
	public boolean behavior_inappropriatePhysicalContactOrFighting = false;
	public boolean behavior_teasingOrInstigatingConflict = false;
	public boolean behavior_runningInCommonSpaces = false;
	public boolean behavior_leavingSupervisionUnattended = false;
	public boolean behavior_failingToFollowRules = false;*/
	
	
	public boolean behavior_other = false; 
	
	public String behaviorSetting = "";
	
	/* ACADEMIC REPORT */
	
/*	public boolean academic_respectsLearningForSelfAndOthers = false;
	public boolean academic_followsDirections = false;
	public boolean academic_consistentlyPreparedAndOrganized = false;
	public boolean academic_completesHomeworkAndAssignments = false;
	public boolean academic_staysOnTask = false;
	public boolean academic_peerTutoring = false;
	public boolean academic_struggles = false;
	public boolean academic_disruptionOfClassLessonActivity = false;
	public boolean academic_refusalToFollowDirectionsAndParticipate = false;
	public boolean academic_unPreparedAndDisorganized = false;
	public boolean academic_failureToCompleteHomeworkAssignment = false;
	public boolean academic_questionableAcademicIntegrity = false;
	public boolean academic_other = false;*/
	
	public boolean academic_other = false;
	public AcademicReport AR = new AcademicReport();
	/* STRATEGY REPORT */
/*	public boolean strategy_plannedIgnoring = false;
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
	public boolean strategy_other = false;*/

	public StrategyReport SR = new StrategyReport();
	public boolean strategy_other = false;

	public String academicSetting = "";
	public String reportDetailsAndComments = "";
	public String strategyComment = "";
	public String academicComment = "";
	public String behaviorComment = "";
	
	/**
	 * 
	 */
	public Report() {
		// TODO Auto-generated constructor stub
	}
	
	 /*
     * Create a Report object from an object retrieved from Parse
     */
    public Report(ParseObject parseObject) {
        // Retrieve report subject info.
        this.studentName       = parseObject.getString("studentName");
        this.studentGrade      = parseObject.getString("studentGrade");
        this.reportCreatedDate = parseObject.getString("date");
        this.objectID = parseObject.getObjectId();
        
       
        retrieveBehaviorData(parseObject);
        
        retrieveAcademicData(parseObject);
        
        retrieveStrategyData(parseObject);
        
        this.reportDetailsAndComments = parseObject.getString("report_details");
        this.behaviorComment = parseObject.getString("behavior_comments");
        this.academicComment = parseObject.getString("academic_comments");
        this.strategyComment = parseObject.getString("strategy_comments");
        this.behaviorSummary = parseObject.getString("behavior_details");
        this.academicSummary = parseObject.getString("academic_details");
        this.strategySummary = parseObject.getString("strategy_details");
    }
    
    private void retrieveBehaviorData(ParseObject parseObject) {
        // Retrieve behavior report data (positive).
    	
       /* this.behavior_respectForSelfAndOthers = parseObject.getBoolean("behavior_respectForSelfAndOthers");
        this.behavior_followingDirections = parseObject.getBoolean("behavior_followingDirections");
        this.behavior_positiveConflictResolution = parseObject.getBoolean("behavior_positiveConflictResolution");
        this.behavior_peerMediation = parseObject.getBoolean("behavior_peerMediation");
        this.behavior_helpingPeerOrStaff = parseObject.getBoolean("behavior_helpingPeerOrStaff");
        this.behavior_leadership = parseObject.getBoolean("behavior_leadership");
        this.behavior_dealingWithAdversityPositively = parseObject.getBoolean("behavior_dealingWithAdversityPositively");
        this.behavior_goingAboveAndBeyond = parseObject.getBoolean("behavior_goingAboveAndBeyond");*/
       
    	PB.retrievePositiveData(parseObject);
        // Retrieve behavior report data (negative).          
       /* this.behavior_refusalToFollowDirectionsOrParticipate = parseObject.getBoolean("behavior_refusalToFollowDirectionsOrParticipate");
        this.behavior_disruptionOfClassOrActivity = parseObject.getBoolean("behavior_disruptionOfClassOrActivity");
        this.behavior_disrespectOfStaffOrScholars = parseObject.getBoolean("behavior_disrespectOfStaffOrScholars");
        this.behavior_inappropriateLanguageOrGestures = parseObject.getBoolean("behavior_inappropriateLanguageOrGestures");
        this.behavior_inappropriatePhysicalContactOrFighting = parseObject.getBoolean("behavior_inappropriatePhysicalContactOrFighting");
        this.behavior_teasingOrInstigatingConflict = parseObject.getBoolean("behavior_teasingOrInstigatingConflict");
        this.behavior_runningInCommonSpaces = parseObject.getBoolean("behavior_runningInCommonSpaces");
        this.behavior_leavingSupervisionUnattended = parseObject.getBoolean("behavior_leavingSupervisionUnattended");
        this.behavior_failingToFollowRules = parseObject.getBoolean("behavior_failingToFollowRules");*/
        NB.retrieveNegativeData(parseObject);
        // Retrieve behavior setting
        this.behaviorSetting = parseObject.getString("behavior_setting");
        
        this.behavior_other = parseObject.getBoolean("behavior_other");
    }
    
    private void retrieveAcademicData(ParseObject parseObject) {
        // Retrieve academic data.
    	this.academicSetting = parseObject.getString("academic_setting");
    	 this.academic_other = parseObject.getBoolean("academic_other");
     	AR.retrieveAcademicData(parseObject);
       /* this.academic_respectsLearningForSelfAndOthers = parseObject.getBoolean("academic_respectsLearningForSelfAndOthers");
        this.academic_followsDirections = parseObject.getBoolean("academic_followsDirections");
        this.academic_consistentlyPreparedAndOrganized = parseObject.getBoolean("academic_consistentlyPreparedAndOrganized");
        this.academic_completesHomeworkAndAssignments = parseObject.getBoolean("academic_completesHomeworkAndAssignments");
        this.academic_staysOnTask = parseObject.getBoolean("academic_staysOnTask");
        this.academic_peerTutoring = parseObject.getBoolean("academic_peerTutoring");
        this.academic_struggles = parseObject.getBoolean("academic_struggles");
        this.academic_disruptionOfClassLessonActivity = parseObject.getBoolean("academic_disruptionOfClassLessonActivity");
        this.academic_refusalToFollowDirectionsAndParticipate = parseObject.getBoolean("academic_refusalToFollowDirectionsAndParticipate");
        this.academic_unPreparedAndDisorganized = parseObject.getBoolean("academic_unPreparedAndDisorganized");
        this.academic_failureToCompleteHomeworkAssignment = parseObject.getBoolean("academic_failureToCompleteHomeworkAssignment");
        this.academic_questionableAcademicIntegrity = parseObject.getBoolean("academic_questionableAcademicIntegrity");
        this.academic_other = parseObject.getBoolean("academic_other");*/
    }
    
    private void retrieveStrategyData(ParseObject parseObject) {
        // Retrieve strategy data.
     /*   this.strategy_plannedIgnoring = parseObject.getBoolean("strategy_plannedIgnoring");
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
        this.strategy_lossOfPriveleges = parseObject.getBoolean("strategy_lossOfPriveleges");*/
        
        SR.retrieveStrategyData(parseObject);
    	
        this.strategy_other = parseObject.getBoolean("strategy_other");
    }
    
    /*
     * Return this Report as a Parse Object to push to the database.
     */
    public ParseObject getParseObject() {
    	
        ParseObject reportParse = new ParseObject("Report");
        // Add report subject info.
        reportParse.put("studentName", studentName);
        reportParse.put("studentGrade", studentGrade);
        reportParse.put("date", reportCreatedDate);
        
        for(String key: PB.PosBehaviorMap.keySet()){
        	reportParse.put(key, PB.PosBehaviorMap.get(key));
        }
        // Add positive behavior report data.
/*        reportParse.put("behavior_respectForSelfAndOthers", behavior_respectForSelfAndOthers);
        reportParse.put("behavior_followingDirections", behavior_followingDirections);
        reportParse.put("behavior_positiveConflictResolution", behavior_positiveConflictResolution);
        reportParse.put("behavior_peerMediation", behavior_peerMediation);
        reportParse.put("behavior_helpingPeerOrStaff", behavior_helpingPeerOrStaff);
        reportParse.put("behavior_leadership", behavior_leadership);
        reportParse.put("behavior_dealingWithAdversityPositively", behavior_dealingWithAdversityPositively);
        reportParse.put("behavior_goingAboveAndBeyond", behavior_goingAboveAndBeyond);*/
        
        // Add negative behavior report data.
       /* reportParse.put("behavior_refusalToFollowDirectionsOrParticipate", behavior_refusalToFollowDirectionsOrParticipate);
        reportParse.put("behavior_disruptionOfClassOrActivity", behavior_disruptionOfClassOrActivity);
        reportParse.put("behavior_disrespectOfStaffOrScholars", behavior_disrespectOfStaffOrScholars);
        reportParse.put("behavior_inappropriateLanguageOrGestures", behavior_inappropriateLanguageOrGestures);
        reportParse.put("behavior_inappropriatePhysicalContactOrFighting", behavior_inappropriatePhysicalContactOrFighting);
        reportParse.put("behavior_teasingOrInstigatingConflict", behavior_teasingOrInstigatingConflict);
        reportParse.put("behavior_runningInCommonSpaces", behavior_runningInCommonSpaces);
        reportParse.put("behavior_leavingSupervisionUnattended", behavior_leavingSupervisionUnattended);
        reportParse.put("behavior_failingToFollowRules", behavior_failingToFollowRules)*/;
        
        for(String key: NB.NegBehaviorMap.keySet()){
        	reportParse.put(key, NB.NegBehaviorMap.get(key));
        }
        // Add other behavior report data.
        reportParse.put("behavior_other", behavior_other);
        reportParse.put("behavior_setting", behaviorSetting);
        
        // Add academic data.
        for(String key: AR.AcademicMap.keySet()){
        	reportParse.put(key, AR.AcademicMap.get(key));
        }
       /* reportParse.put("academic_respectsLearningForSelfAndOthers", academic_respectsLearningForSelfAndOthers);
        reportParse.put("academic_followsDirections", academic_followsDirections);
        reportParse.put("academic_consistentlyPreparedAndOrganized", academic_consistentlyPreparedAndOrganized);
        reportParse.put("academic_completesHomeworkAndAssignments", academic_completesHomeworkAndAssignments);
        reportParse.put("academic_staysOnTask", academic_staysOnTask);
        reportParse.put("academic_peerTutoring", academic_peerTutoring);
        reportParse.put("academic_struggles", academic_struggles);
        reportParse.put("academic_disruptionOfClassLessonActivity", academic_disruptionOfClassLessonActivity);
        reportParse.put("academic_refusalToFollowDirectionsAndParticipate", academic_refusalToFollowDirectionsAndParticipate);
        reportParse.put("academic_unPreparedAndDisorganized", academic_unPreparedAndDisorganized);
        reportParse.put("academic_failureToCompleteHomeworkAssignment", academic_failureToCompleteHomeworkAssignment);
        reportParse.put("academic_questionableAcademicIntegrity", academic_questionableAcademicIntegrity);*/
        
        reportParse.put("academic_other", academic_other);
        reportParse.put("academic_setting", academicSetting);
        
        // Add strategy data.
      /*  reportParse.put("strategy_plannedIgnoring", strategy_plannedIgnoring);
        reportParse.put("strategy_behaviorLog", strategy_behaviorLog);
        reportParse.put("strategy_reteachReviewExpectations", strategy_reteachReviewExpectations);
        reportParse.put("strategy_restorativeAction", strategy_restorativeAction);
        reportParse.put("strategy_apologyVerbalAndOrWritten", strategy_apologyVerbalAndOrWritten);
        reportParse.put("strategy_scholarPairingTimeOut", strategy_scholarPairingTimeOut);
        reportParse.put("strategy_timeOut", strategy_timeOut);
        reportParse.put("strategy_ageAppropriateWritingActivity", strategy_ageAppropriateWritingActivity);
        reportParse.put("strategy_behaviorProcessingForm", strategy_behaviorProcessingForm);
        reportParse.put("strategy_teacherScholarConversationOutsideClassroom", strategy_teacherScholarConversationOutsideClassroom);
        reportParse.put("strategy_conversationWithFamily", strategy_conversationWithFamily);
        reportParse.put("strategy_conference", strategy_conference);
        reportParse.put("strategy_lossOfPriveleges", strategy_lossOfPriveleges);*/
        for(String key: SR.StrategyMap.keySet()){
        	reportParse.put(key, SR.StrategyMap.get(key));
        }
        
        reportParse.put("strategy_other", strategy_other);
        
        reportParse.put("report_details", reportDetailsAndComments);      
        reportParse.put("behavior_comments", this.behaviorComment);
        reportParse.put("academic_comments",this.academicComment);
        reportParse.put("strategy_comments", this.strategyComment);
        
        reportParse.put("behavior_details", this.behaviorSummary);
        reportParse.put("academic_details", this.academicSummary);
        reportParse.put("strategy_details", this.strategySummary);
        
        reportParse.put("object_id", this.objectID);
        
        return reportParse;
    }
    
    @Override
    public String toString() {
        return this.studentName + " " + this.reportCreatedDate;
    }
    
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(this.studentName);
		out.writeString(this.studentGrade);
		out.writeString(this.reportCreatedDate);
		out.writeString(this.behaviorSetting);
		out.writeString(this.behaviorSummary);
		out.writeString(this.behaviorComment);
		out.writeString(this.academicSetting);
		out.writeString(this.academicSummary);
		out.writeString(this.academicComment);
		out.writeString(this.strategySummary);
		out.writeString(this.strategyComment);
		out.writeString(this.reportDetailsAndComments);
		out.writeString(this.objectID);
	}
	
	private Report(Parcel in) {
		this.studentName              = in.readString();
		this.studentGrade             = in.readString();
		this.reportCreatedDate        = in.readString();
		this.behaviorSetting		  = in.readString();
		this.behaviorSummary          = in.readString();
		this.behaviorComment		  = in.readString();
		this.academicSetting		  = in.readString();
		this.academicSummary          = in.readString();
		this.academicComment		  = in.readString();
		this.strategySummary          = in.readString();
		this.strategyComment		  = in.readString();
		this.reportDetailsAndComments = in.readString();
		this.objectID			 	  = in.readString();
	}

	public static final Parcelable.Creator<Report> CREATOR
			= new Parcelable.Creator<Report>() {
		@Override
		public Report createFromParcel(Parcel in) {
			return new Report(in);
		}

		@Override
		public Report[] newArray(int size) {
			return new Report[size];
		}
	};
	

	public String createEmailReportString() {

		/*String report = String.format(
				"Student Behavior Report \n\nName: %s\nGrade: %s\nDate: ",
				studentName, studentGrade, reportCreatedDate);
		*/
		 String report = "Student Behavior Report \n"; report = report +
		  "\nName: " + studentName; report = report + "\nGrade: " +
		  studentGrade; report = report + "%s\nDate: " + reportCreatedDate;
		 
		if (!behaviorSummary.equals("")) {
			report = report + "\n\nBehavior Summary: \n" + behaviorSummary;
			if (!behaviorSetting.equals(""))
				report = report + "\nSetting: " + behaviorSetting;
			if (!behaviorComment.equals(""))
				report = report + "\n\nComments: " + behaviorComment;
		}
		if (!academicSummary.equals("")) {
			report = report + "\n\nAcademic Summary: \n" + academicSummary;
			if (!academicSetting.equals(""))
				report = report + "\nSetting: " + academicSetting;
			if (!academicComment.equals(""))
				report = report + "\n\nComments: " + academicComment;
		}
		if (!strategySummary.equals("")) {
			report = report + "\n\nStrategy Summary: \n" + strategySummary;
			if (!strategyComment.equals(""))
				report = report + "\n\nComments: " + strategyComment;
		}

		return report;
	}

	private String repo(String...content) {
		String result = "";
		
		for (int i = 0; i < 2; i++) {
			switch (content[i]) {
			case "":
				break;
			default:
				if (i == 0)
					result += "\nSetting: " + content[0];
				else
					result += "\n\nComments: " + content[1];
			}
		}
		return result;
	}

}
