package com.example.examevent;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/*
 * 과제
 * EditText와 RadioButton, CheckBox 입력 후
 * Save 누르면 저장과 EditText 수정 불가,
 * cancel 누르면 입력 값 초기화,
 * modify 누르면 editText 수정 가능한 상태로 돌리기
 * */
public class SettingActivity extends AppCompatActivity {
    // Member Variable ----------------------------------
    private final Boolean   D   = true;
    private final String    TAG = "ExamEvent";

    private EditText        idETXT;
    private RadioGroup      themaRGroup;
    private CheckBox        autoSaveCheck, autoWifiCheck;
    private RadioButton     whiteRBTN, darkRBTN,blueRBTN;
    private Button          modifyBTN, saveBTN, cancelBTN;

    private InputMethodManager imm;        //소프트키보드 숨기는거

    private String checkRadio;


    // Member Method - Activity's override --------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 화면 설정
        setContentView(R.layout.setting_main);

        // 초기화
        init();

        if(D) Log.d(TAG, "onCreate()");
    }

    // Member Method - Custom ----------------------------
    // 초기화 메서드
    private void init(){
        //System 서비스 객체 가져오기
        imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);

        // View 객체 가져오기
        idETXT = findViewById(R.id.idETXT);

        themaRGroup = findViewById(R.id.themaRGroup);
        whiteRBTN = findViewById(R.id.whiteRBTN);
        darkRBTN = findViewById(R.id.darkRBTN);
        blueRBTN = findViewById(R.id.blueRBTN);

        autoSaveCheck = findViewById(R.id.autoSaveCheck);
        autoWifiCheck = findViewById(R.id.autoWifiCheck);



        // View 이벤트 리스너 설정
        //softkeyboard 없애기
        idETXT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){ //softkeyboard에서 파란색 체크 표시 버튼 눌렀을 때
                    imm.hideSoftInputFromWindow(idETXT.getWindowToken(),0); //flags에 0 주면 안보임
                }
                return true;
            }
        });
    }

    public void click(View v){
        //saveBTN, cancelBTN
        switch (v.getId()){
            case R.id.modifyBTN:
                //idETXT 수정 가능한 상태로 돌리기
                idETXT.setClickable(true);
                idETXT.setFocusable(true);
                idETXT.setTextColor(Color.BLACK);
                break;
            case R.id.saveBTN:
                // idETXT 변경 불가하게하기
                idETXT.setClickable(false);
                idETXT.setFocusable(false);
                idETXT.setTextColor(Color.LTGRAY);
                //설정 정보 로그 출력
                Log.i(TAG, "SAVE: "+idETXT.getText() + ",  White: " + whiteRBTN.isChecked() + ",  Dark: " + darkRBTN.isChecked() + ",  Blue: " + blueRBTN.isChecked() + ", "+themaRGroup.getCheckedRadioButtonId()+"  Auto Save Checked: " + autoSaveCheck.isChecked() +", Auto Connect Wifi Checked: " + autoWifiCheck.isChecked());
                break;
            case R.id.cancelBTN:
                // idETXT 지우기, radioGroup 선택 값 X, checkBox 선택 값 X
                idETXT.setText("");
                whiteRBTN.setChecked(false);
                darkRBTN.setChecked(false);
                blueRBTN.setChecked(false);
                autoSaveCheck.setChecked(false);
                autoWifiCheck.setChecked(false);
                break;
        }
    }
}