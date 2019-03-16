package present.s2;

import com.google.common.geometry.S2Cap;
import com.google.common.geometry.S2LatLng;

/**
 * Creates circular S2 regions.
 *
 * Patterned after https://github.com/mapbox/node-s2/blob/master/viewfinder/viewfinder.cc.
 *
 * @author Bob Lee (bob@present.co)
 */
public class CircularRegion {

  private CircularRegion() {}

  private static final double EARTH_CIRCUMFERENCE = 40_075_017; // m

  private static double metersToRadians(double m) {
    return (Math.PI * 2) * (m / EARTH_CIRCUMFERENCE);
  }

  /** Creates a circular region with the given center and radius (in m). */
  public static S2Cap create(double latitude, double longitude, double radius) {
    double radiusRadians  = metersToRadians(radius);
    return S2Cap.fromAxisHeight(S2LatLng.fromDegrees(latitude, longitude).toPoint(),
        radiusRadians * radiusRadians / 2);
  }
}
