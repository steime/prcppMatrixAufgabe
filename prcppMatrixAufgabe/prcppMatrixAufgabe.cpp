#include <iostream>
#include "com_company_Matrix.h"
#include <algorithm>

using namespace std;

void multiply(jdouble* A, jdouble* B, jdouble* C, jint zeileA, jint spalteA, jint spalteB)
{
	int l = 0;
	jdouble sum = 0.0;
	for (int i = 0; i<zeileA; i++) {
		int c = i * spalteA, d = i * spalteB;
		for (int j = 0; j<spalteB; j++) {
			sum = 0.0; 
			for (int k = 0; k<spalteA; k++) {
				sum = sum + A[c + k] * B[k * spalteB + j];
			}
			C[d + j ] = sum;
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
		
	env->ReleaseDoubleArrayElements(jA, A, JNI_ABORT);
	env->ReleaseDoubleArrayElements(jB, B, JNI_ABORT);
	env->ReleaseDoubleArrayElements(jC, C, 0);
}

JNIEXPORT void JNICALL Java_com_company_Matrix_powerC
(JNIEnv *env1, jobject jobj1, jdoubleArray jD, jdoubleArray jRes, jint k, jint col)
{
	jint length = env1->GetArrayLength(jD);
	jboolean isCopyD;
	jboolean isCopyRes;
	jdouble* d = env1->GetDoubleArrayElements(jD, &isCopyD);
	jdouble* res = env1->GetDoubleArrayElements(jRes, &isCopyRes);
	jdouble* temp = new jdouble[length];
	memcpy(temp, d, length * sizeof(jdouble));

	for (auto i = 0; i < k; i++)
	{
		multiply(temp,d,res,col,col,col);
		swap(temp, res);
	}
	env1->ReleaseDoubleArrayElements(jD, d, JNI_ABORT);
	env1->ReleaseDoubleArrayElements(jRes, res, 0);
	delete[] temp;
}