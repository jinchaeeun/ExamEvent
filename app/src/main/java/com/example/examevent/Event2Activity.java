package com.example.examevent;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
/* 입력할 때 올라오는 소프트키 입력 후 없애기 */
public class Event2Activity extends AppCompatActivity {
    // Member Variable ----------------------------------
    private final Boolean      D   = true;
    private final String       TAG = "Event2";

    private Button             okBTN;
    private EditText           nameETXT;

    private InputMethodManager  imm;        //소프트키보드 숨기는거

    // Member Method - Activity's override --------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 화면 설정
        setContentView(R.layout.event2_main);

        // 초기화
        init();

        if(D) Log.d(TAG, "onCreate");   //log.d는 디버깅
    }

    // Member Method - Custom ----------------------------
    // 초기화 메서드
    private void init(){
        //System 서비스 객체 가져오기
        imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE); //imm = this.getSystemService(INPUT_METHOD_SERVICE)라고 입력 후 오류 뜨는거 캐스팅해주기
        
        // View 객체 가져오기
        okBTN = findViewById(R.id.okBTN);
        nameETXT = findViewById(R.id.nameETXT);

        // View 이벤트 리스너 설정
        //소프트 키보드 안을 Editor라고하고 만약 글자 하나하나 눌러졌을 때 들어오는걸하고싶으면 keyboardWatcher가 있음
        nameETXT.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // android developer 사이트에 EditorInfo 검색
                Log.d(TAG, "onEditorAction() actionID :  " + actionId ); // event.getAction()하면 getAction을 null 처리해서 못 받아온다고함. + "event : " + KeyEvent.KEY
                if(actionId == EditorInfo.IME_ACTION_DONE){ //체크 표시뜨는 거 눌렀을 때, 안해줘도 되는데 표시하는 이유는 IME_ACTION_ 종류가 많음. 소프트키보드에 DONE이 아닌 다른 종류가 올 수 있어서
                    imm.hideSoftInputFromWindow(nameETXT.getWindowToken(),0); //flags에 0 주면 안보임
                }
                return true;
            }
        });
    }

    // Member Method - XML onClick Method ----------------
    public void click(View v){
        Log.i(TAG, "okBTN Click");
        // Softkeyboard 숨기기 설정
        // inputMethodManager라고 android developer 사이트에 검색하면 표시 약어 IME
        imm.hideSoftInputFromWindow(nameETXT.getWindowToken(),0); //flags에 0 주면 안보임
        //getWindowToken()
        //어디서 포커스가 맞춰졌는지 확인하는 것. 현재는 nameETXT를 클릭해서 softkeyboard가 뜬 것인데 다른 에디터나 버튼에서 발생했을 때 나온 소프트키보드의 출처를 말함
    }
}