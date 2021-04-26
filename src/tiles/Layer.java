package src.tiles;

public class Layer {

    private String name;
    private int width, height;
    public int[][] data;

    public Layer(String name, int width, int height, int[] data) {
        int c = 0;

        this.name = name;
        this.width = width;
        this.height = height;
        this.data = new int[this.width][this.height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.data[x][y] = data[c++];
            }
        }

    }

    public boolean isRegionLayer() {
        return this.name.equals("regions");
    }

}
