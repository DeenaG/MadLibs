package project.madlibs;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class StoryActivity extends FragmentActivity implements FormFragment.OnCreateClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story);
	}
	
	@Override
	public void onCreateButtonClicked(String noun, String verb, String adj, String adv){
		MadLibsDB db = new MadLibsDB(this);
		MadLibs ml = db.getRandomMadLib();
		String story = ml.getMadLib(noun, verb, adj, adv);
		
		TextView tv = (TextView)findViewById(R.id.storyText);
		tv.setText(story);
		
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(tv.getWindowToken(), 0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case(R.id.tab1):
			startActivity(new Intent(StoryActivity.this, MainActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
