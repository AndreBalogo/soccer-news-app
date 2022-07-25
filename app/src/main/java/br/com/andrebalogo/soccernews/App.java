package br.com.andrebalogo.soccernews;

import android.app.Application;

/**
* FIXME Extrair o máximo de ViewModels e camada de acesso
 * a dados. https://stackoverflow.com/a/14057777/3072570">Android Singleton with Global Contexto</a>
*/
public class App extends Application {

    private static App instance;

    //centralizar instância.
    public static App getInstance() {
        return  instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}
