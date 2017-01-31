package com.example.tacademy.gpsandmap;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // 내위치
        LatLng myPosition = new LatLng(U.getInstance().getMyLat(),
                U.getInstance().getMyLng());
        // 마킹 ( 지도에다가 point 를 찍음 )
        Marker marker =
        mMap.addMarker(new MarkerOptions().position(myPosition)
                .title("내위치").snippet("자취방"));
        marker.setTag(" 자취방이다!!"); // 서버로부터 받은 샵 정보를 넣는다.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,12));
        // 퍼미션 문제 / 앞에서 퍼미션을 다 받아서 오기때문에 빨간줄이 떠도 된다.
        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.i("GPS", "구글지도상내위치정보 : " + location.getLatitude()+","+location.getLongitude());
                // 구글지도상내위치정보 : 37.4663258,126.9604367
                // 새로운위치정보 : 37.4666047,126.9605299
            }
        });
        // 지도 클릭
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng latLng) {
                Log.i("GPS", "내가 찍은 위치 : "
                        + latLng.latitude
                        + ","
                        + latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title("신규 위치"));
                CameraPosition MARKER_POS = new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(16)
                        .bearing(60)
                        .tilt(30)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(MARKER_POS));
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener(){
            @Override
            public void onMapLongClick(LatLng latLng) {
                Log.i("GPS", "내가 길게 찍은 위치 : "
                        + latLng.latitude
                        + ","
                        + latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(latLng).title("롱규위치"));
                CameraPosition MARKER_POS = new CameraPosition.Builder()
                        .target(latLng)
                        .zoom(12)
                         .bearing(-60)
                        .tilt(30)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(MARKER_POS));
            }
        });
        // 마커 클릭
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i("GPS", "내가 길게 찍은 위치 : "
                        + marker.getPosition().latitude
                        + ","
                        + marker.getPosition().longitude + " / " + marker.getTag().toString());

                Snackbar.make(null, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                return false;
            }
        });
        // 반경 표시
        // 마커 변경
        // 마커 이동
    }
}
