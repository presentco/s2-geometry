package present.s2;

import com.google.common.collect.Streams;
import com.google.common.geometry.S2CellId;
import com.google.common.geometry.S2CellUnion;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Visualizes S2CellIds.
 *
 * @author Bob Lee (bob@present.co)
 */
public class S2Visualizer {

  /**
   * Opens a browser with with the given S2CellIds plotted on a map.
   */
  public static void visualize(Iterable<S2CellUnion> unions) {
    String param = Streams.stream(unions)
        .flatMap(u -> u.cellIds().stream())
        .map(S2CellId::id)
        .map(Long::toUnsignedString)
        .collect(Collectors.joining(","));
    try {
      new ProcessBuilder("open", "http://s2map.com/#" + param)
          .inheritIO().start().waitFor();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Opens a browser with with the given S2CellIds plotted on a map.
   */
  public static void visualize(S2CellUnion union) {
    visualize(Collections.singleton(union));
  }
}
