/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_intel_bluetooth_BluetoothPeer */

#ifndef _Included_com_intel_bluetooth_BluetoothPeer
#define _Included_com_intel_bluetooth_BluetoothPeer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    doInquiry
 * Signature: (ILjavax/bluetooth/DiscoveryListener;)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_doInquiry
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    cancelInquiry
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_intel_bluetooth_BluetoothPeer_cancelInquiry
  (JNIEnv *, jobject);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    getServiceHandles
 * Signature: ([Ljavax/bluetooth/UUID;J)[I
 */
JNIEXPORT jintArray JNICALL Java_com_intel_bluetooth_BluetoothPeer_getServiceHandles
  (JNIEnv *, jobject, jobjectArray, jlong);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    getServiceAttributes
 * Signature: ([IJI)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_intel_bluetooth_BluetoothPeer_getServiceAttributes
  (JNIEnv *, jobject, jintArray, jlong, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    registerService
 * Signature: ([B)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_registerService
  (JNIEnv *, jobject, jbyteArray);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    unregisterService
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_intel_bluetooth_BluetoothPeer_unregisterService
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    socket
 * Signature: (ZZ)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_socket
  (JNIEnv *, jobject, jboolean, jboolean);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    getsockaddress
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_com_intel_bluetooth_BluetoothPeer_getsockaddress
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    getsockchannel
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_getsockchannel
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    connect
 * Signature: (IJI)V
 */
JNIEXPORT void JNICALL Java_com_intel_bluetooth_BluetoothPeer_connect
  (JNIEnv *, jobject, jint, jlong, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    listen
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_intel_bluetooth_BluetoothPeer_listen
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    accept
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_accept
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    recv
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_recv__I
  (JNIEnv *, jobject, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    recv
 * Signature: (I[BII)I
 */
JNIEXPORT jint JNICALL Java_com_intel_bluetooth_BluetoothPeer_recv__I_3BII
  (JNIEnv *, jobject, jint, jbyteArray, jint, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    send
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_intel_bluetooth_BluetoothPeer_send__II
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    send
 * Signature: (I[BII)V
 */
JNIEXPORT void JNICALL Java_com_intel_bluetooth_BluetoothPeer_send__I_3BII
  (JNIEnv *, jobject, jint, jbyteArray, jint, jint);

/*
 * Class:     com_intel_bluetooth_BluetoothPeer
 * Method:    close
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_intel_bluetooth_BluetoothPeer_close
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
