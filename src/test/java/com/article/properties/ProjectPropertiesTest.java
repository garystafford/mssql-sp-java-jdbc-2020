package com.article.properties;

import org.junit.*;

/**
 * @author Gary A. Stafford
 */
public class ProjectPropertiesTest {

    private static final ProjectProperties properties = ProjectProperties.getInstance();

    /**
     *
     */
    public ProjectPropertiesTest() {
    }

    /**
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getProperties method, of class ProjectProperties.
     *
     * @throws Exception
     */
    @Test
    public void testGetPropertiesGetConnectorServerProperty() throws Exception {
        System.out.println("getPropertiesConnectorDatabaseProperty");
        String expResult = "AdventureWorks".toLowerCase();
        String result = properties.getProperties().
                getProperty("connector.database");
        System.out.println("connector.database: " + result);
        Assert.assertEquals(expResult, result.toLowerCase());
    }

    /**
     * Test of getConfigurationFile method, of class ProjectProperties.
     *
     * @throws Exception
     */
    @Test
    public void testGetGetConfigurationFileName() throws Exception {
        System.out.println("getGetConfigurationFileName");
        String expResult = "config.properties";
        String result = properties.getConfigurationFile();
        System.out.println("getConfigurationFile: " + result);
        Assert.assertEquals(expResult, result.toLowerCase());
    }

    /**
     * Test of getProperties method, of class ProjectProperties.
     *
     * @throws Exception
     */
    @Test
    public void testGetPropertiesSize() throws Exception {
        System.out.println("getPropertiesSize");
        int expResult = 6;
        int result = properties.getProperties().size();
        System.out.println("Properties size: " + result);
        Assert.assertEquals(expResult, result);
    }
}
