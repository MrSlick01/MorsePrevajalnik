package com.example.morseprevajalnik1;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] morseCrke = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", ".-.", "...", "-", "..-", "...-", "--..", "-..-", "-.--", "--.-", ".--", "//", "-----", ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", ".-.-.-", "..--..", "--..--", "-.-.--", "-....-", "-.--.", "-.--.-", "---...", "-.-.-.", "-...-", ".-.-.", "..--.-", ".--.-.", "-..-."};
    String[] tekstCrke = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "v", "z", "x", "y", "q", "w", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "?", ",", "!", "-", "(", ")", ":", ";", "=", "+", "_", "@", "/"};

    EditText Input;
    TextView prevodText;
    ImageButton gumb_prevodM;
    ImageButton gumb_prevodT;
    Button gumb_kop;
    Button gumb_br;
    Button gumb_menjaj;
    Button gumb_helpMain;
    ImageButton gumb_help;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Input = findViewById(R.id.theMainText);
        prevodText = findViewById(R.id.textView2);
        gumb_prevodM = findViewById(R.id.buttonM);
        gumb_prevodT = findViewById(R.id.buttonT);
        gumb_kop = findViewById(R.id.buttonkopiraj);
        gumb_br = findViewById(R.id.buttonbrisi);
        gumb_menjaj = findViewById(R.id.button_menjaj);
        gumb_help = findViewById(R.id.button_help);
        gumb_helpMain = findViewById(R.id.button_helpMain);


        //gumb Menjaj
        gumb_menjaj.setOnClickListener(v -> {
            String zg_vr = Input.getText().toString();
            String sp_vr = prevodText.getText().toString();

            prevodText.setText(zg_vr);
            Input.setText(sp_vr);
        });

        //gumba Kodiraj in Dekodiraj
           //po kliku na gumb za kodiranje izvede metodo MorsePrevod
        gumb_prevodM.setOnClickListener(view -> {
            prevodText.setText(MorsePrevod(Input.getText().toString()));
        });

           //po kliku na gumb za dekodiranje izvede metodo TextPrevod
        gumb_prevodT.setOnClickListener(view -> {
            prevodText.setText(TextPrevod(Input.getText().toString()));
        });


        //gumb Kopiraj
        gumb_kop.setOnClickListener(v -> {
            String vred = prevodText.getText().toString();
            String prosnja = getResources().getString(R.string.prosnja);
            String vsebina = getResources().getString(R.string.vsebina);
            String kopirano = getResources().getString(R.string.kopirano);
            if (vred.isEmpty()) {
                Toast.makeText(getApplicationContext(), prosnja, Toast.LENGTH_SHORT).show();
            } else {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText(vsebina, vred);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), kopirano, Toast.LENGTH_SHORT).show();

            }
        });
        //gumb Brisi
        gumb_br.setOnClickListener(v -> {
            String bes = Input.getText().toString();
            String prazno = getResources().getString(R.string.prazno);
            if(bes.isEmpty()){
                Toast.makeText(getApplicationContext(), prazno,Toast.LENGTH_SHORT).show();
            } else {
                Input.setText("");
                prevodText.setText("");
            }
        });

        //gumb za pomoč
        gumb_help.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, popup.class)));

        //gumb za pomoč
        gumb_helpMain.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, popup2.class)));
    }



    //prevajanje v besedilo
    private String TextPrevod(String str) {
        String textSifrirano = "";
        String[] crke = str.split(" ");

        for (String crka : crke) {

            for (int j = 0; j < morseCrke.length; j++) {
                if (crka.equalsIgnoreCase(morseCrke[j])) {
                    textSifrirano = textSifrirano.concat(tekstCrke[j]);
                }
            }
        }
      return textSifrirano;
    }


    //prevajanje v Morse
    private String MorsePrevod(String str) {
        String morseSifrirano = "";
        String[] crke = str.split("");

        for (String crka : crke) {
            for (int j = 0; j < tekstCrke.length; j++) {
                if (crka.equalsIgnoreCase(tekstCrke[j])) {
                    morseSifrirano = morseSifrirano.concat(morseCrke[j]).concat(" ");
                }
                else if(crka.equals("č")){
                    morseSifrirano = morseSifrirano.concat("-.-.").concat(" ");
                    break;
                } else if(crka.equals("š")){
                    morseSifrirano = morseSifrirano.concat("...").concat(" ");
                    break;
                } else if(crka.equals("ž")){
                    morseSifrirano = morseSifrirano.concat("--..").concat(" ");
                    break;
                }
            }
        }

        return morseSifrirano;
    }


}
