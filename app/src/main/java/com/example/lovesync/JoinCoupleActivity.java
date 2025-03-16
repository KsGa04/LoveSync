package com.example.lovesync;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JoinCoupleActivity extends AppCompatActivity {

    private EditText inviteCodeInput;
    private Button joinButton, backButton;
    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_couple);

        // Инициализация элементов
        inviteCodeInput = findViewById(R.id.inviteCodeInput);
        joinButton = findViewById(R.id.joinButton);
        backButton = findViewById(R.id.backButton);
        errorMessage = findViewById(R.id.errorMessage);

        // Кнопка "Присоединиться"
        joinButton.setOnClickListener(v -> handleJoin());

        // Кнопка "Назад"
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(JoinCoupleActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void handleJoin() {
        String inviteCode = inviteCodeInput.getText().toString().trim();

        if (inviteCode.isEmpty()) {
            showError("Пожалуйста, введите код приглашения");
            return;
        }

        // Симуляция проверки кода (можно заменить API-запросом)
        joinButton.setEnabled(false);
        joinButton.setText("Проверка...");

        new Handler().postDelayed(() -> {
            joinButton.setEnabled(true);
            joinButton.setText("Присоединиться");

            if (inviteCode.equalsIgnoreCase("INVALID")) {
                showError("Неверный код приглашения. Пожалуйста, проверьте и попробуйте снова.");
            } else {
                // Код верный → переход в дашборд
                Toast.makeText(JoinCoupleActivity.this, "Код принят! Добро пожаловать!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JoinCoupleActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void showError(String message) {
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }
}
