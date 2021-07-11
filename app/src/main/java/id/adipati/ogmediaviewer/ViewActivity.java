package id.adipati.ogmediaviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Looper;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.Timer;
import java.util.TimerTask;

import id.adipati.ogmediaviewer.databinding.ActivityViewBinding;

public class ViewActivity extends AppCompatActivity {
    private ActivityViewBinding binding;

    private String status;
    private String url;
    private String thumbnail;
    private  Integer count = 0;
    private Integer id;

    private WebView webView;

    private PostsDB postsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView((View) binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.pink_200_dark));

        postsDB = new PostsDB(this);

        status = getIntent().getStringExtra("status");
        if(status.equals("get_post")){
            url = getIntent().getStringExtra("url");
            getPost(url);
        }else if(status.equals("from_id")){
            id = getIntent().getIntExtra("id", -1);
        }
    }

    public class CustomWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            webView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("Evaluation", "postDelayed has been started");

                    String eval = "(function(){" +
                                "var data = {};" +
                                "data.length = document.querySelectorAll('.Yi5aA').length;" +
                                "data.thumbnail = document.querySelectorAll('main article ul li .FFVAD')[0].src;" +
                                "return data;" +
                            "})()";

                    webView.evaluateJavascript(
                            eval,
                            new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String s) {
                                    Log.v("Evaluation", "JSON: " + s);

                                    JSONObject obj = null;
                                    try {
                                        binding.webView.setVisibility(View.GONE);
                                        binding.layoutView.setVisibility(View.VISIBLE);
                                        obj = new JSONObject(s);

                                        thumbnail = obj.getString("thumbnail");
                                        count = obj.getInt("length");

                                        Picasso.get()
                                                .load(thumbnail)
                                                .into(binding.imgThumbnail);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }, 5000);
        }
    }

    public void next(View view){
//
    }

    public void prev(View view){

    }

    public void getPost(String url){
        webView = binding.webView;

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new CustomWebViewClient());
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);

        webView.loadUrl(url);
    }

    public void save(View view){
        postsDB.insertPost(new Post(
                this.url,
                this.thumbnail,
                this.count
        ));
        finish();
    }
}