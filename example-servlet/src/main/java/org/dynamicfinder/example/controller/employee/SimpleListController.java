package org.dynamicfinder.example.controller.employee;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.dynamicfinder.Restriction;
import org.dynamicfinder.example.EntityManagerFactoryListener;
import org.dynamicfinder.example.controller.Controller;
import org.dynamicfinder.example.dao.EmployeeDAO;
import org.dynamicfinder.example.dao.FindResultDTO;
import org.dynamicfinder.example.model.Employee;
import org.dynamicfinder.example.model.Employee.Gender;

import static org.dynamicfinder.jpa.JpaRestrictionExpression.*;

@WebServlet(value="/employee/simple-list")
public class SimpleListController extends Controller {

	private static final long serialVersionUID = -6473592874706826364L;

	private EmployeeDAO employeeDAO;

	public SimpleListController() {
		this.employeeDAO = new EmployeeDAO(EntityManagerFactoryListener.getEntityManagerFactory());
	}

	@Override
	protected void process() {
		final int page = super.getInteger("page") == null ? 1 : super.getInteger("page");
		final String firstName = super.getString("firstName");
		final String lastName = super.getString("lastName");
		final Gender gender = (null != super.getString("gender") && !super.getString("gender").equals("")) ? 
				Gender.valueOf(super.getString("gender")) : null;

		List<Restriction> restrictions = Arrays.asList(
			$("firstName").like(firstName).or(),
			$("lastName").like(lastName).or(),
			$("gender").equal(gender).or()
		);

		final FindResultDTO<Employee> result = this.employeeDAO.find(page, restrictions);
		addAttribute("employees", result.getResultList());
		addAttribute("pages", result.getPages());
		addAttribute("currentPage", page);
		addAttribute("firstName", firstName);
		addAttribute("lastName", lastName);
		addAttribute("gender", gender);
		addAttribute("queryStringLog", result.getGeneratedQueryString());
		addAttribute("countQueryStringLog", result.getGeneratedCountQueryString());

		setView("/WEB-INF/views/employee/simple-list.jsp");
	}
}
