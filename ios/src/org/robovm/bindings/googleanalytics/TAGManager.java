package org.robovm.bindings.googleanalytics;

import org.robovm.apple.foundation.NSObject;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;

/**
 * Created by rizkisunaryo on 1/21/15.
 */

@NativeClass
public class TAGManager extends NSObject {
//    + (GAI *)sharedInstance;
    @Method(selector = "sharedInstance:")
    public static native void sharedInstance(GAI request);

//    - (id<GAITracker>)trackerWithName:(NSString *)name
    @Method(selector = "trackerWithName:")
    public native GAITracker trackerWithName(String name);

//    trackingId:(NSString *)trackingId;
    @Method(selector = "trackingId:")
    public native void trackingId(String trackingId);

//    - (id<GAITracker>)trackerWithTrackingId:(NSString *)trackingId;
    @Method(selector = "trackerWithTrackingId:")
    public native GAITracker trackerWithTrackingId(String trackingId);

//    - (void)removeTrackerByName:(NSString *)name;
    @Method(selector = "removeTrackerByName:")
    public native void removeTrackerByName(String name);

//    - (void)dispatch;
    @Method(selector = "dispatch:")
    public native void dispatch();

//    - (void)dispatchWithCompletionHandler:(void (^)(GAIDispatchResult))completionHandler;
    @Method(selector = "dispatchWithCompletionHandler:")
    public native void dispatchWithCompletionHandler();
}
