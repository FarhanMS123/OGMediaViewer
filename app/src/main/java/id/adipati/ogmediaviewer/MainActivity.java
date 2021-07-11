package id.adipati.ogmediaviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import id.adipati.ogmediaviewer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private PostsDB postsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView((View) binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.pink_200_dark));

        postsDB = new PostsDB(this);
        postsDB.openDB();
    }

    public void getPost(View view){
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("status", "get_post");
        intent.putExtra("url", binding.txtUrl.getText().toString());
        startActivity(intent);
    }
}