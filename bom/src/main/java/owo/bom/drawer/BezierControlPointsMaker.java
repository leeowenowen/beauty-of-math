package owo.bom.drawer;

import android.graphics.Color;
import android.graphics.Point;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wangli on 10/31/16.
 */

public class BezierControlPointsMaker {
  public static List<Point> make5() {
    return Arrays.asList(new Point(20, 500),
                         new Point(100, 20),
                         new Point(300, 50),
                         new Point(500, 500),
                         new Point(700, 300));
  }

  public static List<Point> make11() {
    return Arrays.asList(new Point(0, 0),
                         new Point(200, 50),
                         new Point(0, 100),
                         new Point(200, 150),
                         new Point(0, 200),
                         new Point(200, 250),
                         new Point(0, 300),
                         new Point(200, 350),
                         new Point(0, 400),
                         new Point(200, 450),
                         new Point(0, 500));
  }

  public static List<Point> make13() {
    return Arrays.asList(new Point(0, 0),
                         new Point(300, 300),
                         new Point(0, 300),
                         new Point(0, 600),
                         new Point(300, 300),
                         new Point(300, 600),
                         new Point(600, 600),
                         new Point(300, 300),
                         new Point(600, 300),
                         new Point(600, 0),
                         new Point(300, 300),
                         new Point(300, 0),
                         new Point(0, 0));
  }


  public static List<Integer> makeColors() {
    return Arrays.asList(Color.GRAY,
                         Color.RED,
                         Color.BLUE,
                         Color.GREEN,
                         Color.CYAN,
                         Color.MAGENTA,
                         Color.RED,
                         Color.BLUE,
                         Color.GREEN,
                         Color.CYAN,
                         Color.MAGENTA,
                         Color.RED);
  }
}
