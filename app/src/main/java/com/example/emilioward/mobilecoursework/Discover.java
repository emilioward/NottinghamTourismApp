package com.example.emilioward.mobilecoursework;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;

/**
 * Created by emilioward on 04/12/2015.
 */
public class Discover extends AppCompatActivity {
        private static final int PLACE_PICKER_REQUEST = 1;
        private TextView mName;
        private TextView mAddress;
        private TextView mPhoneNum;
        private TextView mPlaceType;
        private TextView mPrice;
        private TextView mRating;
        private TextView mURL;
        private WebView mViewPort;

        public static GoogleApiClient mGoogleApiClient;

        //private TextView mURL;
        private TextView mAttributions;
       // private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
                //new LatLng(52.95487, -1.15811), new LatLng(52.95487, -1.15811));

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.discover);
            mName = (TextView) findViewById(R.id.textView);
            mAddress = (TextView) findViewById(R.id.textView2);
            mPhoneNum = (TextView) findViewById(R.id.textView4);
            //mPlaceType = (TextView) findViewById(R.id.textView17);

            //mPrice = (TextView) findViewById(R.id.tvPrice);
            //mRating = (TextView) findViewById(R.id.textView17);
            mURL = (TextView) findViewById(R.id.tvUrl);
            //mViewPort = (WebView) findViewById(R.id.Viewport);
            mAttributions = (TextView) findViewById(R.id.textView3);
            Button pickerButton = (Button) findViewById(R.id.pickerButton);

            pickerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        PlacePicker.IntentBuilder intentBuilder =
                                new PlacePicker.IntentBuilder();
                        //intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
                        Intent intent = intentBuilder.build(Discover.this);
                        startActivityForResult(intent, PLACE_PICKER_REQUEST);

                    } catch (GooglePlayServicesRepairableException
                            | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            });
            pickerButton.performClick();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item=menu.add("Home"); //your desired title here
        item.setIcon(R.drawable.ic_home_white_48dp); //your desired icon here
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(Discover.this, Main.class);
                startActivity(i);
                return false;
            }
        });
        return true;
    }

        private void startActivityAfterCleanup(Class<?> cls) {
            //if (projectsDao != null) projectsDao.close();
            Intent intent = new Intent(getApplicationContext(), cls);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        @Override
        protected void onActivityResult(int requestCode,
                                        int resultCode, Intent data) {

            if (requestCode == PLACE_PICKER_REQUEST
                    && resultCode == Activity.RESULT_OK) {

                final Place place = PlacePicker.getPlace(this, data);
                final CharSequence name = place.getName();
                final CharSequence address = place.getAddress();
                final CharSequence phoneNumber = place.getPhoneNumber();
                final Uri PlaceUrl = place.getWebsiteUri();
                //final List<Integer> types = place.getPlaceTypes();
                //final float Pricelvl = place.getPriceLevel();
                final float Rating = place.getRating();
                //final LatLngBounds ViewPort= place.getViewport();
                String attributions = (String) place.getAttributions();
                if (attributions == null) {
                    attributions = "";
                }


                /*
                //photo function here
                PlacePhotoMetadataResult imgresult = Places.GeoDataApi.getPlacePhotos(mGoogleApiClient, placeId).await();

                if (imgresult!=null && imgresult.getStatus().isSuccess()) {
                    PlacePhotoMetadataBuffer photoMetadataBuffer = imgresult.getPhotoMetadata();
                    //if (photoMetadataBuffer.getCount() > 0) {
                        // Get the first bitmap and its attributions.
                        PlacePhotoMetadata photo = photoMetadataBuffer.get(0);
                        CharSequence attribution = photo.getAttributions();
                        // Load a scaled bitmap for this photo.
                        Bitmap image = photo.getScaledPhoto(mGoogleApiClient, mWidth, mHeight).await()
                                .getBitmap();
                        mImage.setImageBitmap(image);
                        //attributedPhoto = new AttributedPhoto(attribution, image);
                   // }
                    // Release the PlacePhotoMetadataBuffer.
                    photoMetadataBuffer.release();
                }
                */

                mName.setText(name);
                mAddress.setText(address);
                mPhoneNum.setText(phoneNumber);
                //mPlaceType.setText(""+types);
                //mPrice.(Pricelvl);
                //mRating.setText(""+Rating);
                mURL.setText(""+PlaceUrl);

                mAttributions.setText(Html.fromHtml(attributions));

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }

