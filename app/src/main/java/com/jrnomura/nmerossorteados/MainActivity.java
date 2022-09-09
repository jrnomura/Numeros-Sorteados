package com.jrnomura.nmerossorteados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jrnomura.nmerossorteados.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        binding.btSorteio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Ninicio = binding.numInicial.getText().toString();
                String Nfinal = binding.numFinal.getText().toString();

                if (Ninicio.isEmpty() || Nfinal.isEmpty()){
                    binding.txtResultSorteio.setText("Digite número inicial e final");
                }else{
                    sorteioNumero();
                }
            }
        });
    }

    private void sorteioNumero() {
        String inic = binding.numInicial.getText().toString();
        String fin = binding.numFinal.getText().toString();

        int min = Integer.parseInt(inic);
        int max = Integer.parseInt(fin);

        if (max > min) {
            Random novoNumero = new Random();
            int sorteio = novoNumero.nextInt((max - min) + 1) + min;
            String numSorteio = String.valueOf(sorteio);
            binding.txtResultSorteio.setText(numSorteio);
        }else{
            binding.txtResultSorteio.setText("Digite número inicial e final");
        }
    }
}