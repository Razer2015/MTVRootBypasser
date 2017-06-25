package fi.razerman.mtvrootbypasser;

/**
 * Created by Razerman on 25.06.2017.
 */


import android.util.Log;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XC_MethodReplacement.returnConstant;

public class RootDetectionBypass implements IXposedHookLoadPackage {
    private static final String TAG = RootDetectionBypass.class.getSimpleName();

    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("fi.mtvkatsomo")){
            Log.d(TAG, "Katsomo app detected, starting to bypass root detection!");

            findAndHookMethod("com.comscore.utils.RootDetector", lpparam.classLoader, "isDeviceRooted", returnConstant(0));                                         // Check 1
            findAndHookMethod("io.fabric.sdk.android.services.common.CommonUtils", lpparam.classLoader, "g", android.content.Context.class, returnConstant(0));     // Check 2

            Log.d(TAG, "Bypassed Katsomo's root detection!");
        }
        else if (lpparam.packageName.equals("fi.mtv.svod")){
            Log.d(TAG, "C-More app detected, starting to bypass root detection!");

            findAndHookMethod("com.comscore.utils.RootDetector", lpparam.classLoader, "isDeviceRooted", returnConstant(0));                                         // Check 1

            Log.d(TAG, "Bypassed C-More's root detection!");
        }
    }
}
