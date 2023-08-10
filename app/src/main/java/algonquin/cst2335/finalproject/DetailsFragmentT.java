package algonquin.cst2335.finalproject;


import static algonquin.cst2335.finalproject.AviationRecyclerViewAdapter.aDAO;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import algonquin.cst2335.finalproject.databinding.DetailsLayoutBinding;
import algonquin.cst2335.finalproject.databinding.DetailsLayoutTBinding;

/**
 * A Fragment subclass that displays detailed information about a selected TriviaScores object.
 */
public class DetailsFragmentT extends Fragment {

    private TriviaScores selected;

    /**
     * Constructs a new DetailsFragment instance with the provided TriviaScores object.
     *
     * @param t The TriviaScores object for which detailed information will be displayed.
     */
    public DetailsFragmentT(TriviaScores t){
        selected = t;
    }

    /**
     * Inflates the layout for the DetailsFragment and displays detailed information about the
     * selected TriviaScores object.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState A Bundle containing the saved state of the fragment.
     * @return                   The root View of the inflated layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        DetailsLayoutTBinding binding = DetailsLayoutTBinding.inflate(inflater);
        binding.timeTaken.setText(selected.timeTaken);

        return binding.getRoot();
    }
}

