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

```protobuf
completed {
  success: true
  output_group {
    name: "bar_outputs"
    file_sets {
      id: "0"
    }
  }
  output_group {
    name: "foo_outputs"
    file_sets {
      id: "1"
    }
  }
  important_output {
    name: "my_lib-bar.out"
    uri: "file:///usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-bar.out"
    path_prefix: "bazel-out"
    path_prefix: "k8-fastbuild"
    path_prefix: "bin"
  }
  important_output {
    name: "my_lib-foo.out"
    uri: "file:///usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-foo.out"
    path_prefix: "bazel-out"
    path_prefix: "k8-fastbuild"
    path_prefix: "bin"
  }
}
}
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

I would expect `my_lib-foo.out` and `my_lib-bar.out` to be listed.

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


```diff
$ diff ok.txt notok.txt 
47,48c47,48
<   uuid: "dd3e03f3-e283-4fb1-bf05-55dfd6285d22"
<   start_time_millis: 1569278052247
---
>   uuid: "8afddfa5-dcec-4598-a4ce-55e20b0b17b5"
>   start_time_millis: 1569278033004
50c50
<   options_description: "--output_groups=foo_outputs,bar_outputs --build_event_text_file=ok.txt --keep_going"
---
>   options_description: "--output_groups=foo_outputs,bar_outputs,baz_outputs --build_event_text_file=notok.txt --keep_going"
65c65
<   args: "--startup_time=12"
---
>   args: "--startup_time=15"
218,219c218,219
<   args: "--output_groups=foo_outputs,bar_outputs"
<   args: "--build_event_text_file=ok.txt"
---
>   args: "--output_groups=foo_outputs,bar_outputs,baz_outputs"
>   args: "--build_event_text_file=notok.txt"
425c425
<         combined_form: "--startup_time=12"
---
>         combined_form: "--startup_time=15"
427c427
<         option_value: "12"
---
>         option_value: "15"
1491c1491
<         combined_form: "--output_groups=foo_outputs,bar_outputs"
---
>         combined_form: "--output_groups=foo_outputs,bar_outputs,baz_outputs"
1493c1493
<         option_value: "foo_outputs,bar_outputs"
---
>         option_value: "foo_outputs,bar_outputs,baz_outputs"
1498c1498
<         combined_form: "--build_event_text_file=ok.txt"
---
>         combined_form: "--build_event_text_file=notok.txt"
1500c1500
<         option_value: "ok.txt"
---
>         option_value: "notok.txt"
1541,1542c1541,1542
<   cmd_line: "--output_groups=foo_outputs,bar_outputs"
<   cmd_line: "--build_event_text_file=ok.txt"
---
>   cmd_line: "--output_groups=foo_outputs,bar_outputs,baz_outputs"
>   cmd_line: "--build_event_text_file=notok.txt"
1544,1545c1544,1545
<   explicit_cmd_line: "--output_groups=foo_outputs,bar_outputs"
<   explicit_cmd_line: "--build_event_text_file=ok.txt"
---
>   explicit_cmd_line: "--output_groups=foo_outputs,bar_outputs,baz_outputs"
>   explicit_cmd_line: "--build_event_text_file=notok.txt"
1586c1586
<         combined_form: "--startup_time=12"
---
>         combined_form: "--startup_time=15"
1588c1588
<         option_value: "12"
---
>         option_value: "15"
2652c2652
<         combined_form: "--output_groups=foo_outputs,bar_outputs"
---
>         combined_form: "--output_groups=foo_outputs,bar_outputs,baz_outputs"
2654c2654
<         option_value: "foo_outputs,bar_outputs"
---
>         option_value: "foo_outputs,bar_outputs,baz_outputs"
2659c2659
<         combined_form: "--build_event_text_file=ok.txt"
---
>         combined_form: "--build_event_text_file=notok.txt"
2661c2661
<         option_value: "ok.txt"
---
>         option_value: "notok.txt"
2805,2806c2805
<   progress {
<     opaque_count: 2
---
>   workspace_status {
2809,2811c2808,2810
< children {
<   progress {
<     opaque_count: 3
---
> workspace_status {
>   item {
>     key: "BUILD_EMBED_LABEL"
2813,2816c2812,2814
< }
< children {
<   named_set {
<     id: "0"
---
>   item {
>     key: "BUILD_HOST"
>     value: "jingwen.nyc.corp.google.com"
2818,2827c2816,2818
< }
< progress {
<   stderr: "\r\033[1A\033[K\033[32mAnalyzing:\033[0m target //:my_lib (0 packages loaded, 0 targets configured)\n\r\033[1A\033[K\033[32mINFO: \033[0mAnalyzed target //:my_lib (0 packages loaded, 0 targets configured).\n\n\r\033[1A\033[K\033[32mINFO: \033[0mFound 1 target...\n\n"
< }
< }
< 
< event {
< id {
<   named_set {
<     id: "0"
---
>   item {
>     key: "BUILD_TIMESTAMP"
>     value: "1569278033"
2829,2836c2820,2822
< }
< named_set_of_files {
<   files {
<     name: "my_lib-bar.out"
<     uri: "file:///usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-bar.out"
<     path_prefix: "bazel-out"
<     path_prefix: "k8-fastbuild"
<     path_prefix: "bin"
---
>   item {
>     key: "BUILD_USER"
>     value: "jingwen"
2844c2830
<     opaque_count: 3
---
>     opaque_count: 2
2849c2835
<     opaque_count: 4
---
>     opaque_count: 3
2853,2854c2839,2844
<   named_set {
<     id: "1"
---
>   action_completed {
>     primary_output: "bazel-out/k8-fastbuild/bin/my_lib-baz.out"
>     label: "//:my_lib"
>     configuration {
>       id: "a97574deb0f0815b04aefb212765a5ae"
>     }
2857a2848
>   stderr: "\r\033[1A\033[K\033[32mAnalyzing:\033[0m target //:my_lib (0 packages loaded, 0 targets configured)\n\r\033[1A\033[K\033[32mINFO: \033[0mAnalyzed target //:my_lib (0 packages loaded, 0 targets configured).\n\n\r\033[1A\033[K\033[32mINFO: \033[0mFound 1 target...\n\n\r\033[1A\033[K\033[32m[0 / 2]\033[0m 2 actions, 0 running\n    [Prepa] BazelWorkspaceStatusAction stable-status.txt\n    [Sched] Action my_lib-baz.out\n"
2863,2864c2854,2859
<   named_set {
<     id: "1"
---
>   action_completed {
>     primary_output: "bazel-out/k8-fastbuild/bin/my_lib-baz.out"
>     label: "//:my_lib"
>     configuration {
>       id: "a97574deb0f0815b04aefb212765a5ae"
>     }
2867,2873c2862,2865
< named_set_of_files {
<   files {
<     name: "my_lib-foo.out"
<     uri: "file:///usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-foo.out"
<     path_prefix: "bazel-out"
<     path_prefix: "k8-fastbuild"
<     path_prefix: "bin"
---
> action {
>   label: "//:my_lib"
>   configuration {
>     id: "a97574deb0f0815b04aefb212765a5ae"
2874a2867,2870
>   type: "Action"
>   command_line: "/bin/bash"
>   command_line: "-c"
>   command_line: "touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1"
2887,2898c2883,2888
< completed {
<   success: true
<   output_group {
<     name: "bar_outputs"
<     file_sets {
<       id: "0"
<     }
<   }
<   output_group {
<     name: "foo_outputs"
<     file_sets {
<       id: "1"
---
> children {
>   action_completed {
>     primary_output: "bazel-out/k8-fastbuild/bin/my_lib-baz.out"
>     label: "//:my_lib"
>     configuration {
>       id: "a97574deb0f0815b04aefb212765a5ae"
2901,2921d2890
<   important_output {
<     name: "my_lib-bar.out"
<     uri: "file:///usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-bar.out"
<     path_prefix: "bazel-out"
<     path_prefix: "k8-fastbuild"
<     path_prefix: "bin"
<   }
<   important_output {
<     name: "my_lib-foo.out"
<     uri: "file:///usr/local/google/home/jingwen/.cache/bazel/_bazel_jingwen/076bfe8ba4a830458af6c165edb758c8/execroot/__main__/bazel-out/k8-fastbuild/bin/my_lib-foo.out"
<     path_prefix: "bazel-out"
<     path_prefix: "k8-fastbuild"
<     path_prefix: "bin"
<   }
< }
< }
< 
< event {
< id {
<   workspace_status {
<   }
2923,2938c2892
< workspace_status {
<   item {
<     key: "BUILD_EMBED_LABEL"
<   }
<   item {
<     key: "BUILD_HOST"
<     value: "jingwen.nyc.corp.google.com"
<   }
<   item {
<     key: "BUILD_TIMESTAMP"
<     value: "1569278052"
<   }
<   item {
<     key: "BUILD_USER"
<     value: "jingwen"
<   }
---
> completed {
2956,2957c2910
<   overall_success: true
<   finish_time_millis: 1569278052320
---
>   finish_time_millis: 1569278033170
2959c2912,2913
<     name: "SUCCESS"
---
>     name: "BUILD_FAILURE"
>     code: 1
2967c2921
<     opaque_count: 4
---
>     opaque_count: 3
2971c2925
<   stderr: "\r\033[1A\033[K\033[32m[0 / 1]\033[0m [Prepa] BazelWorkspaceStatusAction stable-status.txt\n\r\033[1A\033[KTarget //:my_lib up-to-date:\n\033[32m[1 / 1]\033[0m checking cached actions\n\r\033[1A\033[K  bazel-bin/my_lib-bar.out\n\033[32m[1 / 1]\033[0m checking cached actions\n\r\033[1A\033[K  bazel-bin/my_lib-foo.out\n\033[32m[1 / 1]\033[0m checking cached actions\n\r\033[1A\033[K\033[32mINFO: \033[0mElapsed time: 0.073s, Critical Path: 0.00s\n\033[32m[1 / 1]\033[0m checking cached actions\n\r\033[1A\033[K\033[32mINFO: \033[0m0 processes.\n\033[32m[1 / 1]\033[0m checking cached actions\n\r\033[1A\033[K\033[32mINFO:\033[0m Build completed successfully, 1 total action\n"
---
>   stderr: "\r\033[1A\033[K\r\033[1A\033[K\r\033[1A\033[K\033[31m\033[1mERROR: \033[0m/usr/local/google/home/jingwen/code/repro/9413/BUILD:3:1: Couldn\'t build file my_lib-baz.out: error executing shell command: \'/bin/bash -c touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1\' failed (Exit 1) bash failed: error executing command /bin/bash -c \'touch bazel-out/k8-fastbuild/bin/my_lib-baz.out && exit 1\'\n\nUse --sandbox_debug to see verbose messages from the sandbox\n\033[32m[2 / 2]\033[0m checking cached actions\n\r\033[1A\033[KTarget //:my_lib failed to build\n\033[32m[2 / 2]\033[0m checking cached actions\n\r\033[1A\033[KUse --verbose_failures to see the command lines of failed build steps.\n\033[32m[2 / 2]\033[0m checking cached actions\n\r\033[1A\033[K\033[32mINFO: \033[0mElapsed time: 0.166s, Critical Path: 0.09s\n\033[32m[2 / 2]\033[0m checking cached actions\n\r\033[1A\033[K\033[32mINFO: \033[0m0 processes.\n\033[32m[2 / 2]\033[0m checking cached actions\n\r\033[1A\033[K\033[31m\033[1mFAILED:\033[0m Build did NOT complete successfully\n"
2982c2936
<     actions_executed: 1
---
>     actions_executed: 2
3002c2956
<     contents: "0.073000"
---
>     contents: "0.166000"
3006c2960
<     contents: "Critical Path: 0.00s, Remote (0.00% of the time): [parse: 0.00%, queue: 0.00%, network: 0.00%, upload: 0.00%, setup: 0.00%, process: 0.00%, fetch: 0.00%, retry: 0.00%, processOutputs: 0.00%, other: 0.00%, input files: 0, input bytes: 0, memory bytes: 0]\n  0.00s, Remote (0.00% of the time): [parse: 0.00%, queue: 0.00%, network: 0.00%, upload: 0.00%, setup: 0.00%, process: 0.00%, fetch: 0.00%, retry: 0.00%, processOutputs: 0.00%, other: 0.00%, input files: 0, input bytes: 0, memory bytes: 0] action \'BazelWorkspaceStatusAction stable-status.txt\'"
---
>     contents: "Critical Path: 0.09s, Remote (0.00% of the time): [parse: 0.00%, queue: 0.00%, network: 0.00%, upload: 0.00%, setup: 0.00%, process: 0.00%, fetch: 0.00%, retry: 0.00%, processOutputs: 0.00%, other: 0.00%, input files: 0, input bytes: 0, memory bytes: 0]\n  0.09s, Remote (0.00% of the time): [parse: 0.00%, queue: 0.00%, network: 0.00%, upload: 0.00%, setup: 0.00%, process: 0.00%, fetch: 0.00%, retry: 0.00%, processOutputs: 0.00%, other: 0.00%, input files: 0, input bytes: 0, memory bytes: 0] action \'Action my_lib-baz.out\'"
```
