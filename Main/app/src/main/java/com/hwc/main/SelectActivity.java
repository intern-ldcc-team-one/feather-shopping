package com.hwc.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hwc.cart.CartActivity;
import com.hwc.nfc.NFCSplash;
import com.hwc.paid.PaidActivity;
import com.hwc.shared.LoginSession;
import com.hwc.tagging.TaggingSplash;

import java.util.HashMap;

public class SelectActivity extends Activity {

    public static ProgressDialog progDialog;
    private TextView tvUserName;
    private Button btnLogout;
    private LoginSession loginSession;
    public static boolean flag1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        // LoginSession 정보 가져온다
        loginSession = new LoginSession(getApplicationContext());
        HashMap<String, String> infoListFormPref = loginSession.getPreferencesResultHashMap();
        String userName = infoListFormPref.get("name");

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        tvUserName.setText(userName);

        // Activity 전환 버튼
        Button btnNFCInfo = (Button) findViewById(R.id.btnNFCInfo);
        btnNFCInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(SelectActivity.this, NFCSplash.class);
                startActivity(intent);
            }
        });

        // Activity 전환 버튼
        Button btnTaggingProduct = (Button) findViewById(R.id.btnTagging);
        btnTaggingProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TaggingSplash.class);
                //Intent intent = new Intent(getBaseContext(), TaggingActivity.class);
                startActivity(intent);
            }
        });

        // Activity 전환 버튼
        Button btnCartInfo = (Button) findViewById(R.id.btnCart);
        btnCartInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag1 = true;
                progDialog = new ProgressDialog(SelectActivity.this);
                progDialog.setMessage("로딩중입니다......");
                progDialog.show();
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        // Activity 전환 버튼
        Button btnPaying = (Button) findViewById(R.id.btnPaid);
        btnPaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag1 = true;
                progDialog = new ProgressDialog(SelectActivity.this);
                progDialog.setMessage("로딩중입니다......");
                progDialog.show();
                Intent intent = new Intent(getBaseContext(), PaidActivity.class);
                startActivity(intent);
            }
        });

        // 로그아웃 버튼
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(SelectActivity.this);
                alertDlg.setIcon(R.mipmap.ic_launcher);
                alertDlg.setMessage("로그아웃 하시겠습니까?");
                alertDlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dialog.cancel();
                            }
                        });
                alertDlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                loginSession.clearPreferences(); // 저장된 정보 삭제
                                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                AlertDialog alert = alertDlg.create();
                alert.setTitle("깃털쇼핑_1조");
                alert.show();
            }
        });
    }

    // Progress Dialog onKeyDown 정의
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
                alertDlg.setIcon(R.mipmap.ic_launcher);
                alertDlg.setMessage("종료하시겠습니까?");
                alertDlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dialog.cancel();
                            }
                        });
                alertDlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                System.exit(0);
                            }
                        });
                AlertDialog alert = alertDlg.create();
                alert.setTitle("깃털쇼핑_1조");
                alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
