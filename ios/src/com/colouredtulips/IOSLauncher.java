package com.colouredtulips;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.bindings.googleanalytics.GAI;
import org.robovm.bindings.googleanalytics.GAIDictionaryBuilder;
import org.robovm.bindings.googleanalytics.GAIFields;
import org.robovm.bindings.googleanalytics.GAITrackerImpl;

public class IOSLauncher extends IOSApplication.Delegate {
    GAITrackerImpl tracker;

//    @Override
//    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
//        boolean b = super.didFinishLaunching(application, launchOptions);
//
//        // Optional: automatically send uncaught exceptions to Google Analytics.
////        [GAI sharedInstance].trackUncaughtExceptions = YES;
////        GAI.getSharedInstance().setTrackUncaughtExceptions(true);
////
////        // Optional: set Google Analytics dispatch interval to e.g. 20 seconds.
//////        [GAI sharedInstance].dispatchInterval = 20;
////        GAI.getSharedInstance().setDispatchInterval(20);
////
////        // Optional: set Logger to VERBOSE for debug information.
//////        [[[GAI sharedInstance] logger] setLogLevel:kGAILogLevelVerbose];
////        GAI.getSharedInstance().getLogger().setLogLevel(GAILogLevel.Verbose);
////
////        // Initialize tracker. Replace with your tracking ID.
//////        [[GAI sharedInstance] trackerWithTrackingId:@"UA-XXXX-Y"];
//////        GAITracker gaiTracker = new GAITrackerImpl();
//////        gaiTracker.set("coba","test");
//////        gaiTracker.set
////        GAI.getSharedInstance().setDefaultTracker(GAI.getSharedInstance().getTracker("UA-58774029-1"));
////
////
////
////
////        // May return nil if a tracker has not already been initialized with a
////// property ID.
//////        id tracker = [[GAI sharedInstance] defaultTracker];
////        GAITracker tracker = GAI.getSharedInstance().getDefaultTracker();
////
////// This screen name value will remain set on the tracker and sent with
////// hits until it is set to a new value or to nil.
//////        [tracker set:kGAIScreenName
//////        value:@"Home Screen"];
////        tracker.set(GAIFields.kGAIScreenName,"Home Screen");
////
////// Previous V3 SDK versions
////// [tracker send:[[GAIDictionaryBuilder createAppView] build]];
////
////// New SDK versions
//////        [tracker send:[[GAIDictionaryBuilder createScreenView] build]];
////        tracker.send(GAIDictionaryBuilder.createAppView().build());
//
//        GAI.getSharedInstance().setTrackUncaughtExceptions(true);
//        GAI.getSharedInstance().setDispatchInterval(20);
//
//        tracker = GAI.getSharedInstance().getTracker("UA-58774029-1");
//
//        GAI.getSharedInstance().setDefaultTracker(tracker);
//
//        tracker.set(GAIFields.kGAIScreenName, "MyGameName");
//        tracker.send(GAIDictionaryBuilder.createAppView().build());
//
//        return b;
//    }

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.accelerometerUpdate=0.2f;

        GAI.getSharedInstance().setTrackUncaughtExceptions(true);
        GAI.getSharedInstance().setDispatchInterval(20);

        tracker = GAI.getSharedInstance().getTracker("UA-58774029-1");

        GAI.getSharedInstance().setDefaultTracker(tracker);

        tracker.set(GAIFields.kGAIScreenName, "MyGameName");
        tracker.send(GAIDictionaryBuilder.createAppView().build());

        return new IOSApplication(new Main(tracker), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}