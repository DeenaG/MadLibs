package project.madlibs;

//import com.fragmentsExample.fragments.MasterFragment.OnItemClickListener;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonFragment extends Fragment {
	
	public interface OnSaveClickListener{
		public void onSaveButtonClicked();
	}
	
	private OnSaveClickListener listener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.button_fragment, container, false);
		Button save = (Button)view.findViewById(R.id.button);
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveStory();	
			}
		});
		return view;		
	}
	
	public void saveStory(){
		listener.onSaveButtonClicked();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof OnSaveClickListener){
			//listener will become the activity
			listener = (OnSaveClickListener)activity;
		}
		else{
			throw new ClassCastException(activity.toString()+"must implement master fragment OnSaveClickListener");
		}
	
	}
	
	
}
