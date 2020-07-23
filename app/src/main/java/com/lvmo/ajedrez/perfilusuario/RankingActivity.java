package com.lvmo.ajedrez.perfilusuario;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lvmo.ajedrez.MainActivity;
import com.lvmo.ajedrez.R;

public class RankingActivity extends AppCompatActivity {
    private TextView etRanking;
   // private  InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        etRanking=findViewById(R.id.textView1_RA);
        etRanking.setText(R.string.SetRanking);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_ranking, new RankingFragment())
                .commit();
    }
    @Override
    public void onBackPressed() {
       // interticalAuncio();
        Intent i=new Intent(RankingActivity.this, MainActivity.class);
        startActivity(i);
    }
/*
    private void interticalAuncio() {
        int n = new Random().nextInt(3);
        if(n==2) {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                    Toast.makeText(TransisionActivity.this, "nose cargo el anuncio", Toast.LENGTH_LONG);
                }
                }
            });
        }
    }
    */
}