import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/contact")
public class ContactController {
	
	private PersonService personService;

	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value="/")
	public ModelAndView listPersonsContact(ModelAndView modelAndView) throws IOException {
		List<Person> persons = personService.listPersons();
		modelAndView.addObject("orderType", "contacts");
		modelAndView.addObject("title", "Contact Information");
		modelAndView.addObject("persons", persons);
		modelAndView.setViewName("listPersons");
		return modelAndView;
	}

	@RequestMapping(value="/add")
	public ModelAndView addContact(ModelAndView modelAndView) {
		ContactInformation contactInformation = new ContactInformation();
		modelAndView.addObject("title", "Add Contact Information");
		modelAndView.addObject("contactInformation", contactInformation);
		modelAndView.setViewName("contactForm");
		return modelAndView;
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updateContact(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		ContactInformation contactInformation = person.getContactInformation();
		ModelAndView modelAndView = new ModelAndView("contactForm");
		modelAndView.addObject("title", "Update Contact Information");
		modelAndView.addObject("contactInformation", contactInformation);
		return modelAndView;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute("contactInformation") ContactInformation contactInformation, long id) {
		if (contactInformation.getId() == 0) {
			Person person = personService.getPersonById(id);
			person.setContactInformation(contactInformation);
			personService.updatePerson(person);
		}
		else {
			Person person = contactInformation.getPerson();
			person.setContactInformation(contactInformation);
			personService.updatePerson(person);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam(value="personId", required=true) long id) {
		Person person = personService.getPersonById(id);
		ContactInformation contactInformation = person.getContactInformation();
		//personService.dao.delete(id, "ContactInformation");
		person.setContactInformation(null);
		personService.updatePerson(person);
		return new ModelAndView("redirect:/");
	}
	
}