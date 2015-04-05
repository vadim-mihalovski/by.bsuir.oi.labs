package constants;

public enum Constants {
    COLOR(new double[][] { { 1, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
            new double[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } }),
    TRUE(
            new double[][] { { 0.299, 0.587, 0.114 }, { 0, 0, 0 }, { 0, 0, 0 } },
            new double[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0.299, 0.587, 0.114 } }),
    GRAY(new double[][] { { 0.299, 0.587, 0.114 }, { 0, 0, 0 }, { 0, 0, 0 } },
            new double[][] { { 0, 0, 0 }, { 0.299, 0.587, 0.114 },
                    { 0.299, 0.587, 0.114 } }),
    HALF_COLOR(new double[][] { { 0.299, 0.587, 0.114 }, { 0, 0, 0 },
            { 0, 0, 0 } }, new double[][] { { 0, 0, 0 }, { 0, 1, 0 },
            { 0, 0, 1 } }),
    OPTIMIZED(new double[][] { { 0, 0.7, 0.3 }, { 0, 0, 0 }, { 0, 0, 0 } },
            new double[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } });

    private double[][] leftMatrix;

    private double[][] rightMatrix;

    private Constants(double[][] left, double[][] right) {

        leftMatrix = left;
        rightMatrix = right;
    }

    public double[][] getRightMatrix() {

        return rightMatrix;

    }

    public double[][] getLeftMatrix() {

        return leftMatrix;
    }
}