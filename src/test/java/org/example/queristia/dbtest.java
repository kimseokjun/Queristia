package org.example.queristia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class dbtest {
    @Autowired
    DataSource dataSource;

    @Test
    void canConnectAndSelect1() throws Exception {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT 1"); // Oracle이면: SELECT 1 FROM DUAL
             ResultSet rs = ps.executeQuery()) {

            assertTrue(rs.next());
            System.out.println("DB URL   = " + conn.getMetaData().getURL());
            System.out.println("DB User  = " + conn.getMetaData().getUserName());
            System.out.println("Product  = " + conn.getMetaData().getDatabaseProductName());
        }
    }
}
