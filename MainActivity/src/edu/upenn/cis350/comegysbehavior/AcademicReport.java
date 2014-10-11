package edu.upenn.cis350.comegysbehavior;

import java.util.HashMap;
import java.util.Map;

import com.parse.ParseObject;


public class AcademicReport {
	public boolean academic_respectsLearningForSelfAndOthers = false;
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
	public Map<String, Boolean> AcademicMap = new HashMap<String, Boolean>();
	
	public AcademicReport(){
		AcademicMap.put("academic_respectsLearningForSelfAndOthers", academic_respectsLearningForSelfAndOthers);
        AcademicMap.put("academic_followsDirections", academic_followsDirections);
        AcademicMap.put("academic_consistentlyPreparedAndOrganized", academic_consistentlyPreparedAndOrganized);
        AcademicMap.put("academic_completesHomeworkAndAssignments", academic_completesHomeworkAndAssignments);
        AcademicMap.put("academic_staysOnTask", academic_staysOnTask);
        AcademicMap.put("academic_peerTutoring", academic_peerTutoring);
        AcademicMap.put("academic_struggles", academic_struggles);
        AcademicMap.put("academic_disruptionOfClassLessonActivity", academic_disruptionOfClassLessonActivity);
        AcademicMap.put("academic_refusalToFollowDirectionsAndParticipate", academic_refusalToFollowDirectionsAndParticipate);
        AcademicMap.put("academic_unPreparedAndDisorganized", academic_unPreparedAndDisorganized);
        AcademicMap.put("academic_failureToCompleteHomeworkAssignment", academic_failureToCompleteHomeworkAssignment);
        AcademicMap.put("academic_questionableAcademicIntegrity", academic_questionableAcademicIntegrity);
	}
	
	
	public void retrieveAcademicData(ParseObject parseObject){
		this.academic_respectsLearningForSelfAndOthers = parseObject.getBoolean("academic_respectsLearningForSelfAndOthers");
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
        
	}
	
}
