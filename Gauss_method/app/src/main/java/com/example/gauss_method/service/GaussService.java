package com.example.gauss_method.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GaussService extends Service {

    private final IBinder binder = new GaussServiceBinder();
    private static final double EPSILON = 1e-10;

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

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
    }

    public double[] solve(double[][] A, double[] B) {
        int N = B.length;
        for (int k = 0; k < N; k++) {
            int max = k;
            for (int i = k + 1; i < N; i++)
                if (Math.abs(A[i][k]) > Math.abs(A[max][k]))
                    max = i;
            double[] temp = A[k];
            A[k] = A[max];
            A[max] = temp;
            double t = B[k];
            B[k] = B[max];
            B[max] = t;
            if (Math.abs(A[k][k]) <= EPSILON) {
                Toast toast = Toast.makeText(this, "This matrix has no solutions", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            }
            for (int i = k + 1; i < N; i++) {
                double factor = A[i][k] / A[k][k];
                B[i] -= factor * B[k];
                for (int j = k; j < N; j++)
                    A[i][j] -= factor * A[k][j];
            }
        }
        double[] solution = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++)
                sum += A[i][j] * solution[j];
            solution[i] = (B[i] - sum) / A[i][i];
        }
        return solution;
    }

    public String initMatrix(EditText[] editText) {
        double[][] matrix = new double[3][3];
        for (int i = 0; i < 9; i++) {
            matrix[i / 3][i % 3] = Double.parseDouble(editText[i].getText().toString());
        }
        double[] freeMembers = new double[3];
        for (int i =0; i < 3; i++) {
            freeMembers[i] = Double.parseDouble(editText[i+9].getText().toString());
        }

        StringBuilder stringBuilder = new StringBuilder();

        double[]res=solve(matrix, freeMembers);
        for (int i = 0; i < res.length; i++) {
            stringBuilder.append(roundResultingRoots(res[i], 2)).append("\n");
        }
        return stringBuilder.toString();
    }

    public static double roundResultingRoots(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
