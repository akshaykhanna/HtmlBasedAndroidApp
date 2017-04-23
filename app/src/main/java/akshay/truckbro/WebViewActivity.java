package akshay.truckbro;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity implements View.OnLongClickListener {

    WebView view;
    String coordDest="28.6506, 77.2303"; // chandni chowk
    String coordSource="18.6273° N, 73.8041"; //ginjer hotel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        view = new WebView(this);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setDomStorageEnabled(true);
        // Below required for geolocation
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setGeolocationEnabled(true);
        view.getSettings().setAllowContentAccess(true);
        //view.getSettings().setAllowFileAccessFromFileURLs(true);
        view.setWebChromeClient(new GeoWebChromeClient());
        view.setWebViewClient(new GeoWebViewClient());
        //view.loadUrl("file:///android_asset/index.html");
       view.loadUrl("file:///android_asset/web/index.html");

        //for calling Java func from JS
        JavaScriptInterface jsInterface = new JavaScriptInterface(this);
        view.getSettings().setJavaScriptEnabled(true);
        view.addJavascriptInterface(jsInterface, "JSInterface");
                                                            //dest                         source     curr

        //view.loadUrl("https://www.google.co.in/maps/dir/"+coordDest+"/"+coordSource+"/@18.5684931,73.7204923,12z");
        setContentView(view);
       // view.setOnLongClickListener(this);
    }
    public class JavaScriptInterface {
        private Activity activity;

        public JavaScriptInterface(Activity activiy) {
            this.activity = activiy;
        }
        @JavascriptInterface
        public void startVideo(){
            //Toast.makeText(activity,"Open Nav !!!!",Toast.LENGTH_LONG).show();

            Uri gmmIntentUri = Uri.parse("google.navigation:q="+coordSource);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            activity.startActivity(mapIntent);
        }
    }
    @Override
    public boolean onLongClick(View v)
    {
        Toast.makeText(this,"Long click !!!!",Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+coordDest+"&daddr="+coordSource));
        /*
        String geoUri = "http://maps.google.com/maps?q=loc:" + coordDest;//lat + "," + lng + " (" + mTitle + ")";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(geoUri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);*/
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+coordSource);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
        return false;
    }

    public class GeoWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // When user clicks a hyperlink, load in the existing WebView
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * WebChromeClient subclass handles UI-related calls
     * Note: think chrome as in decoration, not the Chrome browser
     */
    public class GeoWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            // Always grant permission since the app itself requires location
            // permission and the user has therefore already granted it
            callback.invoke(origin, true, false);
        }
    }
    @Override
    public void onBackPressed() {
        // Pop the browser back stack or exit the activity
        if (view.canGoBack()) {
            view.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
    /**
     * Method to display the location on UI
     * */

}
