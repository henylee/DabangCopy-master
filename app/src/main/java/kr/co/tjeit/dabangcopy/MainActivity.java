package kr.co.tjeit.dabangcopy;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

import kr.co.tjeit.dabangcopy.util.GlobalData;

public class MainActivity extends BaseActivity {

    public static MainActivity activity;


    private LinearLayout homeFragment;
    private LinearLayout myProfileFragment;
    private LinearLayout homeBtn;
    private LinearLayout favoritBtn;
    private LinearLayout mapBtn;
    private LinearLayout profileBtn;
    private LinearLayout mapListFragment;
    private LinearLayout seeMoreFragment;
    private LinearLayout favoriteFragment;
    private LinearLayout seeMoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

//       임시로, 이곳에서 GlobalData에 더미데이터를 채워넣음.
        GlobalData.initGlobalData();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "kr.co.tjeit.dabangcopy",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        bindViews();
        setupEvents();
        setValues();
    }


    @Override
    public void setupEvents() {

        final LinearLayout[] frags = {homeFragment, favoriteFragment, mapListFragment, myProfileFragment, seeMoreFragment};
        final View.OnClickListener tagListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (LinearLayout linearLayout : frags) {
                    linearLayout.setVisibility(View.GONE);
                }
                int index = Integer.parseInt(v.getTag().toString());
                frags[index].setVisibility(View.VISIBLE);
            }
        };
        homeBtn.setOnClickListener(tagListener);
        favoritBtn.setOnClickListener(tagListener);
        mapBtn.setOnClickListener(tagListener);
        profileBtn.setOnClickListener(tagListener);
        seeMoreBtn.setOnClickListener(tagListener);
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.seeMoreBtn = (LinearLayout) findViewById(R.id.seeMoreBtn);
        this.profileBtn = (LinearLayout) findViewById(R.id.profileBtn);
        this.mapBtn = (LinearLayout) findViewById(R.id.mapBtn);
        this.favoritBtn = (LinearLayout) findViewById(R.id.favoritBtn);
        this.homeBtn = (LinearLayout) findViewById(R.id.homeBtn);
        this.seeMoreFragment = (LinearLayout) findViewById(R.id.seeMoreFragment);
        this.mapListFragment = (LinearLayout) findViewById(R.id.mapListFragment);
        this.favoriteFragment = (LinearLayout) findViewById(R.id.favoriteFragment);
        this.myProfileFragment = (LinearLayout) findViewById(R.id.myProfileFragment);
        this.homeFragment = (LinearLayout) findViewById(R.id.homeFragment);
    }
}
