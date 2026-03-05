# CIFI_TECH (Ionic React + Capacitor)

Application mobile (Android + iOS) qui charge le site distant dans une WebView Capacitor.

- URL distante: `https://www.cifi_tech-tchad.org/wp-admin`
- Nom de l’app: `CIFI_TECH`
- Package ID: `td.cifi_tech.app`
- Plateformes: Android + iOS
- Push: non (désactivé)

## Fonctionnalités

- Navigation interne conservée (domaine `cifi_tech-tchad.org`).
- Liens externes ouverts dans le navigateur système.
- Splash (par défaut). Icônes/splash personnalisables plus tard.
- Bouton retour Android: retour dans l’historique WebView; si pas d’historique, quitte l’app.
- Page hors-ligne locale: `web/offline.html`.
- Accès caméra / galerie / localisation si le site le demande (permissions natives ajoutées).
- Support JS, localStorage, cookies (gérés par WebView Capacitor).

## Structure principale

- `capacitor.config.ts`: configuration Capacitor (URL distante, domaines autorisés, webDir).
- `web/`: assets statiques. La WebView charge l’URL distante, ces fichiers servent principalement pour le fallback offline.
- `android/` et `ios/`: projets natifs générés.
- `android/app/src/main/java/td/cifi_tech/app/MainActivity.java`: interception liens externes + fallback offline + back Android (commenté en FR).
- `android/app/src/main/AndroidManifest.xml`: permissions INTERNET, CAMERA, READ_MEDIA_IMAGES, LOCATION.
- `ios/App/App/AppDelegate.swift`: interception liens externes + fallback offline (commenté en FR).
- `ios/App/App/Info.plist`: descriptions d’usage (caméra, photothèque, localisation).
- `resources/`: mettez ici vos icônes et splash (voir plus bas).

## Installer les prérequis

- Node.js LTS et npm.
- Android: Android Studio (+ SDK/Platform Tools, un émulateur ou un appareil USB avec débogage). Java JDK 17 recommandé.
- iOS: macOS + Xcode (compilation et publication iOS uniquement sur macOS).

## Commandes de base

Dans `c:\windsurfprojets\webviewapp`:

```bash
# Installer dépendances (si besoin)
npm install

# Synchroniser les assets web vers Android/iOS
npx cap sync

# Lancer Android (ouvre Android Studio)
npx cap open android

# Lancer iOS (ouvre Xcode) – à exécuter sur macOS
npx cap open ios
```

Compilation/running se fait depuis Android Studio/Xcode.

## Modifier l’URL distante

- Éditez `capacitor.config.ts`:
```ts
server: {
  url: 'https://www.cifi_tech-tchad.org/wp-admin',
  cleartext: false,
  allowNavigation: ['www.cifi_tech-tchad.org', 'cifi_tech-tchad.org']
}
```
- Puis:
```bash
npx cap sync
```

## Gestion des liens externes

- Les liens dont le domaine ne se termine pas par `cifi_tech-tchad.org` s’ouvrent dans le navigateur système (Android: `Intent.ACTION_VIEW`, iOS: `UIApplication.shared.open`).
- Pour ajouter d’autres domaines internes, ajustez la constante `INTERNAL_DOMAIN`:
  - Android: `MainActivity.java`
  - iOS: `AppDelegate.swift`

## Hors-ligne

- Si la navigation échoue (erreur réseau), l’app affiche `web/offline.html`.
- Pour un meilleur mode hors-ligne, implémentez un Service Worker côté site (PWA) et laissez la WebView en bénéficier.

## Icônes et Splash

1. Placez vos fichiers dans `resources/`:
   - `resources/icon.png` (1024x1024, carré, sans coins arrondis)
   - `resources/splash.png` (2732x2732, zone centrale sans éléments critiques)
2. Option 1 (outil Capacitor):
   ```bash
   npm i -D @capacitor/assets
   npx capacitor-assets generate --android --ios
   ```
3. Option 2 (Android Studio / Xcode) : configurer manuellement les assets.

Après mise à jour des assets:
```bash
npx cap sync
```

## Permissions

- Android (`AndroidManifest.xml`): `INTERNET`, `CAMERA`, `READ_MEDIA_IMAGES` (Android 13+), `ACCESS_COARSE_LOCATION`, `ACCESS_FINE_LOCATION`.
- iOS (`Info.plist`): `NSCameraUsageDescription`, `NSPhotoLibraryUsageDescription`, `NSLocationWhenInUseUsageDescription`.
- L’app demandera dynamiquement les autorisations la première fois que le site sollicite ces fonctionnalités.

## Bouton retour et rafraîchissement

- Android: bouton retour natif géré dans `MainActivity.java`.
- iOS: geste de retour (swipe) activé; pas de bouton physique.
- Un pull-to-refresh natif n’est pas ajouté par défaut; si souhaité, on peut l’implémenter (à discuter).

## Notes de conformité (stores)

- Apple peut refuser les apps « WebView only » sans valeur ajoutée. Recommandations:
  - Assurer des fonctionnalités utiles et pertinentes pour l’utilisateur final.
  - Utiliser des capacités natives (caméra, fichiers, géolocalisation) si nécessaire.
  - Préférer une section du site orientée usage utilisateurs (plutôt que uniquement `wp-admin`).
  - Fournir une politique de confidentialité et respecter les guidelines.
- Google Play: vérifier que toutes les permissions sont justifiées, fournir une privacy policy.

## Dépannage

- Si les assets `offline.html` ne se reflètent pas dans l’app:
  ```bash
  npx cap copy
  ```
- Problème de scripts sur PowerShell (execution policy): utilisez `cmd` pour exécuter `npm`/`ionic`.
- Conflits de dépendances npm: réessayer avec `--legacy-peer-deps`.

## Où mettre des commentaires

- Les fichiers natifs contiennent des commentaires en français.
- Vous pouvez ajouter d’autres commentaires dans les fichiers si nécessaire.

## Support / Suite

- Pour changer le domaine interne autorisé, mettez à jour `INTERNAL_DOMAIN` (Android/iOS) et `allowNavigation` dans `capacitor.config.ts`.
- Pour ajouter des fonctionnalités (notifications push, partage, etc.), on pourra intégrer d’autres plugins Capacitor.
