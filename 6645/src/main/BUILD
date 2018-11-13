load("@gmaven_rules//:defs.bzl", "gmaven_artifact")

android_binary(
    name = "app",
    manifest = "AndroidManifest.xml",
    deps = [
        "//src/main/java/com/example/bazel:greeter_activity",
        # gmaven_artifact("android.arch.work:work-runtime:aar:1.0.0-alpha08"),
    ],
)
