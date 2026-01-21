import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/ProductBill")
public class ProductBill extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = "";
        int quantity = 0;
        double price = 0;
        double total = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/productinfo?useSSL=false&serverTimezone=UTC",
                    "root",
                    "Nishant@12345"
            );

            String sql = "SELECT product_name, product_price, product_quantity FROM Product ORDER BY product_id DESC LIMIT 1";


            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("product_name");
                price = rs.getDouble("product_price");
                quantity = rs.getInt("product_quantity");
                total = price * quantity;
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3 style='color:red'>Error loading bill</h3>");
            return;
        }

        out.print("<html><head><title>Product Bill</title></head><body>");
        out.print("<center>");

        out.print("<h2>Product Bill</h2>");
        out.print("<hr width='50%'>");

        out.print("<table border='1' cellpadding='10'>");
        out.print("<tr><th>Product Name</th><td>" + name + "</td></tr>");
        out.print("<tr><th>Quantity</th><td>" + quantity + "</td></tr>");
        out.print("<tr><th>Price</th><td>₹ " + price + "</td></tr>");
        out.print("<tr><th>Total Amount</th><td><b>₹ " + total + "</b></td></tr>");
        out.print("</table>");

        out.print("<br><h3>Thank You for Your Purchase!</h3>");

        out.print("</center></body></html>");
    }
}
