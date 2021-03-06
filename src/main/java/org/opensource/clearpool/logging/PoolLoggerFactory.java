package org.opensource.clearpool.logging;

import org.opensource.clearpool.logging.impl.NullLogger;

/**
 * This log factory depend on commons-logging.jar.We use {@link NullLogger} to replace the log if we
 * set the log unable or we don't have commons-logging.jar.
 *
 * @author xionghui
 * @date 26.07.2014
 * @version 1.0
 */
public class PoolLoggerFactory {
  public static final String LOG_UNABLE = "org.clearpool.log.unable";

  private static final String CLAZZ = "org.slf4j.LoggerFactory";

  private static boolean logUnable = false;

  static {
    logUnable = Boolean.getBoolean(LOG_UNABLE);
    if (!logUnable) {
      try {
        // try to load factory
        Class.forName(CLAZZ);
      } catch (ClassNotFoundException e) {
        logUnable = true;
      }
    }
  }

  /**
   * get log by LogFactoryAdapter
   *
   * @param name
   * @return PoolLogger
   */
  public static PoolLogger getLogger(String name) {
    if (logUnable) {
      return new NullLogger();
    }
    return LoggerFactoryAdapter.getLogger(name);
  }

  /**
   * get log by LogFactoryAdapter
   *
   * @param name
   * @return PoolLogger
   */
  public static PoolLogger getLogger(Class<?> clazz) {
    if (logUnable) {
      return new NullLogger();
    }
    return LoggerFactoryAdapter.getLogger(clazz);
  }
}
