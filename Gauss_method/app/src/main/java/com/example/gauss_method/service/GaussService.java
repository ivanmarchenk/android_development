package com.example.gauss_method.service;

import android.app.Service;
import android.content.Intent;
import android.opengl.Matrix;
import android.os.Binder;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.Toast;

public class GaussService extends Service {

    private final IBinder binder = new GaussServiceBinder();

    public class GaussServiceBinder extends Binder {
        public GaussService getService() {
            Toast.makeText(GaussService.this, "service starting", Toast.LENGTH_SHORT).show();
            return GaussService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }*/

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
    }

    static int PerformOperation(float a[][], int n) {
        int i, j, k = 0, c, flag = 0, m = 0;
        float pro = 0;
        for (i = 0; i < n; i++) {
            if (a[i][i] == 0) {
                c = 1;
                while ((i + c) < n && a[i + c][i] == 0)
                    c++;
                if ((i + c) == n) {
                    flag = 1;
                    break;
                }
                for (j = i, k = 0; k <= n; k++) {
                    float temp = a[j][k];
                    a[j][k] = a[j + c][k];
                    a[j + c][k] = temp;
                }
            }
            for (j = 0; j < n; j++) {
                if (i != j) {
                    float p = a[j][i] / a[i][i];
                    for (k = 0; k <= n; k++)
                        a[j][k] = a[j][k] - (a[i][k]) * p;
                }
            }
        }
        return flag;
    }

    public String init (EditText[]editText) {
//       float[][] matrix = new float[3][4];
//
//        for (int i = 0; i < editText.length; i++) {
//            matrix[i / 4][i % 4] = Float.parseFloat(editText[i].getText().toString());
//        }
//        int n = 3, flag = 0;
//        flag = PerformOperation(matrix, n);
//        if (flag == 1)
//            flag = CheckConsistency(matrix, n, flag);
//
//        if (flag == 2)
//            return ("Infinite Solutions Exists");
//        else if (flag == 3)
//            return ("No Solution Exists");
//        else {
//            String print="";
//            for (int i = 0; i < n; i++)
//                print += (matrix[i][n] / matrix[i][i] +" ");
//            return print;
//        }
        return "";
    }

    static int CheckConsistency(float a[][], int n, int flag) {
        int i, j;
        float sum;
        flag = 3;
        for (i = 0; i < n; i++) {
            sum = 0;
            for (j = 0; j < n; j++)
                sum = sum + a[i][j];
            if (sum == a[i][j])
                flag = 2;
        }
        return flag;
    }

}
