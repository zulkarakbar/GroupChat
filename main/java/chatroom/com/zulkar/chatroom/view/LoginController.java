package chatroom.com.zulkar.chatroom.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MyServlet's doGet() method is invoked.");
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyServlet's doPost() method is invoked.");
	//	String contextPath=request.getContextPath();
		//doAction(request, response);
		//response.sendRedirect(contextPath + "/listrooms.jsp");
		
		
	}

	private ModelAndView doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("uname");
		String mail = req.getParameter("mail");
		String otp = req.getParameter("otp");
		//resp.setContentType("text/plain");
		//resp.getWriter().write("Hello " + name + mail+otp+"!");
		ModelAndView modelAndView = new ModelAndView("listrooms");
	    modelAndView.addObject("message", "Baeldung");
	    return modelAndView;
	    //use rediection and not forwarding
	}

}
