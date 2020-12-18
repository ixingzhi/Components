# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontoptimize
-dontpreverify
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepattributes *Annotation*,InnerClasses
-keepattributes Signature

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

-keep class android.support.** {*;}

-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

## 保留R下面的资源
-keep class **.R$* {*;}
## 保留本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

## 保留在Activity中的方法参数是view的方法，这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment
## 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclassmembers class **.R$* {
    public static <fields>;
}
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

## help us to debug
-renamesourcefileattribute SourceFile
-keepattributes Exceptions
-keepattributes SourceFile,LineNumberTable,keepattributes
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes Signature
-keepattributes *Annotation*
-dontshrink

## App混淆配置
-keep class com.xingzhi.android.biz.model.Model { *; }
-keep class * extends com.xingzhi.android.biz.model.Model { *; }
-keep class com.xingzhi.android.biz.event { *; }

# EventBus混淆
-keepclassmembers class ** {
    public void *Event(**);
}

## Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#
## for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

## ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# If you use the byType method to obtain Service, add the following rules to protect the interface:
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# If single-type injection is used, that is, no interface is defined to implement IProvider, the following rules need to be added to protect the implementation
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider

## Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.renamedgson.stream.** { *; }
-keep class com.google.renamedgson.examples.android.model.** { *; }

## Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

## RxJava RxAndroid
-dontwarn java.util.concurrent.Flow*
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

## OkHttp3
#-dontwarn com.squareup.okhttp3.**
#-keep class com.squareup.okhttp3.** { *;}
#-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class okio.** { *; }
-keep interface okio.** { *; }
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

-keep class com.hitomi.** { *; }
-keep interface com.hitomi.** { *; }
-dontwarn com.hitomi.**

## EventBus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

## Toast
-keep class com.hjq.toast.** {*;}

# 华为推送
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-dontwarn com.huawei.hms.**

# 小米推送
-keep class com.fooww.android.biz.push.receive.XiaomiPushReceiver {*;}
-keep class com.xiaomi.**{*;}
-keep public class * extends com.xiaomi.mipush.sdk.PushMessageReceiver
-dontwarn com.xiaomi.**

# Flutter AAR 集成
-keep class io.flutter.facade.**{*;}
-keep class io.flutter.plug.**{*;}
-keep class io.flutter.app.** { *; }
-keep class io.flutter.plugin.**  { *; }
-keep class io.flutter.util.**  { *; }
-keep class io.flutter.view.**  { *; }
-keep class io.flutter.**  { *; }
-keep class io.flutter.plugins.**  { *; }

# 其他
-keep class org.xmlpull.v1.** { *;}
-dontwarn org.xmlpull.v1.**
-dontwarn javax.lang.model.element.**
-keep class javax.lang.model.element.** {*;}
-dontwarn afu.org.checkerframework.**
-dontwarn org.checkerframework.**