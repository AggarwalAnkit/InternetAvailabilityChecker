# InternetAvailabilityChecker
this project checks if active internet is present or not on device. Connecting to network doesn't mean internet access so this project pings google to check if internet is present or not

# How To Use
To use the library follow the following steps:
1. Add the dependency as follows:
   ```
   implementation 'com.treebo:internetavailabilitychecker:1.0.4'
   ```
2. Initialise it in application’s `onCreate()` function. This is necessary step before starting using the library because it needs context to register connectivity broadcast receiver.
It stores only weakreference to the context, so no need to worry about memory leaks.
Also it does lazy registration of receiver; i.e. it registers receiver whenever first listener attaches to listen to internet changes and unregister itself when last listener stops listening.
    ```
    InternetAvailabilityChecker.init(this);
    ```
3. Implement InternetConnectivityListener interface where ever you want to listen to internet connectivity changes (E.g. in activity, fragment or service).
    ```
    public class MainActivity extends AppCompatActivity implements InternetConnectivityListener {
        @Override
        public void onInternetConnectivityChanged(boolean isConnected) {
            //do something based on connectivity
        }
    }
    ```
4. Get instance of InternetAvailabilityChecker and Add listener whenever you want to start listening to connectivity changes (e.g. in activity’s `onCreate()` function).
It keeps a weakreference to the subscriber and clears the references whenever subscriber gets destroyed (when there is no more strong reference referencing it).
    ```
    mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
    mInternetAvailabilityChecker.addInternetConnectivityListener(this);
    ```
5. Remove listener whenever you are done (e.g. in activity’s `onDestroy()`, fragment’s `onDetach()` or service’s `onDestroy()`).
    ```
    mInternetAvailabilityChecker.removeInternetConnectivityChangeListener(this);
    ```
    
# [Medium Link](https://medium.com/@ankit_aggarwal/check-active-internet-connection-on-android-device-3138ad81932d)
