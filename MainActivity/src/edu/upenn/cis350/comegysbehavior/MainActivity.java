package edu.upenn.cis350.comegysbehavior;

import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.parse.Parse;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	// spinner for settings
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		// Initialize Parse
		try{
			Parse.initialize(this, "Q60Xc0o3UZOBLeB8mO83gX19LNV25xwr4LtaX34n", "o4JPB1gTW3Vov6UASIRMeuqVeB0Kg6uwy2G3ufXs");
		} catch(Exception e){
		}
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		if (tab.getPosition() == 1) {
			ReportsListFragment reportsList = (ReportsListFragment) mSectionsPagerAdapter.getItem(1);
			reportsList.refreshReports();
		}
	}
	
	public void onCheckboxClicked(View view) {
		BehaviorFragment behaviorFragment = (BehaviorFragment) mSectionsPagerAdapter.getItem(0);
		behaviorFragment.onCheckboxClicked(view);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode,
            Intent data) {
		if (requestCode == ReportsListFragment.REPORT_DETAILS_REQUEST) {
			ReportsListFragment reportsList = (ReportsListFragment) mSectionsPagerAdapter.getItem(1);
			reportsList.onActivityResult(requestCode, resultCode, data);
		}
        
    }
	
	public void resetBehaviorFragment() {
		mSectionsPagerAdapter.resetReportFragment();
	}
	
	public BehaviorFragment getBehaviorFragment() {
		// For testing.
		return (BehaviorFragment) mSectionsPagerAdapter.getItem(0);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		private Fragment reportFragment = new BehaviorFragment();
		private Fragment reportsListFragment = new ReportsListFragment();
		
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a StrategySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch (position) {
			case 0:
				return this.reportFragment;
			case 1:
				return this.reportsListFragment;
			default:
				return null;
			}
			
		}

		@Override
		public int getCount() {
			// Show two total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			return null;
		}
		
		private void resetReportFragment() {
			this.reportFragment = new BehaviorFragment();
		}
	}

}
