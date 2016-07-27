package com.sgapps.shaharraz.mayday.fragments;


import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.activities.MainActivity;
import com.sgapps.shaharraz.mayday.models.AddressClass;
import com.sgapps.shaharraz.mayday.models.CountryClass;
import com.google.android.gms.maps.model.LatLng;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneCallFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = OneCallFragment.class.getSimpleName();
    private CountryClass mCountryClass = null;
    private AddressClass mAddressClass = null;

    private View revealView;
    private LatLng currentLatLng;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private OnSwipeToRefreshListenerOne mListener;
    private TextView txtAddressFull;

    public OneCallFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the Arguments
        Bundle args = getArguments();
        if (args != null) {
            mCountryClass = args.getParcelable(MainActivity.COUNTRY_KEY);
            mAddressClass = args.getParcelable(MainActivity.ADDRESS_KEY);
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        double lat = Double.parseDouble(sp.getString(MainActivity.LAT_SHARED_KEY, "1"));
        double lng = Double.parseDouble(sp.getString(MainActivity.LONG_SHARED_KEY, "1"));
        currentLatLng = new LatLng(lat, lng);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnSwipeToRefreshListenerOne) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one_call, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListener.refreshOneFragment();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        CardView emCard = (CardView) v.findViewById(R.id.emCard);
        TextView txtAddress = (TextView) v.findViewById(R.id.txtAddressCord);
        TextView txtAddressFull = (TextView) v.findViewById(R.id.txtAddressFull);
        revealView = v.findViewById(R.id.reveal_view);
        emCard.setOnClickListener(this);
        txtAddress.setText(currentLatLng.latitude + ", " + currentLatLng.longitude);

        setTextAddress();

        return v;
    }

    //*************************** Animation **********************************//

    @Override
    public void onResume() {
        super.onResume();
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
        if (revealView.getVisibility() == View.VISIBLE) {
            revealView.setVisibility(View.INVISIBLE);
        }

    }

    public void setTextAddress() {
        String address;
        if (mAddressClass.getAddress() != null) {
            address = mAddressClass.getAddress();
        } else {
            address = "";
        }
        String city;
        if (mAddressClass.getCity() != null) {
            city = mAddressClass.getCity();
        } else {
            city = "";
        }
        String country;
        if (mAddressClass.getCity() != null) {
            country = mAddressClass.getCountry();
        } else {
            country = "";
        }

        txtAddressFull.setText(address + ", " + city + ", " + country);
    }


    Animator.AnimatorListener revealAnimationListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            //when ends - intent to dial
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mCountryClass.getPoliceNum()));
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            getActivity().overridePendingTransition(0, 0);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    };

    private void hideNavigationStatus() {

        View decorView = getActivity().getWindow().getDecorView();
        View fab = getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showRevealEffect(final View v, int centerX, int centerY, Animator.AnimatorListener lis) {

        v.setVisibility(View.VISIBLE);
        int height = v.getHeight();
        Animator anim = ViewAnimationUtils.createCircularReveal(
                v, centerX, centerY, 0, height);

        anim.setDuration(350);
        anim.addListener(lis);
        anim.start();


    }

    public static int getStatusBarHeight(Context c) {

        int result = 0;
        int resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = c.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void startAnimationColor(View v, int color) {
        int[] location = new int[2];
        revealView.setBackgroundColor(color);
        v.getLocationOnScreen(location);
        int cx = (location[0] + (v.getWidth() / 2));
        int cy = location[1] + (getStatusBarHeight(getActivity()) / 2);
        hideNavigationStatus();
        showRevealEffect(revealView, cx, cy, revealAnimationListener);

    }

    //*************************** END --- Animation **********************************//

    @Override
    public void onClick(View v) {
        int colorAnimation = getResources().getColor(R.color.white_color);
        startAnimationColor(v, colorAnimation);
    }


    public interface OnSwipeToRefreshListenerOne {
        void refreshOneFragment();


    }
}
