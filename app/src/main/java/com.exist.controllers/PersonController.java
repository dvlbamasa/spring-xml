import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {
	
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/")
	public ModelAndView listPersons(ModelAndView modelAndView) throws IOException {
		List<Person> persons = personService.listPersons();
		modelAndView.addObject("persons", persons);
		modelAndView.setViewName("listPersons");
		return modelAndView;
	}

	@RequestMapping(value="/addPerson", method= RequestMethod.GET)
	public ModelAndView addPerson(ModelAndView modelAndView) {
		ContactInformation contactInformation = new ContactInformation();
		Address address = new Address();
		Name name = new Name();
		Person person = new Person();
		person.setName(name);
		person.setContactInformation(contactInformation);
		person.setAddress(address);
		modelAndView.addObject("person", person);
		modelAndView.setViewName("personForm");
		return modelAndView;
	}

	@RequestMapping(value="/savePerson", method= RequestMethod.POST)
	public ModelAndView savePerson(Person person) {
		if (person.getId() == 0) {
			personService.addPerson(person);
		}
		else {
			personService.updatePerson(person);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value="/deletePerson")
	public ModelAndView deletePerson(@RequestParam(value="personId", required=true) long id) {
		personService.deletePerson(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value="/updatePerson")
	public ModelAndView updatePerson(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		ModelAndView modelAndView = new ModelAndView("personForm");
		modelAndView.addObject("person", person);
		return modelAndView;
	}
}