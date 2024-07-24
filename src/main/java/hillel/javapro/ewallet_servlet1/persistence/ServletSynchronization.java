package hillel.javapro.ewallet_servlet1.persistence;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class ServletSynchronization extends HttpServlet {

    private StringBuffer locked = new StringBuffer();

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            writer.write("<HTML><HEAD>"
                    + "<TITLE> Sync </TITLE>"
                    + "</HEAD><BODY>");

                writer.write("synchronized <br>");
                writer.write(sync() + "<br>");
            writer.write("<a href=\"/\">Home</a>");
            writer.write("</BODY></HTML>");
        }
    }

    public String sync() {
        final String STR = "SYNCHRONIZATION";
        synchronized (locked) {
            try {
                for (int i = 0; i < STR.length(); i++) {
                    locked.append(STR.charAt(i));
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = locked.toString();
            locked.delete(0, STR.length() - 1);
            return result;
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        performTask(request, response);
    }
}
