package play.testcontainer.querytest;

import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import java.sql.ResultSet;

public class QueryOrderingTest {

    @Rule
    public MySQLContainer mysql = new MySQLContainer();

    @Test
    public void test() {
        mysql.start();
        //ResultSet resultSet = performQuery(mysql, "SELECT 1");
    }

}
