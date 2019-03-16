package present.s2;

import com.google.common.geometry.S1Angle;
import com.google.common.geometry.S2LatLng;
import java.util.Comparator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Orders objects by distance.
 *
 * @author Bob Lee (bob@present.co)
 */
public abstract class AbstractDistanceComparator<T> implements Comparator<T> {

  private final S2LatLng origin;

  public AbstractDistanceComparator(S2LatLng origin) {
    this.origin = origin;
  }

  private Map<T, S1Angle> distances = new WeakHashMap<>();

  @Override public int compare(T a, T b) {
    S1Angle distanceA = distanceTo(a);
    S1Angle distanceB = distanceTo(b);
    return distanceA.compareTo(distanceB);
  }

  private S1Angle distanceTo(T t) {
    S1Angle distance = distances.get(t);
    if (distance == null) {
      S2LatLng location = locationOf(t);
      distances.put(t, distance = origin.getDistance(location));
    }
    return distance;
  }

  protected abstract S2LatLng locationOf(T t);
}
