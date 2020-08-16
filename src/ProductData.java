/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.Vector;
public class ProductData {
    public static Connection GetConnection() throws Exception{
        String url="jdbc:sqlserver://localhost:1455;database=SaleManager";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection cnn=DriverManager.getConnection(url,"sa","123");
        return cnn;
    }
    public static Vector GetProductList() throws Exception{
        Connection cnn=null;
        try {
            cnn=GetConnection();
            String strSQL = "select * from Products";
            Vector<Vector<String>> productList = new Vector<Vector<String>>();
            PreparedStatement pre = cnn.prepareStatement(strSQL);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Vector<String> productDetail = new Vector<String>();
                productDetail.add(rs.getString(1));
                productDetail.add(rs.getString(2));
                productDetail.add(rs.getString(3));
                productDetail.add(rs.getString(4));
                productList.add(productDetail);
            }
            return productList;
        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
        finally{
            cnn.close();
        }
    }
    public static boolean InsertProduct(Product NewProduct) throws Exception{
        Connection cnn=null;
        boolean result = false;
        try {
            cnn=GetConnection();
            String strSQL="insert into Products values(?,?,?,?)";
            PreparedStatement ps= cnn.prepareStatement(strSQL);
            ps.setInt(1, NewProduct.getProductId());
            ps.setString(2, NewProduct.getProductName());
            ps.setFloat(3, NewProduct.getUnitPrice());
            ps.setInt(4, NewProduct.getQuantity());
            result = (ps.executeUpdate()>0);
            ps.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
            
        }
        finally{
            cnn.close();
        }
        return result;
    }
    public static boolean UpdateProduct( Product NewProduct) throws Exception{
        Connection cnn=null;
        boolean result = false;
        try {
            cnn=GetConnection();
            String strSQL="update Products set ProductName=?, UnitPrice=?,Quantity=?"
                    + " where ProductID=?";
            PreparedStatement ps= cnn.prepareStatement(strSQL);
            ps.setString(1, NewProduct.getProductName());
            ps.setFloat(2, NewProduct.getUnitPrice());
            ps.setInt(3, NewProduct.getQuantity());
            ps.setInt(4, NewProduct.getProductId());
            result = (ps.executeUpdate()>0);
            ps.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
            
        }
        finally{
            cnn.close();
        }
        return result;
    }
    public static boolean DeleteProduct(Product NewProduct) throws Exception{
        Connection cnn=null;
        boolean result = false;
        try {
            cnn=GetConnection();
            String strSQL="Delete Products where ProductID=?";
            PreparedStatement ps= cnn.prepareStatement(strSQL);
            ps.setInt(1, NewProduct.getProductId());
            result = (ps.executeUpdate()>0);
            ps.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
            
        }
        finally{
            cnn.close();
        }
        return result;
    }
    
}
