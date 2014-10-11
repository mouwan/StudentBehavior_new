package edu.upenn.cis350.comegysbehavior;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.support.v4.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Fragment that shows behavior report
 */
public class BehaviorFragment extends Fragment {
	
	private Report report = new Report();
	
	public BehaviorFragment() {
	}
	
	private Spinner academic_settings_spinner, behavior_settings_spinner, grade_spinner;
	private EditText student_name, strategy_comment, behavior_comment, academic_comment;
	private DatePicker date;
	
	private static final int[] checkboxBehaviorIds =
		{R.id.checkbox_behavior_respectForSelfAndOthers, R.id.checkbox_behavior_followingDirections,
			R.id.checkbox_behavior_positiveConflictResolution, R.id.checkbox_behavior_peerMediation,
			R.id.checkbox_behavior_helpingPeerOrStaff, R.id.checkbox_behavior_leadership,
			R.id.checkbox_behavior_dealingWithAdversityPositively, R.id.checkbox_behavior_goingAboveAndBeyond,
			R.id.checkbox_behavior_refusalToFollowDirectionsOrParticipate, R.id.checkbox_behavior_disruptionOfClassOrActivity,
			R.id.checkbox_behavior_disrespectOfStaffOrScholars, R.id.checkbox_behavior_inappropriateLanguageOrGestures,
			R.id.checkbox_behavior_inappropriatePhysicalContactOrFighting, R.id.checkbox_behavior_teasingOrInstigatingConflict,
			R.id.checkbox_behavior_runningInCommonSpaces, R.id.checkbox_behavior_leavingSupervisionUnattended,
			R.id.checkbox_behavior_failingToFollowRules};
	private static final int[] checkboxAcademicIds =
		{R.id.checkbox_academic_respectsLearningForSelfAndOthers, R.id.checkbox_academic_followsDirections, 
	    	R.id.checkbox_academic_consistentlyPreparedAndOrganized, R.id.checkbox_academic_completesHomeworkAndAssignments, 
	    	R.id.checkbox_academic_staysOnTask, R.id.checkbox_academic_peerTutoring, R.id.checkbox_academic_disruptionOfClassLessonActivity, 
	    	R.id.checkbox_academic_refusalToFollowDirectionsAndParticipate, R.id.checkbox_academic_unPreparedAndDisorganized, 
	    	R.id.checkbox_academic_failureToCompleteHomeworkAssignment, R.id.checkbox_academic_questionableAcademicIntegrity};
	private static final int[] checkboxStrategyIds =
		{R.id.checkbox_strategy_plannedIgnoring, R.id.checkbox_strategy_behaviorLog, 
	    	R.id.checkbox_strategy_reteachReviewExpectations, R.id.checkbox_strategy_restorativeAction, 
	    	R.id.checkbox_strategy_apologyVerbalAndOrWritten, R.id.checkbox_strategy_scholarPairingTimeOut, 
	    	R.id.checkbox_strategy_timeOut, R.id.checkbox_strategy_ageAppropriateWritingActivity, 
	    	R.id.checkbox_strategy_behaviorProcessingForm, R.id.checkbox_strategy_teacherScholarConversationOutsideClassroom, 
	    	R.id.checkbox_strategy_conversationWithFamily, R.id.checkbox_strategy_conference, R.id.checkbox_strategy_lossOfPriveleges};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.report, container, false);
		initSpinnerContent(rootView);
		initSubmitButton(rootView);
		
		student_name     = (EditText) rootView.findViewById(R.id.scholar_name);
		strategy_comment = (EditText) rootView.findViewById(R.id.strategy_comment);
		academic_comment = (EditText) rootView.findViewById(R.id.academic_comment);
		behavior_comment = (EditText) rootView.findViewById(R.id.behavior_comment);
		date = (DatePicker) rootView.findViewById(R.id.SelectDate);
		
		return rootView;
	}
	
	private void initSubmitButton(final View rootView) {
		final Button button = (Button) rootView.findViewById(R.id.submit_report_button);
        button.setOnClickListener(new View.OnClickListener() {
        	@Override
             public void onClick(View v) {
        		// 
            	report.studentName  = student_name.getText().toString();
            	report.studentGrade = grade_spinner.getSelectedItem().toString();
            	
            	setReportDate();
            	setReportComments();
            	setReportSettings();
            	setReportSummaries(rootView);
            	 
            	// Save in background and notify the user.
            	if (!report.studentName.equals("")) {
            	report.getParseObject().saveInBackground();
            	 
            	Toast submittedNotification = Toast.makeText(getActivity(), "Report submitted!", Toast.LENGTH_SHORT);
            	submittedNotification.show();
            	
            	// Reset the fields.
            	resetFields();
            	} else {
            		showFixUserInputDialog();
            	}
             }
         });
	}
	
	private void setReportDate() {
		int day = date.getDayOfMonth();
   		int month = date.getMonth();
   		int year =  date.getYear();
   		Calendar calendar = Calendar.getInstance();
   		calendar.set(year, month, day);
   		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", getResources().getConfiguration().locale);
   		report.reportCreatedDate = sdf.format(calendar.getTime());
	}
	
	private void setReportComments() {
		this.report.strategyComment = this.strategy_comment.getText().toString();
    	this.report.academicComment = this.academic_comment.getText().toString();
    	this.report.behaviorComment = this.behavior_comment.getText().toString();
	}
	
	private void setReportSettings() {
		this.report.behaviorSetting = this.behavior_settings_spinner.getSelectedItem().toString();
		this.report.academicSetting = this.academic_settings_spinner.getSelectedItem().toString();
	}
	
	private void setReportSummaries(View rootView) {
		this.report.behaviorSummary = createSummary(rootView, checkboxBehaviorIds);
		this.report.academicSummary = createSummary(rootView, checkboxAcademicIds);
		this.report.strategySummary = createSummary(rootView, checkboxStrategyIds);
	}
	
	private static String createSummary(View rootView, int[] resIDs) {
		StringBuilder summaryBuilder = new StringBuilder();
		for (int i = 0; i < resIDs.length; ++i) {
			CheckBox checkbox = (CheckBox) rootView.findViewById(resIDs[i]);
			if (checkbox.isChecked()) {
				summaryBuilder.append(checkbox.getText());
				summaryBuilder.append('\n');
			}
		}
		return summaryBuilder.toString();
	}
	
	public void onCheckboxClicked(View view) {
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	    	// Behavior Checkboxes
	        case R.id.checkbox_behavior_respectForSelfAndOthers:
	        	this.report.PB.behaviorRespectForSelfAndOthers = checked;
	            break;
	            
	        case R.id.checkbox_behavior_followingDirections:
	        	this.report.PB.behaviorFollowingDirections = checked; 
	            break;
	            
	        case R.id.checkbox_behavior_positiveConflictResolution:
	        	this.report.PB.behaviorPositiveConflictResolution = checked;
	            break;
	            
	        case R.id.checkbox_behavior_peerMediation:
	        	this.report.PB.behaviorPeerMediation = checked;
	            break;
	            
	        case R.id.checkbox_behavior_helpingPeerOrStaff:
	        	this.report.PB.behaviorHelpingPeerOrStaff = checked;
	            break;
	            
	        case R.id.checkbox_behavior_leadership:
	        	this.report.PB.behaviorLeadership = checked;
	            break;
	            
	        case R.id.checkbox_behavior_dealingWithAdversityPositively:
	        	this.report.PB.behaviorDealingWithAdversityPositively = checked;
	            break;
	            
	        case R.id.checkbox_behavior_goingAboveAndBeyond:
	        	this.report.PB.behaviorGoingAboveAndBeyond = checked;
	            break;
	            
	        case R.id.checkbox_behavior_refusalToFollowDirectionsOrParticipate:
	        	this.report.NB.behavior_refusalToFollowDirectionsOrParticipate = checked;
	            break;
	            
	        case R.id.checkbox_behavior_disruptionOfClassOrActivity:
	        	this.report.NB.behavior_disruptionOfClassOrActivity = checked;
	            break;
	            
	        case R.id.checkbox_behavior_disrespectOfStaffOrScholars:
	        	this.report.NB.behavior_disrespectOfStaffOrScholars = checked;
	            break;
	            
	        case R.id.checkbox_behavior_inappropriateLanguageOrGestures:
	        	this.report.NB.behavior_inappropriateLanguageOrGestures = checked;
	            break;
	            
	        case R.id.checkbox_behavior_inappropriatePhysicalContactOrFighting:
	        	this.report.NB.behavior_inappropriatePhysicalContactOrFighting = checked;
	            break;
	            
	        case R.id.checkbox_behavior_teasingOrInstigatingConflict:
	        	this.report.NB.behavior_teasingOrInstigatingConflict = checked;
	            break;
	            
	        case R.id.checkbox_behavior_runningInCommonSpaces:
	        	this.report.NB.behavior_runningInCommonSpaces = checked;
	            break;
	            
	        case R.id.checkbox_behavior_leavingSupervisionUnattended:
	        	this.report.NB.behavior_leavingSupervisionUnattended = checked;
	            break;
	            
	        case R.id.checkbox_behavior_failingToFollowRules:
	        	this.report.NB.behavior_failingToFollowRules = checked;
	            break;
	            
	        
	        // Academic Checkboxes
	        case R.id.checkbox_academic_respectsLearningForSelfAndOthers:
	        	this.report.AR.academic_respectsLearningForSelfAndOthers = checked;
	            break;
	            
	        case R.id.checkbox_academic_followsDirections:
	        	this.report.AR.academic_followsDirections = checked;
	            break;
	            
	        case R.id.checkbox_academic_consistentlyPreparedAndOrganized:
	        	this.report.AR.academic_consistentlyPreparedAndOrganized = checked;
	            break;
	            
	        case R.id.checkbox_academic_completesHomeworkAndAssignments:
	        	this.report.AR.academic_completesHomeworkAndAssignments = checked;
	            break;
	            
	        case R.id.checkbox_academic_staysOnTask:
	        	this.report.AR.academic_staysOnTask = checked;
	            break;
	            
	        case R.id.checkbox_academic_peerTutoring:
	        	this.report.AR.academic_peerTutoring = checked;
	            break;
	            
	        case R.id.checkbox_academic_struggles:
	        	this.report.AR.academic_struggles = checked;
	            break;
	            
	        case R.id.checkbox_academic_disruptionOfClassLessonActivity:
	        	this.report.AR.academic_disruptionOfClassLessonActivity = checked;
	            break;
	            
	        case R.id.checkbox_academic_refusalToFollowDirectionsAndParticipate:
	        	this.report.AR.academic_refusalToFollowDirectionsAndParticipate = checked;
	            break;
	            
	        case R.id.checkbox_academic_unPreparedAndDisorganized:
	        	this.report.AR.academic_unPreparedAndDisorganized = checked; 
	            break;
	            
	        case R.id.checkbox_academic_failureToCompleteHomeworkAssignment:
	        	this.report.AR.academic_failureToCompleteHomeworkAssignment = checked;
	            break;
	            
	        case R.id.checkbox_academic_questionableAcademicIntegrity:
	        	this.report.AR.academic_questionableAcademicIntegrity = checked;
	            break;
	            
	        case R.id.checkbox_strategy_plannedIgnoring:
	        	this.report.SR.strategy_plannedIgnoring = checked;
	            break;
	            
	            
	        // Strategies
	        case R.id.checkbox_strategy_behaviorLog:
	        	this.report.SR.strategy_behaviorLog = checked;
	            break;
	            
	        case R.id.checkbox_strategy_reteachReviewExpectations:
	        	this.report.SR.strategy_reteachReviewExpectations = checked;
	            break;
	            
	        case R.id.checkbox_strategy_restorativeAction:
	        	this.report.SR.strategy_restorativeAction = checked;
	            break;
	            
	        case R.id.checkbox_strategy_apologyVerbalAndOrWritten:
	        	this.report.SR.strategy_apologyVerbalAndOrWritten = checked;
	            break;
	            
	        case R.id.checkbox_strategy_scholarPairingTimeOut:
	        	this.report.SR.strategy_scholarPairingTimeOut = checked;
	            break;
	            
	        case R.id.checkbox_strategy_timeOut:
	        	this.report.SR.strategy_timeOut = checked;
	            break;
	            
	        case R.id.checkbox_strategy_ageAppropriateWritingActivity:
	        	this.report.SR.strategy_ageAppropriateWritingActivity = checked;
	            break;
	            
	        case R.id.checkbox_strategy_behaviorProcessingForm:
	        	this.report.SR.strategy_behaviorProcessingForm = checked;
	            break;
	            
	        case R.id.checkbox_strategy_teacherScholarConversationOutsideClassroom:
	        	this.report.SR.strategy_teacherScholarConversationOutsideClassroom = checked;
	            break;
	            
	        case R.id.checkbox_strategy_conversationWithFamily:
	        	this.report.SR.strategy_conversationWithFamily = checked;
	            break;
	            
	        case R.id.checkbox_strategy_conference:
	        	this.report.SR.strategy_conference = checked;
	            break;
	            
	        case R.id.checkbox_strategy_lossOfPriveleges:
	        	this.report.SR.strategy_lossOfPriveleges = checked;
	            break;
	    }
	}
	
	/*public void onCheckboxClicked(View view) {
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	    	// Behavior Checkboxes
	        case R.id.checkbox_behavior_respectForSelfAndOthers:
	        	this.report.behavior_respectForSelfAndOthers = checked;
	            break;
	            
	        case R.id.checkbox_behavior_followingDirections:
	        	this.report.behavior_followingDirections = checked; 
	            break;
	            
	        case R.id.checkbox_behavior_positiveConflictResolution:
	        	this.report.behavior_positiveConflictResolution = checked;
	            break;
	            
	        case R.id.checkbox_behavior_peerMediation:
	        	this.report.behavior_peerMediation = checked;
	            break;
	            
	        case R.id.checkbox_behavior_helpingPeerOrStaff:
	        	this.report.behavior_helpingPeerOrStaff = checked;
	            break;
	            
	        case R.id.checkbox_behavior_leadership:
	        	this.report.behavior_leadership = checked;
	            break;
	            
	        case R.id.checkbox_behavior_dealingWithAdversityPositively:
	        	this.report.behavior_dealingWithAdversityPositively = checked;
	            break;
	            
	        case R.id.checkbox_behavior_goingAboveAndBeyond:
	        	this.report.behavior_goingAboveAndBeyond = checked;
	            break;
	            
	        case R.id.checkbox_behavior_refusalToFollowDirectionsOrParticipate:
	        	this.report.behavior_refusalToFollowDirectionsOrParticipate = checked;
	            break;
	            
	        case R.id.checkbox_behavior_disruptionOfClassOrActivity:
	        	this.report.behavior_disruptionOfClassOrActivity = checked;
	            break;
	            
	        case R.id.checkbox_behavior_disrespectOfStaffOrScholars:
	        	this.report.behavior_disrespectOfStaffOrScholars = checked;
	            break;
	            
	        case R.id.checkbox_behavior_inappropriateLanguageOrGestures:
	        	this.report.behavior_inappropriateLanguageOrGestures = checked;
	            break;
	            
	        case R.id.checkbox_behavior_inappropriatePhysicalContactOrFighting:
	        	this.report.behavior_inappropriatePhysicalContactOrFighting = checked;
	            break;
	            
	        case R.id.checkbox_behavior_teasingOrInstigatingConflict:
	        	this.report.behavior_teasingOrInstigatingConflict = checked;
	            break;
	            
	        case R.id.checkbox_behavior_runningInCommonSpaces:
	        	this.report.behavior_runningInCommonSpaces = checked;
	            break;
	            
	        case R.id.checkbox_behavior_leavingSupervisionUnattended:
	        	this.report.behavior_leavingSupervisionUnattended = checked;
	            break;
	            
	        case R.id.checkbox_behavior_failingToFollowRules:
	        	this.report.behavior_failingToFollowRules = checked;
	            break;
	            
	        
	        // Academic Checkboxes
	        case R.id.checkbox_academic_respectsLearningForSelfAndOthers:
	        	this.report.academic_respectsLearningForSelfAndOthers = checked;
	            break;
	            
	        case R.id.checkbox_academic_followsDirections:
	        	this.report.academic_followsDirections = checked;
	            break;
	            
	        case R.id.checkbox_academic_consistentlyPreparedAndOrganized:
	        	this.report.academic_consistentlyPreparedAndOrganized = checked;
	            break;
	            
	        case R.id.checkbox_academic_completesHomeworkAndAssignments:
	        	this.report.academic_completesHomeworkAndAssignments = checked;
	            break;
	            
	        case R.id.checkbox_academic_staysOnTask:
	        	this.report.academic_staysOnTask = checked;
	            break;
	            
	        case R.id.checkbox_academic_peerTutoring:
	        	this.report.academic_peerTutoring = checked;
	            break;
	            
	        case R.id.checkbox_academic_struggles:
	        	this.report.academic_struggles = checked;
	            break;
	            
	        case R.id.checkbox_academic_disruptionOfClassLessonActivity:
	        	this.report.academic_disruptionOfClassLessonActivity = checked;
	            break;
	            
	        case R.id.checkbox_academic_refusalToFollowDirectionsAndParticipate:
	        	this.report.academic_refusalToFollowDirectionsAndParticipate = checked;
	            break;
	            
	        case R.id.checkbox_academic_unPreparedAndDisorganized:
	        	this.report.academic_unPreparedAndDisorganized = checked; 
	            break;
	            
	        case R.id.checkbox_academic_failureToCompleteHomeworkAssignment:
	        	this.report.academic_failureToCompleteHomeworkAssignment = checked;
	            break;
	            
	        case R.id.checkbox_academic_questionableAcademicIntegrity:
	        	this.report.academic_questionableAcademicIntegrity = checked;
	            break;
	            
	        case R.id.checkbox_strategy_plannedIgnoring:
	        	this.report.strategy_plannedIgnoring = checked;
	            break;
	            
	            
	        // Strategies
	        case R.id.checkbox_strategy_behaviorLog:
	        	this.report.strategy_behaviorLog = checked;
	            break;
	            
	        case R.id.checkbox_strategy_reteachReviewExpectations:
	        	this.report.strategy_reteachReviewExpectations = checked;
	            break;
	            
	        case R.id.checkbox_strategy_restorativeAction:
	        	this.report.strategy_restorativeAction = checked;
	            break;
	            
	        case R.id.checkbox_strategy_apologyVerbalAndOrWritten:
	        	this.report.strategy_apologyVerbalAndOrWritten = checked;
	            break;
	            
	        case R.id.checkbox_strategy_scholarPairingTimeOut:
	        	this.report.strategy_scholarPairingTimeOut = checked;
	            break;
	            
	        case R.id.checkbox_strategy_timeOut:
	        	this.report.strategy_timeOut = checked;
	            break;
	            
	        case R.id.checkbox_strategy_ageAppropriateWritingActivity:
	        	this.report.strategy_ageAppropriateWritingActivity = checked;
	            break;
	            
	        case R.id.checkbox_strategy_behaviorProcessingForm:
	        	this.report.strategy_behaviorProcessingForm = checked;
	            break;
	            
	        case R.id.checkbox_strategy_teacherScholarConversationOutsideClassroom:
	        	this.report.strategy_teacherScholarConversationOutsideClassroom = checked;
	            break;
	            
	        case R.id.checkbox_strategy_conversationWithFamily:
	        	this.report.strategy_conversationWithFamily = checked;
	            break;
	            
	        case R.id.checkbox_strategy_conference:
	        	this.report.strategy_conference = checked;
	            break;
	            
	        case R.id.checkbox_strategy_lossOfPriveleges:
	        	this.report.strategy_lossOfPriveleges = checked;
	            break;
	    }
	}*/
	
	private void initSpinnerContent(View view) {
	  behavior_settings_spinner = (Spinner) view.findViewById( R.id.behavior_settings_spinner );
	  academic_settings_spinner = (Spinner) view.findViewById( R.id.academic_settings_spinner); 
	  grade_spinner = (Spinner) view.findViewById( R.id.grade_spinner);
	}
	
	private void resetFields() {
		MainActivity mainActivity = (MainActivity) getActivity();
		mainActivity.resetBehaviorFragment();
	}

	private void showFixUserInputDialog() {
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		
		alert.setTitle("Incomplete form");
		alert.setMessage("Please enter the student's name before submitting.");

		alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

			  }
			});

		alert.show();
	}
	
	public Report getReport() {
		return this.report;
	}
}