/**
 * A Fragment class that displays detailed information about a selected TriviaScores object.
 * This class is responsible for inflating the layout and populating it with data.
 */
package algonquin.cst2335.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import algonquin.cst2335.finalproject.databinding.DetailsLayoutBinding;

public class DetailsFragment extends Fragment {

    private TriviaScores selected;

    /**
     * Constructs a new DetailsFragment with the provided TriviaScores object.
     *
     * @param t The TriviaScores object to display detailed information for.
     */
    public DetailsFragment(TriviaScores t){
        selected = t;
    }

    /**
     * Called to create the content view for this Fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return                   The View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout using the generated binding class
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);

        // Set the timeTaken TextView with the selected TriviaScores object's timeTaken value
        binding.timeTaken.setText(selected.timeTaken);

        return binding.getRoot();
    }
}
