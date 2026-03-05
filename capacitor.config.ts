import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'td.cifi_tech.app',
  appName: 'ADMIN FIDETECHL',
  webDir: 'web',
  server: {
    url: 'https://www.fidetechlformation.com/login',
    cleartext: false,
    allowNavigation: ['www.fidetechlformation.com/login', 'fidetechlformation.com/login']
  },
  ios: {
    contentInset: 'always',
    
  },
  android: {
    allowMixedContent: true
  }
};

export default config;
