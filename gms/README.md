Repro steps:

```
bazel build //java/com/example/bin
adb install bazel-bin/java/com/example/bin/bin.apk
```

Launch `Test App` on emulator, which should crash. 

`adb logcat` crash log: https://gist.github.com/jin/4cff4fecacf22202b65a2a1838244d08
