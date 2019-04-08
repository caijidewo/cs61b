import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    private Map<String, Double> defaultMap;
    private static final int MAXDEPTH = 7;
    private boolean querySuccess;
    private double renderUllon;
    private double renderLrlon;
    private double renderUllat;
    private double renderLrlat;

    public Rasterer() {
        defaultMap = new HashMap<>();
        defaultMap.put("ullon", -122.2998046875);
        defaultMap.put("lrlon", -122.2119140625);
        defaultMap.put("ullat", 37.892195547244356);
        defaultMap.put("lrlat", 37.82280243352756);
        defaultMap.put("w", 256.0);
        defaultMap.put("h", 256.0);
        querySuccess = true;
    }

    public Rasterer(double ullon, double lrlon, double ullat, double lrlat, double w, double h) {
        if (defaultMap == null) {
            defaultMap = new HashMap<>();
            defaultMap.put("ullon", ullon);
            defaultMap.put("lrlon", lrlon);
            defaultMap.put("ullat", ullat);
            defaultMap.put("lrlat", lrlat);
            defaultMap.put("w", w);
            defaultMap.put("h", h);
        }
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        if (params == null) {
            querySuccess = false;
        }
        int depth = depth(resolution(params));
        results.put("depth", depth);
        results.put("render_grid", renderGrid(params, depth));
        results.put("raster_ul_lon", renderUllon);
        results.put("raster_ul_lat", renderUllat);
        results.put("raster_lr_lon", renderLrlon);
        results.put("raster_lr_lat", renderLrlat);
        results.put("query_success", querySuccess);
        return results;
    }
    public double resolution(Map<String, Double> params) {
        if (params == null) {
            return 0;
        } else {
            return (params.get("lrlon") - params.get("ullon")) / params.get("w");
        }
    }
    public int depth(double requireResolution) {
        double minResolution = resolution(defaultMap);
        double iLevelDepthResolution = minResolution;
        for (int i = 0; i <= MAXDEPTH; i++) {
            if (iLevelDepthResolution <= requireResolution) {
                return i;
            }
            iLevelDepthResolution = iLevelDepthResolution / 2;
        }
        return MAXDEPTH;
    }
    public String[][] renderGrid(Map<String, Double> params, int depth) {
        int xOfTLC = xOfTopLeftCorner(params.get("ullon"), depth);
        int yOfTLC = yOfTopLeftCorner(params.get("ullat"), depth);
        int xOfLRC = xOfLowerRightCorner(params.get("lrlon"), depth);
        int yOfLRC = yOfLowerRightCorner(params.get("lrlat"), depth);
        renderUllon = ullonOfX(xOfTLC, depth);
        renderLrlon = lrlonOfX(xOfLRC, depth);
        renderUllat = ullatOfY(yOfTLC, depth);
        renderLrlat = lrlatOfY(yOfLRC, depth);
        int row = Math.abs(yOfLRC - yOfTLC) + 1;
        int column = Math.abs(xOfLRC - xOfTLC) + 1;
        String[][] rederFile = new String[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                rederFile[i][j] = "d" + depth + "_x" + (j + xOfTLC) + "_y" + (i + yOfTLC) + ".png";
            }
        }
        return rederFile;
    }
    //return 与x对应的ullon，ullon是x轴或y轴上的数，而x（y）是在x轴（y轴）方向上块的编号
    public double ullonOfX(int x, int depth) {
        double ullon = defaultMap.get("ullon")
                + (defaultMap.get("lrlon") - defaultMap.get("ullon")) / Math.pow(2, depth) * x;
        return ullon;
    }

    public double lrlonOfX(int x, int depth) {
        double lrlon = ullonOfX(x, depth)
                + (defaultMap.get("lrlon") - defaultMap.get("ullon")) / Math.pow(2, depth);
        return lrlon;
    }
    public double ullatOfY(int y, int depth) {
        double ullat = defaultMap.get("ullat")
                - ((defaultMap.get("ullat") - defaultMap.get("lrlat")) / Math.pow(2, depth) * y);
        return ullat;
    }
    public double lrlatOfY(int y, int depth) {
        double lrlat = ullatOfY(y, depth)
                - ((defaultMap.get("ullat") - defaultMap.get("lrlat")) / Math.pow(2, depth));
        return lrlat;
    }
    //return 符合u最接近llon要求的块的编号(image)，x is from 0 to 2^depth - 1
    public int xOfTopLeftCorner(double requireUllon, int depth) {
        int x = (int) Math.pow(2, depth) - 1;
        for (; x >= 0; x--) {
            if (ullonOfX(x, depth) <= requireUllon) {
                return x;
            }
        }
        return 0;
    }
    public int yOfTopLeftCorner(double requireUllat, int depth) {
        int y = (int) Math.pow(2, depth) - 1;
        for (; y >= 0; y--) {
            if (ullatOfY(y, depth) >= requireUllat) {
                return y;
            }
        }
        return 0;
    }
    public int xOfLowerRightCorner(double requireLrlon, int depth) {
        int x = 0;
        for (; x < Math.pow(2, depth); x++) {
            if (lrlonOfX(x, depth) >= requireLrlon) {
                return x;
            }
        }
        return (int) Math.pow(2, depth) - 1;
    }
    public int yOfLowerRightCorner(double requireLrlat, int depth) {
        int y = 0;
        for (; y < Math.pow(2, depth); y++) {
            if (lrlatOfY(y, depth) <= requireLrlat) {
                return y;
            }
        }
        return (int) Math.pow(2, depth) - 1;
    }
    public static void main(String[] args) {
        String a = "d3_x1_y3";
        System.out.println(a.charAt(1));
        System.out.println(a.charAt(4));
        System.out.println(a.charAt(7));
        int x = 1;
        int y = 1;
        int depth = 4;
        String b = "d" + depth + "_x" + (x + 1) + "_y" + (y + 1);
        System.out.println(b);
    }
}
