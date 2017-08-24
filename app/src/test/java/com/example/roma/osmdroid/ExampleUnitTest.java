package com.example.roma.osmdroid;

import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void main() throws Exception {
        String json = "{" +
                "   \"geometry\": {" +
                "       \"type\": \"MyPolygon\"," +
                "       \"coordinates\": [" +
                "           [" +
                "               [-69.899139," +
                "                   12.452005" +
                "               ]," +
                "               [-69.895676," +
                "                   12.423015" +
                "               ]" +
                "           ]" +
                "       ]" +
                "   }" +
                "}";

        Geometry g = new GsonBuilder().create().fromJson(json, Geometry.class);
        System.out.println(g);
        // Geometry [geometry=GeometryData [type=MyPolygon, coordinates={{{-69.899139,12.452005},{-69.895676,12.423015}}}]]
    }

    class Geometry {
        GeometryData geometry;

        @Override
        public String toString() {
            return "Geometry [geometry=" + geometry + "]";
        }
    }

    class GeometryData {
        String type;
        double[][][] coordinates;

    }
}

