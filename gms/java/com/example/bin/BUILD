package(
    default_visibility = ["//visibility:public"],
)

android_binary(
    name = "bin",
    srcs = glob(["*.java"]),
    manifest = "AndroidManifest.xml",
    multidex = "native",
    resource_files = glob(["res/**"]),
    deps = [
        "//java/com/example/lib",
        "@com_google_android_gms_play_services_ads_lite_11_8_0//aar",
    ],
)
