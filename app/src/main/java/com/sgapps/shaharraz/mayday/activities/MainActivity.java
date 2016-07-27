package com.sgapps.shaharraz.mayday.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;


import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.sgapps.shaharraz.mayday.adapters.ContactListAdapter;
import com.sgapps.shaharraz.mayday.fragments.AddContactFragment;
import com.sgapps.shaharraz.mayday.DialogFragments.NumbersDialogFragment;
import com.sgapps.shaharraz.mayday.R;
import com.sgapps.shaharraz.mayday.adapters.CountryListAdapter;
import com.sgapps.shaharraz.mayday.fragments.ListContactsFragment;
import com.sgapps.shaharraz.mayday.fragments.ListCountriesFragment;
import com.sgapps.shaharraz.mayday.fragments.ThreeCallsFragment;
import com.sgapps.shaharraz.mayday.fragments.MapFragment;
import com.sgapps.shaharraz.mayday.fragments.OneCallFragment;
import com.sgapps.shaharraz.mayday.models.AddressClass;
import com.sgapps.shaharraz.mayday.models.ContactClass;
import com.sgapps.shaharraz.mayday.models.CountryClass;

import com.sgapps.shaharraz.mayday.services.CurrentLocationService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, CountryListAdapter.OnCountrySelectedListener, ListContactsFragment.OnNewContactListener,
        ContactListAdapter.OnUpdateContactListener, ThreeCallsFragment.OnSwipeToRefreshListenerThree,
        OneCallFragment.OnSwipeToRefreshListenerOne {


    private static final int REQUEST_LOCATION = 100;
    private static final int REQUEST_CAMERA = 200;
    private static final int REQUEST_TAKE_PHOTO = 300;
    private static final int REQUEST_GPS = 400;
    public static final String COUNTRY_KEY = "country_key";
    public static final String LAT_SHARED_KEY = "lat_shared_key";
    public static final String LONG_SHARED_KEY = "long_shared_key";
    public static final String SELECT_ON_MAP = "select_on_map";
    public static final String PLACES_SERVICE_INTENT = "places_service_intent";
    public static final String CONTACT_KEY = "contact_key";
    public static final String IS_UPDATE_KEY = "is_update_key";
    public static final String ADDRESS_KEY = "address_key";
    private FloatingActionButton fab;
    private boolean isOn = false;
    private ProgressDialog progressDialog;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private View mainContainer;
    private String mCurrentPhotoPath;
    private Camera cam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContainer = findViewById(R.id.main_container);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {

            //Create Receiver for the CurrentLocationService
            GetData receiver = new GetData();
            IntentFilter filter = new IntentFilter(CurrentLocationService.ACTION_GET_DATA);
            LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

            //Progress Dialog
            progressDialog = new ProgressDialog(this);
            progressDialog.setIcon(R.drawable.ic_logo);
            progressDialog.setTitle(getResources().getString(R.string.loading));
            progressDialog.show();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Get the current location
        getCurrentLocation();

    }

    @Override
    public void onBackPressed() {
        //Set the FloatingActionButton , ToolBar and the DrawerLayout to the default settings
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        fab.show();
        fab.setImageResource(R.drawable.ic_location_white);
        fab.setOnClickListener(this);
        toolbar.setTitle(R.string.app_name);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        //FloatingActionButton onClick method
        //Replace to MapFragment
        //Lock the drawer
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new MapFragment(), MapFragment.TAG)
                .addToBackStack(MapFragment.TAG)
                .commitAllowingStateLoss();
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


    }

    //***************************** Drawer Menu **************************************//

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_flashlight:
                //Turn On/Off Torch
                if (!isOn) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setTurnOnFlashLightMarsh(this);
                    } else {
                        setTurnOnFlashLight();
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setTurnOffFlashLightMarsh(this);
                    } else {
                        setTurnOffFlashLight();
                    }
                }

                break;

            case R.id.nav_camera:
                //Intent to the Camera App and save the picture in external storage
                if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showCameraPreview();
                } else {
                    askForPermission(Manifest.permission.CAMERA, R.string.camera_message, REQUEST_CAMERA);
                }

                break;

            case R.id.nav_contacts:
                //Replace to ListContactsFragment
                //Lock the drawer
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new ListContactsFragment(), ListContactsFragment.TAG)
                        .addToBackStack(ListContactsFragment.TAG)
                        .commitAllowingStateLoss();
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                break;

            case R.id.nav_list_countries:
                //Replace to ListCountriesFragment
                //Lock the drawer
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, new ListCountriesFragment(), ListCountriesFragment.TAG)
                        .addToBackStack(ListCountriesFragment.TAG)
                        .commitAllowingStateLoss();
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

                break;

            case R.id.nav_send_mail:
                //Intent to open email apps and send mail to the owners
                String address = "sg.apps2016@gmail.com";
                String subject = "Sent from my MayDay App";
                String chooserTitle = getResources().getString(R.string.choose_app);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + address));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                startActivity(Intent.createChooser(emailIntent, chooserTitle));
                break;


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //***************************** END ----- Drawer Menu **************************************//

    //********************************* Current Location Method ********************************//
    //Check if the GPS is enable
    //Disable --> Alert Dialog with a message and intent to settings
    //Enable --> Get the current Location - check the permission and then start the service.

    private void getCurrentLocation() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Intent in = new Intent(this, CurrentLocationService.class);
                startService(in);
            } else {
                askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, R.string.location_message, REQUEST_LOCATION);
            }
        } else {
            progressDialog.dismiss();
            buildAlertMessageNoGps();

        }

    }
    //*********************************END ---- Current Location Method ********************************//

    //*********************Alert Dialog - GPS DISABLE ************************************//

    //In case that the Gps is disable -
    // open the Alert dialog with the option to go to the device settings and  turn on the location

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.gps_title);
        builder.setMessage(R.string.gps_msg)
                .setCancelable(false)
                .setPositiveButton(R.string.gps_setting, new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), REQUEST_GPS);
                    }
                })
                .setNegativeButton(R.string.gps_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        Snackbar.make(mainContainer, R.string.gps_error, Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    //*********************END ---- Alert Dialog - GPS DISABLE ************************************//


    //********************* Marshmallow Permissions ************************************//

    //Check if there is a permission
    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    //Ask and explain
    public void askForPermission(final String permission, int message, final int requestCode) {
        if (shouldShowRequestPermissionRationale(permission)) {
            progressDialog.dismiss();
            Snackbar.make(mainContainer, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestPermissions(new String[]{permission}, requestCode);
                        }
                    }).show();

        } else {
            requestPermissions(new String[]{permission}, requestCode);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            // The location permission
            case REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent in = new Intent(this, CurrentLocationService.class);
                    startService(in);
                } else {
                    Snackbar.make(mainContainer, R.string.permission_not_granted, Snackbar.LENGTH_LONG).show();
                }

                break;
            //The camera permission
            case REQUEST_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCameraPreview();
                } else {
                    Snackbar.make(mainContainer, R.string.permission_not_granted, Snackbar.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //********************* END ----- Marshmallow Permissions ******************************//


    //******************* Camera - take a picture and save it methods ***********************//

    private void showCameraPreview() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imageFile = null;
        try {
            imageFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "MAYDAY_" + timeStamp + "_";
        File imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "_" + fileName + ".jpg");
        mCurrentPhotoPath = "file:" + imageFile.getAbsolutePath();
        return imageFile;
    }

    private void refreshGallery(String file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.parse(file));
        sendBroadcast(mediaScanIntent);
    }

    //******************* END --- Camera - take a picture and save it methods ***********************//

    //******************* On ActivityResult -- From camera and From gps settings  ***********************//

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //After taking a picture with the camera
            //Show a snack bar and refresh tje device gallery
            case REQUEST_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Snackbar.make(mainContainer, R.string.image_taken_saved, Snackbar.LENGTH_LONG).show();
                    refreshGallery(mCurrentPhotoPath);

                } else {
                    Snackbar.make(mainContainer, R.string.error_saving_image, Snackbar.LENGTH_LONG).show();
                }

                break;
            //After changing the device settings
            //Show alert dialog and get the current location
            case REQUEST_GPS:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle(getResources().getString(R.string.loading));
                progressDialog.setIcon(R.drawable.ic_logo);
                progressDialog.show();
                getCurrentLocation();

                break;
        }

    }

    //***************** END --- On ActivityResult -- From camera and From gps settings  *********************//


    //******************************** Flashlight method ********************************************//
    //Turn On torch
    //Turn Off torch


    //sdk >= Marshmallow
    private void setTurnOnFlashLightMarsh(Context context) {
        try {

            CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            String[] list = manager.getCameraIdList();
            manager.setTorchMode(list[0], true);
            isOn = true;
        } catch (CameraAccessException cae) {
            Log.e("Flash ON Marsh", cae.getMessage());
            cae.printStackTrace();
        }
    }

    private void setTurnOffFlashLightMarsh(Context context) {
        try {
            CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            manager.setTorchMode(manager.getCameraIdList()[0], false);
            isOn = false;
        } catch (CameraAccessException cae) {
            Log.e("Flash OFF Marsh", cae.getMessage());
            cae.printStackTrace();
        }
    }

    //sdk < Marshmallow
    private void setTurnOnFlashLight() {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam = Camera.open();
                Camera.Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                cam.setParameters(p);
                cam.startPreview();
                isOn = true;
            }
        } catch (Exception e) {
            Log.e("Flash ON", e.getMessage());
            e.printStackTrace();
            Snackbar.make(mainContainer, R.string.mas_no_flashlight, Snackbar.LENGTH_LONG).show();
        }

    }

    private void setTurnOffFlashLight() {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                cam.stopPreview();
                cam.release();
                cam = null;
                isOn = false;
            }
        } catch (Exception e) {
            Log.e("Flash OFF", e.getMessage());
            e.printStackTrace();
            Snackbar.make(mainContainer, R.string.mas_no_flashlight, Snackbar.LENGTH_LONG).show();
        }
    }

    //***************** END ---- Flashlight method *********************************//


    //********************* Fragments Methods - Implementation ******************//


    //Open the progressDialog fragment that shows the telephone numbers of each country
    @Override
    public void countrySelected(CountryClass country) {
        NumbersDialogFragment numbersDialog = new NumbersDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.COUNTRY_KEY, country);
        numbersDialog.setArguments(bundle);
        numbersDialog.show(getSupportFragmentManager(), NumbersDialogFragment.TAG);

    }

    //Replace to AddContactFragment
    @Override
    public void addNewContact() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new AddContactFragment(), AddContactFragment.TAG)
                .addToBackStack(AddContactFragment.TAG)
                .commitAllowingStateLoss();

    }

    //Replace to AddContactFragment
    //Transfer data using Bundle - data - the contact details
    @Override
    public void updateContact(ContactClass contactClass) {
        AddContactFragment addContactFragment = new AddContactFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(MainActivity.CONTACT_KEY, contactClass);
        bundle.putBoolean(MainActivity.IS_UPDATE_KEY, true);
        addContactFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, addContactFragment, AddContactFragment.TAG)
                .addToBackStack(AddContactFragment.TAG)
                .commitAllowingStateLoss();

    }

    //Swipe to refresh
    //get the current location
    @Override
    public void refreshThreeFragment() {
        //Create Receiver for the MyLocationService
        GetData receiver = new GetData();
        IntentFilter filter = new IntentFilter(CurrentLocationService.ACTION_GET_DATA);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.loading));
        progressDialog.setIcon(R.drawable.ic_logo);
        progressDialog.show();

        //Get The Current Location
        //Delay for 2 sec
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCurrentLocation();
            }
        }, 2000);


    }

    //Swipe to refresh
    //get the current location
    @Override
    public void refreshOneFragment() {
        //Create Receiver for the MyLocationService
        GetData receiver = new GetData();
//        IntentFilter filter = new IntentFilter(MyLocationService.ACTION_GET_DATA);
        IntentFilter filter = new IntentFilter(CurrentLocationService.ACTION_GET_DATA);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);

        //Progress Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.loading));
        progressDialog.setIcon(R.drawable.ic_logo);
        progressDialog.show();

        //Get The Current Location
        //Delay for 2 sec
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getCurrentLocation();
            }
        }, 2000);
    }


    //********************* END ------ Fragments Methods Implementation ******************//


    //****************** Receive the BroadCast from the Service *************************************//
    public class GetData extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            CountryClass mCountryClass = intent.getParcelableExtra(MainActivity.COUNTRY_KEY);
            AddressClass addressClass = intent.getParcelableExtra(MainActivity.ADDRESS_KEY);
            if (mCountryClass.getThreeNum() == 1) {
                ThreeCallsFragment fragment = new ThreeCallsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.COUNTRY_KEY, mCountryClass);
                if (addressClass != null) {
                    bundle.putParcelable(MainActivity.ADDRESS_KEY, addressClass);
                }

                if (!isFinishing()) {
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, fragment, ThreeCallsFragment.TAG)
                            .commitAllowingStateLoss();
                }
            } else {
                if (!isFinishing()) {
                    OneCallFragment fragment = new OneCallFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(MainActivity.COUNTRY_KEY, mCountryClass);

                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.main_container, fragment, OneCallFragment.TAG)
                            .commitAllowingStateLoss();
                }
            }
            progressDialog.dismiss();

        }

    }
//****************** END ------ Receive the BroadCast from the Service *************************************//


}


