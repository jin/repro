#include <jni.h>
#include <string>

#include "java/com/app/jni_dep.h"

extern "C" JNIEXPORT jstring JNICALL
Java_app_Jni_hello(JNIEnv *env, jclass clazz) {
  std::string hello = "Hello";
  std::string jni = "JNI";
  return NewStringLatin1(env, (hello + " " + jni).c_str());
}
