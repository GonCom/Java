package servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadFileServlet
 */
@WebServlet("/downloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	//Ubicación de los archivos
	final String FILE_LOCATION = "C:/Users/gonza/Downloads";
	final String [][] contentTypes = {{"todos","application/octet-stream"}};
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object requestObject = request.getParameter("filename");
		if (requestObject != null) {
			String fileName = (String) requestObject;
			String contentType = getContentType(fileName.split("\\.")[1]);
			File file = new File (FILE_LOCATION + "/" + fileName);
			response.setContentType(contentType);
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName); 
			response.setContentLength((int)file.length());
			ServletOutputStream servletoutputstream = response.getOutputStream();
			BufferedInputStream bufferedinputstream = new BufferedInputStream(new FileInputStream(file));
		    int bytesRead = bufferedinputstream.read();
			while(bytesRead != -1) {
				servletoutputstream.write(bytesRead);
				bytesRead = bufferedinputstream.read();
				
			}
			if(servletoutputstream != null) servletoutputstream.close();
			if(bufferedinputstream != null) bufferedinputstream.close();
			
			
		}
	}
	private String getContentType(String fileType) {
		String returnType  = null;
		for(int i=0; i< contentTypes.length; i++) {
			if (fileType.equals(contentTypes[i][0])) returnType= contentTypes[i][1];
		}
		return returnType;
	}
	
}