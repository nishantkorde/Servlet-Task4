import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ProductRegistration")
public class ProductRegistration extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<html>");
        out.print("<head>");
        out.print("<title>Product Registration</title>");
        out.print("<script>");
        out.print("function showAlert() {");
        out.print("alert('Product Registered Successfully!');");
        out.print("}");
        out.print("</script>");
        out.print("</head>");

        out.print("<body>");
        out.print("<center>");

        out.print("<h2>Product Registration Form</h2>");

        out.print("<form action='ProductDatabase' method='post' onsubmit='showAlert()'>");

        out.print("Product ID : ");
        out.print("<input type='number' name='product_id' required>");
        out.print("<br><br>");

        out.print("Product Name : ");
        out.print("<input type='text' name='product_name' required>");
        out.print("<br><br>");

        out.print("Product Price : ");
        out.print("<input type='number' name='product_price' step='0.01' required>");
        out.print("<br><br>");

        out.print("Product Quantity : ");
        out.print("<input type='number' name='product_quantity' required>");
        out.print("<br><br>");

        out.print("<button type='submit'>Register Product</button>");

        out.print("</form>");

        out.print("</center>");
        out.print("</body>");
        out.print("</html>");


    }
}
