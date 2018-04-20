import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	private RoleService roleService;
	private PersonService personService;

	public RoleController(RoleService roleService, PersonService personService) {
		this.roleService = roleService;
		this.personService = personService;
	}

	@RequestMapping(value="/")
	public ModelAndView listRoles(ModelAndView modelAndView,
								@RequestParam(value="prompt", required=false) String prompt) throws IOException {
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("title", "Role");
		modelAndView.addObject("prompt", prompt);
		modelAndView.addObject("roles", roles);
		modelAndView.setViewName("listRoles");
		return modelAndView;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addRole(ModelAndView modelAndView) {
		Role role = new Role();
		List<Person> persons = personService.listPersons();
		modelAndView.addObject("persons", persons);
		modelAndView.addObject("title", "Add Role");
		modelAndView.addObject("role", role);
		modelAndView.setViewName("roleForm");
		return modelAndView;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveRole(@ModelAttribute("role") Role role) {
		ModelAndView modelAndView = new ModelAndView("redirect:/role/");
		if (role.getId() == 0) {
			roleService.addRole(role);	
			modelAndView.addObject("prompt", "Successfully Added a Role!");
		}
		else {
			roleService.updateRole(role);
			modelAndView.addObject("prompt", "Successfully Updated a Role!");
		}
		return modelAndView;
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updateRole(@RequestParam(value="roleId", required=true) long id) {
		Role role = roleService.getRoleById(id);		
		ModelAndView modelAndView = new ModelAndView("roleForm");
		List<Person> persons = personService.listPersons();
		modelAndView.addObject("persons", persons);
		modelAndView.addObject("role", role);
		modelAndView.addObject("title", "Update Role");
		return modelAndView;
	}
}