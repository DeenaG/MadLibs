package project.madlibs;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ButtonFragment.OnSaveClickListener  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//testDatabase();
	}
	
	@Override
	public void onSaveButtonClicked(){
		InputFragment input = (InputFragment)getFragmentManager().findFragmentById(R.id.inputFragment);
		String story = input.getText();
		boolean correctFormat = storyFormatCorrect(story);
		if(!correctFormat)
			Toast.makeText(getApplicationContext(), "Story should contain all four keywords",
					   Toast.LENGTH_LONG).show();
		else{
			MadLibs ml = new MadLibs(story);
			MadLibsDB db = new MadLibsDB(this);
			db.insertMadLibs(ml);
			Intent intent = new Intent(this, StoryActivity.class);
			startActivity(intent);      
			//finish();
		}

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
		case(R.id.tab2):
			startActivity(new Intent(MainActivity.this, StoryActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//test the database
	public void testDatabase(){
		
		MadLibsDB db = new MadLibsDB(this);
		db.dropTable();
		
	}
	
	private static boolean storyFormatCorrect(String text){
		return text.contains("noun") && text.contains("verb") && text.contains ("adjective") && text.contains("adverb");
	}
	
}
	


