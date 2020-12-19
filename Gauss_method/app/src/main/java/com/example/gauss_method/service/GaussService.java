package com.example.gauss_method.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

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

    public String init(EditText[] editText) {
        double[][] matrix = new double[3][3];
        for (int i = 0; i < 9; i++) {
            matrix[i / 3][i % 3] = Double.parseDouble(editText[i].getText().toString());
        }
        double[] freeMembers = new double[3];
        for (int i =0; i < 3; i++) {
            freeMembers[i] = Double.parseDouble(editText[i+9].getText().toString());
        }

        StringBuilder sb = new StringBuilder();

        //return Arrays.toString(solve(matrix, freeMembers));
        double[]res=solve(matrix, freeMembers);
        for (int i = 0; i < res.length; i++) {
            sb.append(round(res[i], 2)).append("\n");
        }
        return sb.toString();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
