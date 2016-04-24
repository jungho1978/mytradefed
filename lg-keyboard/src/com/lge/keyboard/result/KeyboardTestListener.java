package com.lge.keyboard.result;

import java.util.Map;

import com.android.ddmlib.testrunner.TestIdentifier;
import com.android.tradefed.build.IBuildInfo;
import com.android.tradefed.log.LogUtil.CLog;
import com.android.tradefed.result.ITestInvocationListener;
import com.android.tradefed.result.InputStreamSource;
import com.android.tradefed.result.LogDataType;
import com.android.tradefed.result.TestSummary;

/*
 * the sequence of calls (http://source.android.com/reference/com/android/tradefed/result/ITestInvocationListener.html)
 * 
 * invocationStarted(BuildInfo)
 *      testRunStarted
 *          testStarted
 *          [testFailed]
 *          [testStopped]
 *          [testIgnored]
 *          testEnded
 *      [testRunFailed]
 *      [testRunStopped]
 *      testRunEnded
 * [invocationFailed]
 * [testLog+]
 * invocationEnded
 * gestSummary
 *      
 */
public class KeyboardTestListener implements ITestInvocationListener {
    @Override
    public void invocationStarted(IBuildInfo buildInfo) {
        // TODO Auto-generated method stub
        CLog.i("invocationStarted");
        CLog.i("> ID: " + buildInfo.getBuildId());
        CLog.i("> TargetName: " + buildInfo.getBuildTargetName());
        CLog.i("> DeviceSerial: " + buildInfo.getDeviceSerial());
        CLog.i("> Tag: " + buildInfo.getTestTag());
    }

    @Override
    public void invocationFailed(Throwable cause) {
        // TODO Auto-generated method stub
        CLog.i("invocationFailed");
    }

    @Override
    public void invocationEnded(long elapsedTime) {
        // TODO Auto-generated method stub
        CLog.i("invocatinoEnded");
        CLog.i("> ElapsedTime:run " + elapsedTime);
    }

    @Override
    public void testRunStarted(String name, int numTests) {
        // TODO Auto-generated method stub
        CLog.i("testRunStarted");
        CLog.i("> name: " + name);
        CLog.i("> numTests: " + numTests);
    }

    @Override
    public void testRunFailed(String errorMessage) {
        // TODO Auto-generated method stub
        CLog.i("testRunFailed");
        CLog.i("> errMessage: " + errorMessage);
    }

    @Override
    public void testRunStopped(long elapsedTime) {
        // TODO Auto-generated method stub
        CLog.i("testRunStopped");
        CLog.i("> ElapsedTime: " + elapsedTime);
    }

    @Override
    public void testRunEnded(long elapsedTime, Map<String, String> runMetrics) {
        // TODO Auto-generated method stub
        CLog.i("testRunEnded");
        CLog.i("> elapsedTime: " + elapsedTime);
        CLog.i("> runMetrics: " + runMetrics);
    }

    @Override
    public void testStarted(TestIdentifier test) {
        // TODO Auto-generated method stub
        CLog.i("testStarted");
        CLog.i("> testIdentifier: " + test.toString());
    }

    @Override
    public void testFailed(TestIdentifier test, String trace) {
        // TODO Auto-generated method stub
        CLog.i("testFailed");
        CLog.i("> testIdentifier: " + test.toString());
        CLog.i("> trace: " + trace);
    }

    @Override
    public void testIgnored(TestIdentifier arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void testEnded(TestIdentifier test, Map<String, String> testMetric) {
        // TODO Auto-generated method stub
        CLog.i("testEnded");
        CLog.i("> testIdentifier: " + test.toString());
        CLog.i("> testMetric: " + testMetric);
    }

    @Override
    public TestSummary getSummary() {
        // TODO Auto-generated method stub
        CLog.i("getSummary");
        return null;
    }

    @Override
    public void testLog(String dataName, LogDataType dataType, InputStreamSource dataStream) {
        // TODO Auto-generated method stub
        CLog.i("testLog");
    }

    @Override
    public void testAssumptionFailure(TestIdentifier test, String trace) {
        // TODO Auto-generated method stub
        CLog.i("testAssumptionFailure");
        CLog.i("> testIdentifier: " + test.toString());
        CLog.i("> trace: " + trace);
    }
}
