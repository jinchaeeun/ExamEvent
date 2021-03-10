package com.example.examevent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Member Variable ----------------------------------
    private final Boolean   D   = true;
    private final String    TAG = "ExamEvent";

    private Button          leftBTN;
    private TextView        msgTXT;
    private CheckBox        checkBox;
    private RadioGroup      radioGroup;
    private RadioButton     redRBTN, blueRBTN;
    private ConstraintLayout          conLAY;

    // Member Method - Activity's override --------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 화면 설정
        setContentView(R.layout.activity_main);

        // 초기화
        init();

        if(D) Log.d(TAG, "onCreate");   //log.d는 디버깅
    }

    // Member Method - Custom ----------------------------
    // 초기화 메서드
    private void init(){
        // View 객체 가져오기
        leftBTN = findViewById(R.id.leftBTN);
        msgTXT = findViewById(R.id.msgTXT);
        checkBox = findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.radioGroup);
        redRBTN = findViewById(R.id.redRBTN);
        blueRBTN = findViewById(R.id.blueRBTN);
        conLAY = findViewById(R.id.conLAY);


        // View 이벤트 리스너 설정
        leftBTN.setOnClickListener(new View.OnClickListener() { //()안에 OnClickListener의 객체를 생성하라고 나옴. 객체 생성은 new
            @Override
            public void onClick(View v) {
                Log.i(TAG, "leftBTN - onClick()");
            }
        });

        msgTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "msgTXT - onClick()");
            }
        });
        //leftBTN.setOnLongClickListener가 발생하는지 안하는지 듣고 있다가 (new View.OnLongClickListener(){}에 전달해줌
        leftBTN.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "leftBTN - Long~ Long~ Click");
                return true;   //return true 처리하면 한번만 클릭됨 이벤트 핸들러가 처리했다는 것임
            }
        });

        //체크박스의 check 여부에 따른 동작
        //체크 이벤트가 발생하는 !순간! 무언가 하고싶을 때
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "checkbox - CheckedChange"+isChecked);
                conLAY.setBackgroundColor((isChecked) ? Color.CYAN : Color.WHITE);

            }
        });
        /*
        //체크되어있는지 안되어있는지에 따라서 다른 일을 하려할 때
        checkBox.isChecked();
        //강제 체크
        checkBox.setChecked(true);
        */
        //변경하는 순간에하고싶을 땐 Listener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Log.i(TAG, "radioGroup - checkedId : " + checkedId);    //찍히는건 리소스ID
                Log.i(TAG, "radioGroup - checkedId : " + ((RadioButton) findViewById(checkedId)).getText()); //findView는 View를 반환하니 앞에 RadioButton이라고 명시
            }
        });
        //라디오 버튼만 해보기
        redRBTN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "radioButton - CheckedChange"+buttonView.getText());

            }
        });





    }
}