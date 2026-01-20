import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ProductDatabase")
public class ProductDatabase extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pro_id = req.getParameter("product_id");
        String pro_name = req.getParameter("product_name");
        String pro_price = req.getParameter("product_price");
        String pro_quantity = req.getParameter("product_quantity");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/productinfo?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Nishant@12345");

            String insertSql = "INSERT INTO Product (product_id, product_name, product_price, product_quantity) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(insertSql);
            ps.setInt(1, Integer.parseInt(pro_id));
            ps.setString(2,pro_name);
            ps.setFloat(3, Float.parseFloat(pro_price));
            ps.setInt(4, Integer.parseInt(pro_quantity));
            ps.executeUpdate();
            System.out.println("Product Successfully Inserted..!!");

            ps = conn.prepareStatement("SELECT * FROM Product");
            rs = ps.executeQuery();

            System.out.println("\n--- Product Records ---");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("product_id") + "  " +
                                rs.getString("product_name") + "  " +
                                rs.getString("product_price") + "  " +
                                rs.getString("product_quantity")
                );
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
