package edu.upenn.cis350.comegysbehavior;

import java.util.HashMap;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PastReportDetails extends Activity {
	
	protected Report pastReport;
	private HashMap<Integer,String>idStringMap = new HashMap<Integer,String>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.past_reports_details);

		// Retrieve brother data from intent.
		int s = R.string.past_report_data;
		String content = getString(s);
		pastReport = (Report)getIntent().getParcelableExtra(content);
		
		
		//pastReport = (Report) getIntent().getParcelableExtra(getString(R.string.past_report_data));
		
		createHash();
		for (Integer i: idStringMap.keySet()){
			setTextfield(i);
		}
		
		
		
		/*// Set all labels.
		// Set Name
		TextView nameLabel = (TextView) findViewById(R.id.name);
		String name =  pastReport.studentName;
		nameLabel.setText(name); 
		
		// Set Grade
		TextView gradeLabel = (TextView) findViewById(R.id.grade);
		String grade =  pastReport.studentGrade;
		gradeLabel.setText(grade);
		
		// Set Date
		TextView dateLabel = (TextView) findViewById(R.id.date);
		String date =  pastReport.reportCreatedDate;
		dateLabel.setText(date);
		
		// Set Behavior Setting
		TextView bSettingLabel = (TextView) findViewById(R.id.behavior_location);
		String bSetting =  pastReport.behaviorSetting;
		bSettingLabel.setText(bSetting);
		
		// Set Behavior Details
		TextView behaviorLabel = (TextView) findViewById(R.id.behavior_report_details);
		String behavior =  pastReport.behaviorSummary;
		behaviorLabel.setText(behavior);
		
		// Set Behavior Comments
		TextView bCommsLabel = (TextView) findViewById(R.id.behavior_report_comments);
		String behaviorCom =  pastReport.behaviorComment;
		bCommsLabel.setText(behaviorCom);
		
		// Set Academic Setting
		TextView aSettingLabel = (TextView) findViewById(R.id.academic_location);
		String aSetting =  pastReport.academicSetting;
		aSettingLabel.setText(aSetting);
		
		// Set Academic Details
		TextView academicLabel = (TextView) findViewById(R.id.academic_report_details);
		String academic =  pastReport.academicSummary;
		academicLabel.setText(academic);
		
		// Set Academic Comments
		TextView academicComsLabel = (TextView) findViewById(R.id.academic_report_comments);
		String academicCom =  pastReport.academicComment;
		academicComsLabel.setText(academicCom);
		
		// Set Strategies Details
		TextView strategiesLabel = (TextView) findViewById(R.id.strategies_report_details);
		String strategy =  pastReport.strategySummary;
		strategiesLabel.setText(strategy);
		
		// Set strategies Comments
		TextView strategiesComLabel = (TextView) findViewById(R.id.strategies_report_comments);
		String strategyCom =  pastReport.strategyComment;
		strategiesComLabel.setText(strategyCom);*/

		final Button button = (Button) findViewById(R.id.email_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	getUserEmailPrompt();
            }
        });
	
       final Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	new AlertDialog.Builder(PastReportDetails.this)
            		.setTitle("Confirm Deletion")
            		.setMessage("Delete this report?")
            		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		            	@Override
		            	public void onClick(DialogInterface dialog, int which) {
		            		deleteReport();
		            		
			            	// Notify the user that deletion is complete.
			            	Toast logoutNotification = Toast.makeText(PastReportDetails.this, "Report deleted.", Toast.LENGTH_SHORT);
			            	logoutNotification.show();
			            	PastReportDetails.this.finish();
			            }
			        })
	            	.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		            	public void onClick(DialogInterface dialog, int which) {
		            		// do nothing
		            	}
		            })
	            	.setIcon(android.R.drawable.ic_dialog_alert)
	            	.show();
            }
        });	
         
	}
	private void createHash(){
		idStringMap.put(R.id.name,pastReport.studentName);
		idStringMap.put(R.id.grade, pastReport.studentGrade);
		idStringMap.put(R.id.date, pastReport.reportCreatedDate);
		idStringMap.put(R.id.behavior_location,pastReport.behaviorSetting);
		
		idStringMap.put(R.id.behavior_report_details,pastReport.behaviorSummary);
		idStringMap.put(R.id.behavior_report_comments,pastReport.behaviorComment);
		idStringMap.put(R.id.academic_location,pastReport.academicSetting);
		idStringMap.put(R.id.academic_report_details,pastReport.academicSummary);
		idStringMap.put(R.id.academic_report_comments,pastReport.academicComment);
		idStringMap.put(R.id.strategies_report_details,pastReport.strategySummary);
	}
	private void setTextfield(int m){
		TextView view = (TextView) findViewById(m);
		String content = idStringMap.get(m);
		view.setText(content);
	}
	
	private void deleteReport() {
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Report");
    	query.whereEqualTo("objectId", pastReport.objectID);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> parseReportList, ParseException e) {
				if (e == null)
					try {
						if (parseReportList.size() > 0) {
							parseReportList.get(0).delete();
							PastReportDetails.this.setResult(ReportsListFragment.RESULT_DELETED_REPORT);
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
			}
		});
	}
	
	protected void sendEmail(String emailAddress) {
	      Intent emailIntent = new Intent(Intent.ACTION_SEND);
	      emailIntent.setData(Uri.parse("mailto:"));
	      emailIntent.setType("text/plain");


	      emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddress});
	      emailIntent.putExtra(Intent.EXTRA_SUBJECT, "New Student Behavior Report");
	      emailIntent.putExtra(Intent.EXTRA_TEXT, pastReport.createEmailReportString());

	      try {
	         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(this, 
	         "There is no email client installed.", Toast.LENGTH_SHORT).show();
	      }
	}
	
	private void getUserEmailPrompt() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		
		alert.setTitle("Send to Email");
		alert.setMessage("If you would like to receive a copy of this report by email, please enter your email address below.");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Send email", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String emailAddress = input.getText().toString();
		  sendEmail(emailAddress);
		  }
		});

		alert.setNegativeButton("Don't email", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();
	}
}