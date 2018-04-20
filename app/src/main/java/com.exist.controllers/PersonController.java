import java.io.IOException;
import java.util.List;
import java.util.HashSet;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/person")
public class PersonController {
	
	private PersonService personService;
	private RoleService roleService;

	public PersonController(PersonService personService, RoleService roleService) {
		this.personService = personService;
		this.roleService = roleService;
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView listPersons(ModelAndView modelAndView,
									@RequestParam(value="order_type", required=false) String orderType) throws IOException {
		
		List<Person> persons = null;
		String subTitle = "";
		if (orderType != null) {
			if (orderType.equals("GWA")) {
			persons = personService.listPersons();
            Collections.sort(persons, (person1, person2) -> {
               return Float.compare(person1.getGwa(), person2.getGwa());
            });
			}
			else if (orderType.equals("Date Hired")) {
				persons = personService.listPersonsOrderBy("dateHired");
			}
			else if (orderType.equals("Last Name")) {
				persons = personService.listPersonsOrderBy("name.lastName");
			}
			subTitle = "List of Persons Ordered by " + orderType;
		}
		else {
			persons = personService.listPersons();	
			subTitle = "List of Persons";
		}
		modelAndView.addObject("title", "Person");
		modelAndView.addObject("subTitle", subTitle);
		modelAndView.addObject("persons", persons);
		modelAndView.setViewName("listPersons");
		return modelAndView;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addPerson(ModelAndView modelAndView) {
		Person person = new Person();
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("title", "Add Person");
		modelAndView.addObject("person", person);
		modelAndView.addObject("roles", roles);
		modelAndView.setViewName("personForm");
		return modelAndView;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView savePerson(@ModelAttribute("person") Person person) {
		if (person.getId() == 0) {
			personService.addPerson(person);
		}
		else {
			personService.updatePerson(person);
		}
		return new ModelAndView("redirect:/person/list");
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deletePerson(@RequestParam(value="personId", required=true) long id) {
		personService.deletePerson(id);
		return new ModelAndView("redirect:/person/list");
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updatePerson(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		if (person.getContactInformation() == null) {
			person.setContactInformation(new ContactInformation());
		}
		ModelAndView modelAndView = new ModelAndView("personForm");
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("person", person);
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("title", "Update Person");
		return modelAndView;
	}
}