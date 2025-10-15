package td.adhet.app;

import com.getcapacitor.BridgeActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends BridgeActivity {

  // Domaine autorisé à rester dans la WebView (navigation interne)
  private static final String INTERNAL_DOMAIN = "adhet-tchad.org";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Configure un WebViewClient personnalisé pour:
    // - ouvrir les liens externes dans le navigateur système
    // - afficher une page locale hors-ligne en cas d'erreur réseau
    WebView webView = getBridge().getWebView();
    if (webView != null) {
      webView.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
          Uri uri = request.getUrl();
          String host = uri.getHost() != null ? uri.getHost() : "";
          if (host.endsWith(INTERNAL_DOMAIN)) {
            // Lien interne -> rester dans la WebView
            return false;
          }
          // Lien externe -> ouvrir dans le navigateur
          Intent intent = new Intent(Intent.ACTION_VIEW, uri);
          startActivity(intent);
          return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
          // En cas d'erreur réseau, afficher une page offline locale
          view.loadUrl("file:///android_asset/public/offline.html");
        }
      });
    }
  }

  @Override
  public void onBackPressed() {
    // Gestion du bouton retour: revenir en arrière dans l'historique WebView si possible
    WebView webView = getBridge().getWebView();
    if (webView != null && webView.canGoBack()) {
      webView.goBack();
    } else {
      super.onBackPressed();
    }
  }
}
