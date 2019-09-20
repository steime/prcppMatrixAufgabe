#include <iostream>
#include "com_company_Matrix.h"

using namespace std;

JNIEXPORT void JNICALL Java_com_company_Matrix_multiplyC
(JNIEnv *env, jobject jobj, jdoubleArray jA, jdoubleArray jB, jdoubleArray jC, jint zeileA, jint spalteA, jint spalteB)
{

	jboolean isCopyA;
	jboolean isCopyB;
	jboolean isCopyC;
	jdouble* A = env->GetDoubleArrayElements(jA, &isCopyA);
	jdouble* B = env->GetDoubleArrayElements(jB, &isCopyB);
	jdouble* C = env->GetDoubleArrayElements(jC, &isCopyC);

	jdouble sum = 0.0;
	
		
	for (int i = 0; i < zeileA; i++) {
		for (int j = 0; j < spalteB; j++) {
			sum = 0.0;
			for (int k = 0; k < spalteA; k++) {
				sum = sum + A[i * spalteA + k] * B[k * spalteB + j];
			}
			C[i * spalteB + j] = sum;
		}
	}

	if (isCopyA == JNI_TRUE) {
		env->ReleaseDoubleArrayElements(jA, A, 0);
	}
	if (isCopyB == JNI_TRUE) {
		env->ReleaseDoubleArrayElements(jB, B, 0);
	}
	if (isCopyC == JNI_TRUE) {
		env->ReleaseDoubleArrayElements(jC, C, 0);
	}
	
}