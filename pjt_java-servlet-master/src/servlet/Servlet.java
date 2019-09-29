package servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		out.print("<head><title>A binfa a megadott bemenetre</title><meta charset=\"UTF-8\"></head>");
		String input_string =  request.getParameterMap().containsKey("input") ? request.getParameter("input") : "Nincs megadott bemenet";
		String method = request.getParameterMap().containsKey("method") ? request.getParameter("method") : "preorder";
		//out.println(method);
		byte[] bajtok = input_string.getBytes();

        LZWBinFa binFa = new LZWBinFa(out);

        for (int j = 0; j < bajtok.length; ++ j) {
            if (bajtok[j] == 0x4e) {
                continue;
            }

            for (int i = 0; i < 8; ++ i) {
                if ((bajtok[j] & 0x80) != 0) {
                    binFa.add('1');
                } else {
                    binFa.add('0');
                }

                bajtok[j] <<= 1;
            }
        }
        
        binFa.writeHTML(method);
		out.println("<p>Szoras: ");
		out.println(binFa.getSzoras());
		out.println("</p>");
		out.println("<p>Atlag: ");
		out.println(binFa.getAtlag());
		out.println("</p>");
		out.println("<p>Melyseg: ");
		out.println(binFa.getMelyseg());
		out.println("</p>");
		out.println("<a href=\"/servlet/index.html\"><input type=\"Button\" value=\"Vissza\"  /></a>");
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html");
        response.sendError(400, "POST metódus nem engedélyezett");
	}
	
}
