load("@gmaven_rules//:defs.bzl", "gmaven_artifact")

android_instrumentation_test(
    name = "test",
    target_device = "@android_test_support//tools/android/emulated_devices/generic_phone:android_21_x86_qemu2",
    test_app = ":apptest",
)

android_binary(
    name = "apptest",
    srcs = ["AppTest.java"],
    instruments = "//java/com/app:app",
    manifest = "AndroidManifest.xml",
    deps = [
        "//java/com/app:applib",
        gmaven_artifact("com.android.support.test.espresso:espresso_core:aar:3.0.1"),
        gmaven_artifact("com.android.support.test:runner:aar:1.0.1"),
        gmaven_artifact("com.android.support.test:rules:aar:1.0.1"),
        gmaven_artifact("com.android.support:support-annotations:jar:27.0.2"),
        "@junit_junit//jar",
        "@org_hamcrest_java_hamcrest//jar",
    ],
)
