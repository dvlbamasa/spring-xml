import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

public class PersonController {
	
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public ModelAndView listPersons(ModelAndView modelAndView) throws IOException {
		List<Person> persons = personService.getPersons();
		modelAndView.addObject("persons", persons);
		modelAndView.setViewName("listPersons");
		return modelAndView;
	}

	public ModelAndView addPerson(ModelAndView modelAndView) {
		Person person = new Person();
		modelAndView.addObject("person", person);
		modelAndView.setViewName("addPerson");
		return modelAndView;
	}

	public ModelAndView savePerson(Person person) {
		if (person.getId() == 0) {
			personService.addPerson(person);
		}
		else {
			personService.updatePerson(person);
		}
		return new ModelAndView("redirect:/");
	}

	public ModelAndView deletePerson(long id) {
		personService.deletePerson(id);
		return new ModelAndView("redirect:/");
	}

	public ModelAndView updatePerson(long id) {
		Person person = personService.getPersonById(id);
		ModelAndView modelAndView = new ModelAndView("updatePerson");
		modelAndView.addObject("person", person);
		return modelAndView;
	}
}