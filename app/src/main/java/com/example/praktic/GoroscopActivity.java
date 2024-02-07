package com.example.praktic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class GoroscopActivity extends AppCompatActivity {

    private Spinner spinnerZadiak;//переменные
    private TextView textViewHoroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goroscop);
        spinnerZadiak = findViewById(R.id.spinnerZadiak);//ссылки на переменные
        textViewHoroscope = findViewById(R.id.textViewHoroscope);
    }

    public void onClickExit(View view) {//Кнопка переход на главную страницу
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void DiscoverHoroscope(View view) {//Кнопка узнать прездскозание
         int postion = spinnerZadiak.getSelectedItemPosition();//номер позиции
         String goroscop = getGoroscopeByPosition(postion);
        textViewHoroscope.setText(goroscop);//присваеваем к текст к TextView
    }

    private String getGoroscopeByPosition(int position){//возвращает индекс выбраного варианта в раскрывающимся списке
        String[] goroscope = getResources().getStringArray(R.array.Character_description);
        return goroscope[position];
    }

    public void onClickShare(View view) {//отправка предсказания друзьям
        String msg = textViewHoroscope.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");//описание типа данных
        intent.putExtra(Intent.EXTRA_TEXT, msg);//получаем данные из интента
        startActivity(intent);
    }
}