package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kr.co.tjeit.dabangcopy.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdit;
    private Button loginbtn;
    private com.facebook.login.widget.LoginButton loginbutton;
    private android.widget.CheckBox autoLoginChk;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("kr.co.tjeit.dabangcopy", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
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

        callbackManager = CallbackManager.Factory.create();
        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setupEvents() {
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);
                ContextUtil.setLoginUser(mContext, "A사용자", "011-222-333", idEdit.getText().toString(), "tempURL");
                startActivity(intent);
                finish();
            }
        });

        autoLoginChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContextUtil.SetAutoLogin(mContext, isChecked);
            }
        });
    }

    @Override
    public void setValues() {
        boolean autoLogin = ContextUtil.getAutoLogin(mContext);
        autoLoginChk.setChecked(autoLogin);
        idEdit.setText(ContextUtil.getUserName(mContext));
    }

    @Override
    public void bindViews() {
        this.loginbutton = (LoginButton) findViewById(R.id.login_button);
        this.loginbtn = (Button) findViewById(R.id.loginbtn);
        this.autoLoginChk = (CheckBox) findViewById(R.id.autoLoginChk);
        this.idEdit = (EditText) findViewById(R.id.idEdit);
    }
}
