```
$ bazel build :my_lib --output_groups=foo_outputs,bar_outputs --build_event_text_file=bep.txt && grep \"my_lib.*\.out\" bep.txt
INFO: Analyzed target //:my_lib (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //:my_lib up-to-date:
  bazel-bin/my_lib-bar.out
  bazel-bin/my_lib-foo.out
INFO: Elapsed time: 0.060s, Critical Path: 0.01s
INFO: 0 processes.
INFO: Build Event Protocol files produced successfully.
INFO: Build completed successfully, 1 total action
    name: "my_lib-bar.out"
    name: "my_lib-foo.out"
    name: "my_lib-bar.out"
    name: "my_lib-foo.out"
```
