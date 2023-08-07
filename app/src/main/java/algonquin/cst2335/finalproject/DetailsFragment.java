package algonquin.cst2335.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import algonquin.cst2335.finalproject.databinding.DetailsLayoutBinding;

public class DetailsFragment extends Fragment {

    TriviaScores selected;
    public DetailsFragment(TriviaScores t){
        selected = t;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);
        binding.timeTaken.setText(selected.timeTaken);

        return binding.getRoot();
    }
}
