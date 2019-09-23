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

`baz_output` requests an action that fails, and BEP is empty depsite the fact the the files were created:

```
$ bazel build :my_lib --output_groups=foo_outputs,bar_outputs,baz_outputs --build_event_text_file=bep.txt; grep \"my_lib.*\.out\" bep.txt
INFO: Analyzed target //:my_lib (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
ERROR: /usr/local/google/home/jingwen/code/repro/bep_aspect/BUILD:3:1: error executing shell command: '/bin/bash -c touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1' failed (Exit 1) bash failed: error executing command /bin/bash -c 'touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1'

Use --sandbox_debug to see verbose messages from the sandbox
Target //:my_lib failed to build
Use --verbose_failures to see the command lines of failed build steps.
INFO: Elapsed time: 0.126s, Critical Path: 0.07s
INFO: 0 processes.
INFO: Build Event Protocol files produced successfully.
FAILED: Build did NOT complete successfully
```

```
$ tree bazel-bin
bazel-bin
├── my_lib-bar.out
├── my_lib-baz.out
└── my_lib-foo.out
```

`--experimental_show_artifacts` lists all three files:

```
$ bazel build :my_lib --experimental_show_artifacts --output_groups=foo_outputs,bar_outputs,baz_outputs  -k
INFO: Analyzed target //:my_lib (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
ERROR: /usr/local/google/home/jingwen/code/repro/9413/BUILD:3:1: Couldn't build file my_lib-baz.out: error executing shell command: '/bin/bash -c touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1' failed (Exit 1) bash failed: error executing command /bin/bash -c 'touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1'

Use --sandbox_debug to see verbose messages from the sandbox
Target //:my_lib failed to build
Use --verbose_failures to see the command lines of failed build steps.
Build artifacts:
>>>/usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-bar.out
>>>/usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-baz.out
>>>/usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-foo.out
INFO: Elapsed time: 0.200s, Critical Path: 0.10s
INFO: 0 processes.
FAILED: Build did NOT complete successfully
```
