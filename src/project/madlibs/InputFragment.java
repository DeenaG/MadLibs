package project.madlibs;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InputFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.input_fragment, container, false);
		return view;
				
	}
	
	public String getText(){
		TextView view = (TextView)getView().findViewById(R.id.inputTextView);
		if (view == null)
			Log.d("text view message", "view is null");
		else
			Log.d("text view message", view.getText().toString());
		return view.getText().toString();
	}

}
