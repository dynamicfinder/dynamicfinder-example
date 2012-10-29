package org.dynamicfinder.example.controller.employee;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.dynamicfinder.Restriction;
import org.dynamicfinder.example.CriteriaUtil;
import org.dynamicfinder.example.EntityManagerFactoryListener;
import org.dynamicfinder.example.controller.Controller;
import org.dynamicfinder.example.dao.EmployeeDAO;
import org.dynamicfinder.example.dao.FindResultDTO;
import org.dynamicfinder.example.model.Employee;
import org.dynamicfinder.example.model.Employee.Gender;

import static org.dynamicfinder.jpa.JpaRestrictionExpression.*;

@WebServlet(value="/employee/gmail-like-list")
public class GmailLikeListController extends Controller {

	private static final long serialVersionUID = -6473592874706826364L;

	private EmployeeDAO employeeDAO;

	public GmailLikeListController() {
		this.employeeDAO = new EmployeeDAO(EntityManagerFactoryListener.getEntityManagerFactory());
	}

	@Override
	protected void process() {
		final int page = super.getInteger("page") == null ? 1 : super.getInteger("page");
		final String keyword = super.getString("keyword");
		final String firstName = CriteriaUtil.getCriteriaFromTokenizedKeyword("firstName", keyword);
		final String lastName = CriteriaUtil.getCriteriaFromTokenizedKeyword("lastName", keyword);
		final String genderString = CriteriaUtil.getCriteriaFromTokenizedKeyword("gender", keyword);
		final Gender gender = (genderString.equals("M") || genderString.equals("F")) 
				? Gender.valueOf(genderString) : null;

		List<Restriction> restrictions = Arrays.asList(
			$("firstName").like(firstName).and(),
			$("lastName").like(lastName).and(),
			$("gender").equal(gender).and()
		);

		final FindResultDTO<Employee> result = this.employeeDAO.find(page, restrictions);
		addAttribute("employees", result.getResultList());
		addAttribute("pages", result.getPages());
		addAttribute("currentPage", page);
		addAttribute("keyword", keyword);
		addAttribute("queryStringLog", result.getGeneratedQueryString());
		addAttribute("countQueryStringLog", result.getGeneratedCountQueryString());

		setView("/WEB-INF/views/employee/gmail-like-list.jsp");
	}
}
