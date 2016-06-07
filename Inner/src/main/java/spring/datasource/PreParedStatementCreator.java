package spring.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by seasen on 2016/1/19.
 */
public interface PreParedStatementCreator {
    PreparedStatement createPreparedStatement(Connection con)
        throws SQLException;
}
