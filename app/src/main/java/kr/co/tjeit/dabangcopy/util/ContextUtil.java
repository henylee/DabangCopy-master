package kr.co.tjeit.dabangcopy.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.dabangcopy.data.User;

/**
 * Created by user on 2017-08-22.
 */

// 메쏘드를 실행하는데, context가 재료로 필요한 메쏘드들의 모임.
public class ContextUtil {
//    일반적으로 로그인한 사용자 데이터는 저장.
//    불러오기에 context가 필요함. => contextUtil 내부에 로그인 사용자 데이터.

    public static User loginUser = null;
//    메모된 파일의 이름 preference 을 생성.
    private static final String prefName = "DabangPref";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";
    private static final String USER_COUNT = "USER_COUNT";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_PROVIDER = "USER_PROVIDER";
    private static final String USER_PROFILE_URL = "USER_PROFILE_URL";
    private static final String USER_PHONE_NUM = "USER_PHONE_NUM";

//    getter/setter는 public으로 열자.

    public static void setUserPhoneNum(Context context, String userPhonenumStr) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        pref.edit().putString(USER_PHONE_NUM, userPhonenumStr).commit();
    }

    public static String getUserPhoneNum(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        return pref.getString(USER_PHONE_NUM, "");
    }

    public static void SetAutoLogin(Context context, boolean isAutoLogin) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        pref.edit().putBoolean(AUTO_LOGIN, isAutoLogin).commit();
    }

    public static boolean getAutoLogin(Context cotext) {
        SharedPreferences pref = cotext.getSharedPreferences(prefName, cotext.MODE_PRIVATE);
        boolean autoLogin = pref.getBoolean(AUTO_LOGIN, false);
        return autoLogin;
    }

    public static void setUserName(Context context, String userIdStr) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        pref.edit().putString(USER_ID, userIdStr).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        String userIdStr = pref.getString(USER_ID, "");
        return userIdStr;
    }

    public static void setEditId(Context context, String editIdTxt) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        pref.edit().putString(USER_NAME, editIdTxt).commit();
    }

    public static String getEditId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        return pref.getString(USER_NAME, "");
    }

    public static void setLoginUser(Context context, String name, String phoheNum, String Id, String profileURL) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        pref.edit().putString(USER_NAME, name);
        pref.edit().putString(USER_PHONE_NUM, phoheNum);
        pref.edit().putString(USER_ID, Id);
        pref.edit().putString(USER_PROFILE_URL, profileURL);

        loginUser = new User();
    }

    public static User getLoginUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, context.MODE_PRIVATE);
        if (loginUser!=null) {
            loginUser.setName(pref.getString(USER_NAME, ""));
            loginUser.setPhoneNum(pref.getString(USER_PHONE_NUM, ""));
            loginUser.setLoginId(pref.getString(USER_ID,""));
            loginUser.setProfileImageURL(pref.getString(USER_PROFILE_URL,""));
        }
        return loginUser;
    }

    public static void logoutProcess() {
        loginUser =null;
    }

}
