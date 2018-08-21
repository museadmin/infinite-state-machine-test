package com.github.museadmin.infinite_state_machine.test.unit.tests.core;

import com.github.museadmin.infinite_state_machine.lib.PropertyCache;
import com.github.museadmin.infinite_state_machine.test.unit.tests.support.TestSupportMethods;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.net.URL;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestPropertyCache extends TestSupportMethods {

  protected PropertyCache propertyCache;

  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();

  @Before
  public void setUp(){
    propertyCache = new PropertyCache();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    URL is = loader.getResource(PROPERTIES);
    propertyCache.importProperties(is.getPath());
  }

  @Test
  public void testCacheGetsValueForKnownKey() {
    assertTrue(propertyCache.getProperty("rdbms").equalsIgnoreCase("sqlite3") ||
        propertyCache.getProperty("rdbms").equalsIgnoreCase("postgres"));
  }
  @Test
  public void testCacheGetsDefaultValueForUnknownKey() {
    assertEquals("default",
        propertyCache.getProperty("xdbms", "default"));
  }
  @Test
  public void testCacheDoesNotGetDefaultValueForKnownKey() {
    assertTrue(propertyCache.getProperty("rdbms", "default").equalsIgnoreCase("sqlite3") ||
        propertyCache.getProperty("rdbms", "default").equalsIgnoreCase("postgres"));
  }
}