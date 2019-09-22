#include <iostream>
#include "com_company_Matrix.h"

using namespace std;

void multiply(jdouble* A, jdouble* B, jdouble* C, jint zeileA, jint spalteA, jint spalteB)
{
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
}

JNIEXPORT void JNICALL Java_com_company_Matrix_multiplyC
(JNIEnv *env, jobject jobj, jdoubleArray jA, jdoubleArray jB, jdoubleArray jC, jint zeileA, jint spalteA, jint spalteB)
{

	jboolean isCopyA;
	jboolean isCopyB;
	jboolean isCopyC;
	jdouble* A = env->GetDoubleArrayElements(jA, &isCopyA);
	jdouble* B = env->GetDoubleArrayElements(jB, &isCopyB);
	jdouble* C = env->GetDoubleArrayElements(jC, &isCopyC);

	multiply(A, B, C, zeileA, spalteA, spalteB);
		
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

JNIEXPORT void JNICALL Java_com_company_Matrix_powerC
(JNIEnv *env1, jobject jobj1, jdoubleArray d, jdoubleArray res, jint k, jint col)
{
	jint length = env1->GetArrayLength(d);
	jboolean isCopyD;
	jboolean isCopyRes;
	jboolean isCopyTemp;
	jdouble* D = env1->GetDoubleArrayElements(d, &isCopyD);
	jdouble* RES = env1->GetDoubleArrayElements(res, &isCopyRes);
	jdouble* TEMP = env1->GetDoubleArrayElements(res, &isCopyTemp);

	for (auto i = 0; i < k; i++)
	{
		multiply(TEMP,D,RES,col,col,col);
		memcpy(TEMP, RES, length*sizeof(jdouble));
	}

	if (isCopyD == JNI_TRUE) {
		env1->ReleaseDoubleArrayElements(d, D, 0);
	}
	if (isCopyRes == JNI_TRUE) {
		env1->ReleaseDoubleArrayElements(res, RES, JNI_ABORT);
	}
	if (isCopyTemp == JNI_TRUE) {
		env1->ReleaseDoubleArrayElements(res, TEMP, 0);
	}

}