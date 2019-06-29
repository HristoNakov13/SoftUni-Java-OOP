package classBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double calculateSurfaceArea() {
        return 2 * this.getLength() * this.getHeight()
                + 2 * this.getLength() * this.getWidth()
                + 2 * this.getWidth() * this.getHeight();
    }

    public double calculateLateralSurfaceArea() {
        return 2 * this.getLength() * this.getHeight()
                + 2 * this.getHeight() * this.getWidth();
    }

    public double calculateVolume() {
        return this.getLength() * this.getWidth() * this.getHeight();
    }

    private double getLength() {
        return length;
    }

    private double getWidth() {
        return width;
    }

    private double getHeight() {
        return height;
    }
}
