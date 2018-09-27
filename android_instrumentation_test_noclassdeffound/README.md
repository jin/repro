There was 1 failure:
1) AppTest-testClick(com.google.android.apps.common.testing.suite.AndroidGoogleTest)
junit.framework.AssertionFailedError: java.lang.NoClassDefFoundError: android.support.test.espresso.base.BaseLayerModule_ProvideFailureHanderFactory
        at android.support.test.espresso.DaggerBaseLayerComponent.initialize(DaggerBaseLayerComponent.java:92)
        at android.support.test.espresso.DaggerBaseLayerComponent.<init>(DaggerBaseLayerComponent.java:78)
        at android.support.test.espresso.DaggerBaseLayerComponent.<init>(DaggerBaseLayerComponent.java:37)
        at android.support.test.espresso.DaggerBaseLayerComponent$Builder.build(DaggerBaseLayerComponent.java:191)
        at android.support.test.espresso.DaggerBaseLayerComponent.create(DaggerBaseLayerComponent.java:86)
        at android.support.test.espresso.GraphHolder.baseLayer(GraphHolder.java:42)
        at android.support.test.espresso.Espresso.<clinit>(Espresso.java:52)
        at com.app.AppTest.testClick(AppTest.java:24)
        at java.lang.reflect.Method.invoke(Native Method)
        at java.lang.reflect.Method.invoke(Method.java:372)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at android.support.test.rule.ActivityTestRule$ActivityStatement.evaluate(ActivityTestRule.java:433)
        at org.junit.rules.RunRules.evaluate(RunRules.java:20)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.junit.runners.Suite.runChild(Suite.java:128)
        at org.junit.runners.Suite.runChild(Suite.java:27)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
        at android.support.test.internal.runner.TestExecutor.execute(TestExecutor.java:58)
        at android.support.test.runner.AndroidJUnitRunner.onStart(AndroidJUnitRunner.java:375)
        at android.app.Instrumentation$InstrumentationThread.run(Instrumentation.java:1837)

        at junit.framework.Assert.fail(Assert.java:57)
        at junit.framework.TestCase.fail(TestCase.java:227)
        at com.google.android.apps.common.testing.suite.AndroidGoogleTest.runGoogleAndroidTestCase(AndroidGoogleTest.java:350)
        at com.google.android.apps.common.testing.suite.AndroidGoogleTest.runTest(AndroidGoogleTest.java:393)
        at junit.framework.TestCase.runBare(TestCase.java:141)
        at junit.framework.TestResult$1.protect(TestResult.java:122)
        at junit.framework.TestResult.runProtected(TestResult.java:142)
        at junit.framework.TestResult.run(TestResult.java:125)
        at junit.framework.TestCase.run(TestCase.java:129)
        at junit.framework.TestSuite.runTest(TestSuite.java:252)
        at junit.framework.TestSuite.run(TestSuite.java:247)
        at junit.framework.TestSuite.runTest(TestSuite.java:252)
        at junit.framework.TestSuite.run(TestSuite.java:247)
        at junit.framework.TestSuite.runTest(TestSuite.java:252)
        at junit.framework.TestSuite.run(TestSuite.java:247)
        at junit.framework.TestSuite.runTest(TestSuite.java:252)
        at junit.framework.TestSuite.run(TestSuite.java:247)
        at org.junit.internal.runners.JUnit38ClassRunner.run(JUnit38ClassRunner.java:86)
        at com.google.testing.junit.runner.internal.junit4.CancellableRequestFactory$CancellableRunner.run(CancellableRequestFactory.java:89)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
        at com.google.testing.junit.runner.junit4.JUnit4Runner.run(JUnit4Runner.java:112)
        at com.google.testing.junit.runner.BazelTestRunner.runTestsInSuite(BazelTestRunner.java:144)
        at com.google.testing.junit.runner.BazelTestRunner.main(BazelTestRunner.java:82)

FAILURES!!!
Tests run: 1,  Failures: 1


BazelTestRunner exiting with a return value of 1
JVM shutdown hooks (if any) will run now.
The JVM will exit once they complete.

-- JVM shutdown starting at 2018-09-27 22:56:12 --

================================================================================
