/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.util;

/**
 *
 * @author tuski-Revolve
 */
public class LocalDBManager {
    
    

private static LocalDBConnector dbc;

static{
	try {
		dbc=new LocalDBConnector();
	} catch (SQLException e) {
		// TODO Auto-generated catch dblock
		e.printStackTrace();
	}
}    
    
public static void insert2Inbox(int i,String from,String date,String subject) throws Exception {

		//String sql="Insert into logininfo(username,email,password,ip) values (?,?,?,?)";

		  PreparedStatement ps = dbc.getPreparedStatement("INSERT INTO INBOX(SERIAL,EFROM,RCVDATE,SUBJECT)" + "VALUES(?,?,?,?)");
                    ps.setInt(1, i);
                    ps.setString(2, from);
                    ps.setString(3,  date);
                    ps.setString(4, subject);
                   // ps.setString(5, message);
                   
                    ps.executeUpdate();
	}

}
