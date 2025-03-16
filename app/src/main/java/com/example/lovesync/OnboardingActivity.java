package com.example.lovesync;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    private RadioGroup modeGroup;
    private RadioButton modeCouple, modeSingle;
    private Button continueButton, copyCodeButton;
    private TextView inviteCodeText;
    private String inviteCode = "LOVESYNC123"; // Имитация генерации кода
    private boolean isCoupleMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Инициализация элементов
        modeGroup = findViewById(R.id.modeGroup);
        modeCouple = findViewById(R.id.modeCouple);
        modeSingle = findViewById(R.id.modeSingle);
        continueButton = findViewById(R.id.continueButton);
        copyCodeButton = findViewById(R.id.copyCodeButton);
        inviteCodeText = findViewById(R.id.inviteCodeText);

        // Проверяем выбранный режим
        modeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.modeCouple) {
                isCoupleMode = true;
                inviteCodeText.setVisibility(View.VISIBLE);
                copyCodeButton.setVisibility(View.VISIBLE);
            } else {
                isCoupleMode = false;
                inviteCodeText.setVisibility(View.GONE);
                copyCodeButton.setVisibility(View.GONE);
            }
        });

        // Копирование кода в буфер обмена
        copyCodeButton.setOnClickListener(v -> copyInviteCode());

        // Переход на следующий экран
        continueButton.setOnClickListener(v -> {
            if (isCoupleMode) {
                // Если выбрали режим пары, показываем код приглашения
                Toast.makeText(this, "Поделитесь кодом с партнером!", Toast.LENGTH_SHORT).show();
            } else {
                // Если выбрали одиночный режим, переходим в дашборд
                Toast.makeText(this, "Настройка завершена!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> {
                    Intent intent = new Intent(OnboardingActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }, 1500);
            }
        });
    }

    private void copyInviteCode() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Invite Code", inviteCode);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Код скопирован: " + inviteCode, Toast.LENGTH_SHORT).show();
    }
}
