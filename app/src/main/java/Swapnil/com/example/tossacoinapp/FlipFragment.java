package Swapnil.com.example.tossacoinapp;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

import Swapnil.com.example.tossacoinapp.Database.FlipHistoryEntity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FlipFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FlipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlipFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FlipFragmentViewModel mflipFragmentViewModel;
    private ImageView mImageViewCoin;


    private Button mButtonFlip;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FlipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlipFragment.
     */

    public static FlipFragment newInstance(String param1, String param2) {
        FlipFragment fragment = new FlipFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("FlipFragment:-","onActivityCreated");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flip, container, false);

        // In Fragment if you want put menu you setHasOptionsMenu(true); method in onCreateView.
        setHasOptionsMenu(true);
        mflipFragmentViewModel = ViewModelProviders.of(this).get(FlipFragmentViewModel.class);

        mImageViewCoin = view.findViewById(R.id.imageView);

        mButtonFlip = view.findViewById(R.id.buttonFlipCoin);

        mButtonFlip.setOnClickListener(v -> {
            // This code is for Animation.
            Animation fadeOut = new AlphaAnimation(1, 0);
            fadeOut.setInterpolator(new AccelerateInterpolator());
            fadeOut.setDuration(1000);
            fadeOut.setFillAfter(true);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    // minimum value.
                    int min = 1;

                    // create random object
                    Random randomno = new Random();

                    // getting the random values between 1 and 2.
                    // Here 2 is max value.
                    // Remember the value we get from randomno.nextInt(2)
                    // is being added with min value.
                    // So Than we don't get 0 value as output which we don't want.

                    int getResult = min + randomno.nextInt(2);
                    // if getresult is equal to 1 than it is tails.
                    // if getresult is equal to 2 than it is heads.

                    // get values from SharedPreferences file.
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    boolean get_record_preference = sharedPreferences.getBoolean(getString(R.string.switch_history_preference),true);
                    Log.i("Setting:-",String.valueOf(get_record_preference));
                    // if get_record_preference = True then save Data to Database.
                    if (get_record_preference){
                        // Checking for null.
                        if (getResult != 0){
                            Log.i("Result:-",String.valueOf(getResult));
                            // if getResult == 1 then save Tails,Get current time and date.
                            if (getResult == 1){
                                FlipHistoryEntity flipHistoryEntity = new FlipHistoryEntity("Tails",DateTimeUtility.getDate(),DateTimeUtility.getTime());
                                Log.i("Save:- ","Tails" + String.valueOf(DateTimeUtility.getDate()) + String.valueOf(DateTimeUtility.getTime()));
                                mflipFragmentViewModel.insert(flipHistoryEntity);
                            }
                            // if getResult == 2 then save Heads,Get current time and date.
                            else if(getResult == 2) {
                                FlipHistoryEntity flipHistoryEntity = new FlipHistoryEntity("Heads",DateTimeUtility.getDate(),DateTimeUtility.getTime());
                                Log.i("Save:- ","Heads" + String.valueOf(DateTimeUtility.getDate()) + String.valueOf(DateTimeUtility.getTime()));
                                mflipFragmentViewModel.insert(flipHistoryEntity);
                            }
                        }
                    }

                    // Set ImageResource.
                    mImageViewCoin.setImageResource(getResult > 1 ? R.drawable.heads : R.drawable.tails);

                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new DecelerateInterpolator());
                    fadeIn.setDuration(3000);
                    fadeIn.setFillAfter(true);

                    mImageViewCoin.startAnimation(fadeIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            mImageViewCoin.startAnimation(fadeOut);




        });



        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings){
            // Open SettingFragment.
            Intent intent = new Intent(getContext(),SettingActivity.class);
            startActivity(intent);
        }


        return true;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
