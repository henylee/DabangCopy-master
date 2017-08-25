package kr.co.tjeit.dabangcopy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileSettingActivity extends BaseActivity {

    final int REQ_FOR_CAMERA = 1;
    final int REQ_FOR_GALLEY = 2;
    private Button profileImageBtn;
    private Button logoutBtn;
    private CircleImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_setting);

        bindViews();
        setValues();
        setupEvents();
    }

    @Override
    public void setupEvents() {

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder logout = new AlertDialog.Builder(mContext);
                logout.setTitle("로그아웃");

                logout.setMessage("로그아웃 하시겠습니까?").setCancelable(false);
                logout.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                logout.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = logout.create();
                alert.show();
            }
        });

        profileImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder profileImg = new AlertDialog.Builder(mContext);
                final CharSequence[] imgItem = new CharSequence[]{"사진찍기", "갤러리에서 가져오기", "사진삭제", "취소"};

                profileImg.setTitle("이미지 변경");
                profileImg.setItems(imgItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQ_FOR_CAMERA);
                        } else if (which == 1) {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, REQ_FOR_GALLEY);
                        }
                    }
                });
                profileImg.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap profileBitmap = (Bitmap) extras.get("data");
                profileImage.setImageBitmap(profileBitmap);
            }
        } else if (requestCode == REQ_FOR_GALLEY) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
//                try : 한번 시도해봐. try 내부는 언제 에러가 터질지 모르는 부분.(예외발생 가능지점).
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    profileImage.setImageBitmap(bitmap);
//                    예외가 실제로 발생하면 대처하는 부분 : catch
//                    앱이 죽지 않고 실행상태를 유지하도록 대체하는 부분.
                } catch (IOException e) {
//                    어떤 예외가 발생햇는지 로그로 기록.
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.profileImageBtn = (Button) findViewById(R.id.profileImageBtn);
        this.profileImage = (CircleImageView) findViewById(R.id.profileImage);
    }
}
