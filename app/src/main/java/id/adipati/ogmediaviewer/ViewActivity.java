package id.adipati.ogmediaviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.adipati.ogmediaviewer.databinding.ActivityViewBinding;

public class ViewActivity extends AppCompatActivity {
    private ActivityViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
    }
}