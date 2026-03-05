# Documentation du Projet CIFI_TECH

## Description du Projet

Ce projet est une application mobile hybride (Android et iOS) nommée "CIFI_TECH". Elle a pour but d'encapsuler le site web "cifi_tech-tchad.org" dans une WebView, en pointant spécifiquement sur la page d'administration (`wp-admin`). L'application est développée avec Ionic et React, et utilise Capacitor comme moteur pour l'accès aux fonctionnalités natives.

## Technologies et Ressources

Voici la liste des technologies et ressources utilisées dans le projet, avec leurs versions actuelles :

*   **Ionic/React**: `^8.7.18` (Framework UI)
*   **React**: `^19.2.4` (Bibliothèque JavaScript)
*   **React Router**: `^7.13.1` (Gestion de la navigation)
*   **Capacitor**: `^8.1.0` (Moteur natif)
*   **TypeScript**: `^5.9.3` (Langage de programmation)
*   **Node.js**: LTS (Environnement d'exécution)
*   **Java**: JDK 17 (pour la compilation Android)

### Plugins Capacitor

*   **App**: `^8.0.1`
*   **Browser**: `^8.0.1`
*   **Camera**: `^8.0.1`
*   **Geolocation**: `^8.1.0`
*   **Network**: `^8.0.1`
*   **Splash Screen**: `^8.0.1`

## Mode d'emploi, de configuration, de fonctionnement et d'utilisation

### Installation des prérequis

*   Node.js LTS et npm.
*   Android Studio avec le SDK et les outils de plateforme.
*   Xcode pour la compilation sur iOS (nécessite un macOS).

### Commandes de base

1.  **Installer les dépendances** :
    ```bash
    npm install
    ```
2.  **Synchroniser les assets web** :
    ```bash
    npx cap sync
    ```
3.  **Ouvrir le projet natif** :
    *   Android : `npx cap open android`
    *   iOS : `npx cap open ios`

### Configuration

*   **URL distante**: Modifiez le fichier `capacitor.config.ts` pour changer l'URL du serveur.
*   **Domaines internes**: Pour autoriser d'autres domaines, ajustez `allowNavigation` dans `capacitor.config.ts` et la constante `INTERNAL_DOMAIN` dans `MainActivity.java` (Android) et `AppDelegate.swift` (iOS).

### Fonctionnement

*   L'application charge l'URL distante dans une WebView.
*   Les liens internes au domaine `cifi_tech-tchad.org` sont ouverts dans l'application.
*   Les liens externes sont ouverts dans le navigateur par défaut du système.
*   En cas de perte de connexion, une page hors-ligne (`web/offline.html`) est affichée.
*   Le bouton "retour" d'Android permet de naviguer dans l'historique de la WebView.

### Utilisation

*   L'application demande les permissions nécessaires (caméra, galerie, localisation) lorsque le site web les sollicite.
*   Les icônes et l'écran de démarrage (splash screen) peuvent être personnalisés en plaçant les fichiers correspondants dans le répertoire `resources/`.
