package project.madlibs;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FormFragment extends Fragment {
	public interface OnCreateClickListener{
		public void onCreateButtonClicked(String noun, String verb, String adj, String adv);
	}
	
	private OnCreateClickListener listener;
	private EditText noun, verb, adj, adv;
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.form_fragment, container, false);
		Button create = (Button)view.findViewById(R.id.create);
		create.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createStory();	
			}
		});
		return view;
	}
	
	public void createStory(){
		noun = (EditText)view.findViewById(R.id.noun);
		verb = (EditText)view.findViewById(R.id.verb);
		adj = (EditText)view.findViewById(R.id.adj);
		adv = (EditText)view.findViewById(R.id.adv);
		String nounText = noun.getText().toString();
		String verbText = verb.getText().toString();
		String adjText = adj.getText().toString();
		String advText = adv.getText().toString();
		listener.onCreateButtonClicked(nounText, verbText, adjText, advText);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof OnCreateClickListener){
			//listener will become the activity
			listener = (OnCreateClickListener)activity;
		}
		else{
			throw new ClassCastException(activity.toString()+"must implement master fragment OnCreateClickListener");
		}
	
	}
	

}
