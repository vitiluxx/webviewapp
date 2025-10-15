import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'td.adhet.app',
  appName: 'ADHET',
  webDir: 'web',
  server: {
    url: 'https://www.adhet-tchad.org/wp-admin',
    cleartext: false,
    allowNavigation: ['www.adhet-tchad.org', 'adhet-tchad.org']
  },
  ios: {
    contentInset: 'always',
    
  },
  android: {
    allowMixedContent: true
  }
};

export default config;
