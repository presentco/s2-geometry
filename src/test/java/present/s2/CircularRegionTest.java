package present.s2;

import com.google.common.geometry.S2Cap;
import com.google.common.geometry.S2Cell;
import com.google.common.geometry.S2LatLng;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CircularRegionTest {

  @Test public void testBasic() {
    double latitude = 37.77151;
    double longitude = -122.3965447;

    S2Cell battery = newCell(37.7979267, -122.4034064);
    S2Cell umi = newCell(37.7627331, -122.3983706);
    S2Cell google = newCell(37.4220041, -122.0862462);
    S2Cell stl = newCell(38.6532851, -90.3835469);

    S2Cap region = CircularRegion.create(latitude, longitude, 10_000);
    assertTrue(region.contains(battery));
    assertTrue(region.contains(umi));
    assertFalse(region.contains(google));
    assertFalse(region.contains(stl));
  }

  @Test public void testAccuracy() {
    // 3km apart
    double latitude = 37.77151;
    double longitude = -122.3965447;
    S2Cell battery = newCell(37.7979267, -122.4034064);
    assertTrue(CircularRegion.create(latitude, longitude, 3100).contains(battery));
    assertFalse(CircularRegion.create(latitude, longitude, 2900).contains(battery));
  }

  private static S2Cell newCell(double latitude, double longitude) {
    return new S2Cell(S2LatLng.fromDegrees(latitude, longitude));
  }
}
