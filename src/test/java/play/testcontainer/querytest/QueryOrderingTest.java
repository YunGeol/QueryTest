package play.testcontainer.querytest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class QueryOrderingTest extends AbstractContainerDatabaseTest {

    @Rule
    public MySQLContainer mysql = new MySQLContainer();

    @Test
    public void test() throws SQLException {
        mysql.start();

        performCUDQuery(mysql, "CREATE TABLE dept (\n" +
                "  dept_no INT(11) unsigned NOT NULL,\n" +
                "  dept_name VARCHAR(32) NOT NULL\n" +
//                "  PRIMARY KEY (dept_no)\n" +
                ");");

        performCUDQuery(mysql, "create UNIQUE index dept_name_idx on dept(dept_name desc)");

        performCUDQuery(mysql, "insert into dept(dept_no, dept_name) values(3, 'name01')");
        performCUDQuery(mysql, "insert into dept(dept_no, dept_name) values(2, 'name02')");
        performCUDQuery(mysql, "insert into dept(dept_no, dept_name) values(7, 'name03')");
        performCUDQuery(mysql, "insert into dept(dept_no, dept_name) values(4, 'name04')");
        performCUDQuery(mysql, "insert into dept(dept_no, dept_name) values(1, 'name05')");


        ResultSet rs = performQuery(mysql, "select * from dept");

        do {
            System.out.print("dept_no : "+rs.getInt(1));
            System.out.println(", dept_name : "+rs.getString(2));
        } while(rs.next());

        System.out.println("Test ended!!");


    }

}
