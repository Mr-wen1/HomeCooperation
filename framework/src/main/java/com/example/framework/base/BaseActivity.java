package com.example.framework.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @filename: com.example.framework.baseui
 * @author: 60347
 * @description: 权限
 * @time: 2020/5/20 0:50
 */
public class BaseActivity extends AppCompatActivity {
    //申请运行时权限的Code
    private static final int PERMISSION_REQUEST_CODE = 1000;
    //申请窗口权限的Code
    public static final int PERMISSION_WINDOW_REQUEST_CODE = 1001;

    private String[] mPermissions = {Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION};

    //保存没有同意的权限
    private List<String> mPerList = new ArrayList<>();

    //保存没有同意的失败权限
    private List<String> mPerNoList = new ArrayList<>();

    private OnPermissionsResult onPermissionsResult;

    /**
     * 一个方法请求权限
     *
     * @param permissionsResult
     */
    protected void requset(OnPermissionsResult permissionsResult) {
        if (!checkPermissionAll()) {
            requestPermissionsAll(permissionsResult);
        }
    }

    protected void requestPermissionsAll(OnPermissionsResult permissionsResult) {

    }

    /**
     * 判断单个权限
     *
     * @param permission
     * @return
     */
    protected boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int check = checkSelfPermission(permission);
            return check == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    /**
     * 判断所有权限
     * @return
     */
    protected boolean checkPermissionAll() {
        mPerList.clear();
        for (int i = 0; i < mPermissions.length; i++) {
            Boolean check = checkPermission(mPermissions[i]);
            if (!check) {
                mPerList.add(mPermissions[i]);
            }
        }
        return mPerList.size() > 0 ? false : true;
    }

    /**
     * 请求权限
     * @param mPermission
     */
    protected void requestPermission(String[] mPermission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requestPermissions(mPermissions, PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * 申请所有权限
     * @param onPermissionsResult
     */
    protected void requestPermissionAll(OnPermissionsResult onPermissionsResult) {
        this.onPermissionsResult = onPermissionsResult;
        requestPermission((String[]) mPerList.toArray(new String[mPerList.size()]));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPerNoList.clear();
        if (requestCode == PERMISSION_REQUEST_CODE){
            if (grantResults.length>0){
                for (int i = 0; i < grantResults.length; i++){
                    if (grantResults[i] ==PackageManager.PERMISSION_DENIED){
                        mPerNoList.add(permissions[i]);
                    }
                }
            }
            if (onPermissionsResult != null){
                if (mPerNoList.size() == 0){
                    onPermissionsResult.OnSuccess();
                }else {
                    onPermissionsResult.OnFail(mPerNoList);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected interface OnPermissionsResult {
        void OnSuccess();

        void OnFail(List<String> noPermissions);
    }

    /**
     * 判断窗口权限
     *
     * @return
     */
    protected boolean checkWindowPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Settings.canDrawOverlays(this);
        }
        return true;
    }

    /**
     * 请求窗口权限
     */
    protected void requestWindowPermissions() {
        Toast.makeText(this, "申请窗口权限，暂时没做UI交互", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION
                , Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, PERMISSION_WINDOW_REQUEST_CODE);
    }
}
