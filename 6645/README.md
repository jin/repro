Repro for https://github.com/bazelbuild/bazel/issues/6645 

```
$ bazel build //src/main:app

ERROR: /Users/jingwen/code/repro/6645/src/main/BUILD:3:1: Merging manifest for //src/main:app failed (Exit 1) ResourceProcessorBusyBox failed: error executing command bazel-out/host/bin/external/bazel_tools/src/tools/android/java/com/google/devtools/build/android/ResourceProcessorBusyBox --tool MERGE_MANIFEST -- --manifest src/main/AndroidManifest.xml ... (remaining 8 argument(s) skipped)

Use --sandbox_debug to see verbose messages from the sandbox
Exception in thread "main" java.lang.NoSuchMethodError: com.google.common.base.Objects.firstNonNull(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
	at com.android.ide.common.res2.MergingException$Builder.build(MergingException.java:129)
	at com.android.manifmerger.XmlElement.<init>(XmlElement.java:140)
	at com.android.manifmerger.XmlElement.initMergeableChildren(XmlElement.java:871)
	at com.android.manifmerger.XmlElement.<init>(XmlElement.java:171)
	at com.android.manifmerger.XmlElement.initMergeableChildren(XmlElement.java:871)
	at com.android.manifmerger.XmlElement.<init>(XmlElement.java:171)
	at com.android.manifmerger.XmlDocument.getRootNode(XmlDocument.java:231)
	at com.android.manifmerger.ManifestMerger2.loadLibraries(ManifestMerger2.java:589)
	at com.android.manifmerger.ManifestMerger2.merge(ManifestMerger2.java:126)
	at com.android.manifmerger.ManifestMerger2.access$1100(ManifestMerger2.java:58)
	at com.android.manifmerger.ManifestMerger2$Invoker.merge(ManifestMerger2.java:1163)
	at com.google.devtools.build.android.AndroidManifestProcessor.mergeManifest(AndroidManifestProcessor.java:181)
	at com.google.devtools.build.android.ManifestMergerAction.main(ManifestMergerAction.java:215)
	at com.google.devtools.build.android.ResourceProcessorBusyBox$Tool$11.call(ResourceProcessorBusyBox.java:126)
	at com.google.devtools.build.android.ResourceProcessorBusyBox.processRequest(ResourceProcessorBusyBox.java:234)
	at com.google.devtools.build.android.ResourceProcessorBusyBox.main(ResourceProcessorBusyBox.java:197)
Target //src/main:app failed to build
Use --verbose_failures to see the command lines of failed build steps.
INFO: Elapsed time: 85.869s, Critical Path: 23.21s
INFO: 239 processes: 206 darwin-sandbox, 33 worker.
FAILED: Build did NOT complete successfully
```
