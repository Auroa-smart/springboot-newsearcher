package com.example.utils;

import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.ml.clustering.Clusterable;

public class MyClusterable implements Clusterable {
    private final RealVector point;

    public MyClusterable(RealVector point) {
        this.point = point;
    }

    @Override
    public double[] getPoint() {
        return point.toArray();
    }
}
