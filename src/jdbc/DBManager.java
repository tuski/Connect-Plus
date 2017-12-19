package jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBManager {

    private static DBConnector dbc;

    static {
        try {
            dbc = new DBConnector();
        } catch (SQLException e) {
            // TODO Auto-generated catch dblock
            e.printStackTrace();
        }
    }

    public static void registerUser(String userName, String email, String password, InputStream imgLink) throws Exception {
        String ip = "127.0.0.1";
        String sql = "Insert into logininfo(username,email,password,ip) values (?,?,?,?)";

        PreparedStatement pst = dbc.getPreparedStatement(sql);
        pst.setString(1, userName);
        pst.setString(2, email);
        pst.setString(3, password);
        pst.setString(4, ip);
        pst.executeUpdate();

        String sql2 = "Insert into userinfo(username,propic) values (?,?)";
        PreparedStatement pst2 = dbc.getPreparedStatement(sql2);
        pst2.setString(1, userName);
       // pst2.setString(2, email);
        pst2.setBlob(2, imgLink);

        pst2.executeUpdate();

    }

    public static boolean loginUser(String userName, String password) throws Exception {

        String sql = "select password from logininfo where username=?";
        String pass = null;
        boolean result = false;
        //String ip=util.getIp() ;
        String ip = "127.0.0.1";
        PreparedStatement pst = dbc.getPreparedStatement(sql);
        pst.setString(1, userName);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            pass = rs.getString("password");
        }
        if (password.equals(pass)) {
            String sql2 = "Update  logininfo Set ip=? Where username=?";
            PreparedStatement pst2 = dbc.getPreparedStatement(sql2);
            pst2.setString(1, ip);
            pst2.setString(2, userName);
            pst2.executeUpdate();

            result = true;
        }

//            System.out.println();
        return result;

    }

    public static HashMap<String, String> showFriendList() throws SQLException {

        String sql = "Select username,ip from logininfo";
        String user = null, ip = "127.0.0.1";
        //List<String> userlist = new ArrayList<String>();
        HashMap<String, String> userNip = new HashMap<String, String>();

        PreparedStatement pst = dbc.getPreparedStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            user = rs.getString("userName");
            ip = rs.getString("ip");
            //userlist.add(user);
            userNip.put(user, ip);
        }
        return userNip;
    }

    public static void updateUser(String username, String Name, String address, InputStream imgLink, String contact, String gender) throws Exception {

        String sql = "update  userinfo set name=?, address=?,propic=?, contact=?,gender=? where username=?";

        PreparedStatement pst = dbc.getPreparedStatement(sql);
        pst.setString(1, Name);
        pst.setString(2, address);

        pst.setBlob(3, imgLink);
        pst.setString(4, contact);
        pst.setString(5, gender);

        pst.setString(6, username);
        pst.executeUpdate();
    }

    public static List<String> getUserinfo(String userName) throws FileNotFoundException, SQLException, IOException {

//             File file=new File(userName+".jpg");
//            FileOutputStream fos=new FileOutputStream(file);
//            byte b[];
//            Blob blob;
        String sql = "SELECT name,address,contact,gender FROM userinfo WHERE username=?";
        PreparedStatement ps = dbc.getPreparedStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();

        List<String> s = new ArrayList<>();
        String a = null, b = null, c = null, d = null;
        if (rs.next()) {
            s.add(rs.getString("name"));
            s.add(rs.getString("address"));
            s.add(rs.getString("contact"));
            s.add(rs.getString("gender"));
            //s.add(rs.getString("email"));

        }

//            while(rs.next()){
//       }         
//                blob=rs.getBlob("propic");
//                b=blob.getBytes(1,(int)blob.length());
//                fos.write(b);
//            }
        // Image img =new Image("file:"+userName+".jpg",100,150,true,true);
        // WorkStation.friendName.setText(lbl);
        // WorkStation.friendsPic.setImage(img);
        return s;
    }

    public static void getAllPropic() throws SQLException, FileNotFoundException, IOException {
        String sql = "SELECT username,propic FROM userinfo ";
        PreparedStatement ps = dbc.getPreparedStatement(sql);
        ResultSet rs = ps.executeQuery();
        byte b[];
        Blob blob;
        String strDirectoy="d:/img";
 boolean success = (new File(strDirectoy)).mkdir();
 
 while (rs.next()) {
            blob = rs.getBlob("propic");
            b = blob.getBytes(1, (int) blob.length());
            File file = new File("d:/img/" + rs.getString("username") + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(b);

        }
 
    }

}
