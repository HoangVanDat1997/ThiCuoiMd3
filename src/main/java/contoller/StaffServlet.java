package contoller;

import dao.StaffDao;
import model.Staff;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StaffServlet", value = "/StaffServlet")
public class StaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StaffDao staffDao;

    public void init() {
        staffDao = new StaffDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteStaff(request, response);
                    break;
                default:
                    listStaff(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertStaff(request, response);
                    break;
                case "edit":
                    updateStaff(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }


    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Staff> listStaff = staffDao.selectAllStaff();
        request.setAttribute("listStaff", listStaff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Staff/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Staff/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff existingUser = staffDao.selectStaff(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Staff/edit.jsp");
        request.setAttribute("staff", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String salary = request.getParameter("salary");
        String department = request.getParameter("department");
        Staff newStaff = new Staff(1, name, email, address, phonenumber, salary, department);
        staffDao.insertStaff(newStaff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Staff/create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String salary = request.getParameter("salary");
        String department = request.getParameter("department");

        Staff book = new Staff(id, name, email, address, phonenumber, salary, department);
        staffDao.updateStaff(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Staff/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffDao.deleteStaff(id);

        List<Staff> listStaff = staffDao.selectAllStaff();
        request.setAttribute("listStaff",listStaff );
        RequestDispatcher dispatcher = request.getRequestDispatcher("Staff/list.jsp");
        dispatcher.forward(request, response);
    }
}

