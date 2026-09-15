import 'i18next';

// @ts-ignore
import fr from '../locales/fr.json';


declare module 'i18next' {
    interface CustomTypeOptions {
        defaultNS: 'translation';
        resources: {
            translation: typeof fr;
        };
    }
}