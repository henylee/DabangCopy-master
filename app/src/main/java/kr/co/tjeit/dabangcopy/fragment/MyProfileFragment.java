package kr.co.tjeit.dabangcopy.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.dabangcopy.MyProfileSettingActivity;
import kr.co.tjeit.dabangcopy.R;

/**
 * Created by the on 2017-08-25.
 */

public class MyProfileFragment extends Fragment {

    private CircleImageView profileImage;
    private TextView telephoneLayout;
    private android.widget.LinearLayout myInfoLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_my_profile, container, false);
        this.myInfoLayout = (LinearLayout) v.findViewById(R.id.myInfoLayout);
        this.telephoneLayout = (TextView) v.findViewById(R.id.telephoneLayout);
        this.profileImage = (CircleImageView) v.findViewById(R.id.profileImage);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    private void setupEvents() {
        telephoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:070-4211-3951"));
                startActivity(intent);
            }
        });
        myInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyProfileSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setValues() {

    }

    private void bindViews() {

    }

}
