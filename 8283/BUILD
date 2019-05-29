aar_import(
    name = "envoy",
    aar = "envoy_aar.aar",
)

cc_library(
    name = "native",
    srcs = ["a.cc"],
)

android_binary(
    name = "app",
    deps = [":envoy", ":native"],
    custom_package = "doesnt.matter",
    manifest = "AndroidManifest.xml",
)