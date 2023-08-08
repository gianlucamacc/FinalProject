package algonquin.cst2335.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.finalproject.databinding.TimeLayoutBinding;
/**
 * This fragment displays the time when a currency conversion was executed.
 */
public class ConversionTimeFragment extends Fragment {

    CurrencyConverter currencyConverter;
    /**
     * Constructs a new instance of the ConversionTimeFragment.
     *
     * @param cc The CurrencyConverter object containing conversion details.
     */
    public ConversionTimeFragment(CurrencyConverter cc){currencyConverter = cc;}
    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        TimeLayoutBinding binding = TimeLayoutBinding.inflate(inflater);

        binding.timeExecTextView.setText("  " + currencyConverter.timeExecuted);

        return binding.getRoot();
    }
}