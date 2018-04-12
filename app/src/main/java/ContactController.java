public class ContactController {
	
	private PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public ModelAndView addContact(ModelAndView modelAndView) {
		ContactInformation contactInformation = new ContactInformation();
		modelAndView.addObject("contactInformation", contactInformation);
		modelAndView.setView("addContact");
		return modelAndView;
	}

	public ModelAndView updateContact(long id) {
		Person person = personService.getPersonById(id);
		ContactInformation contactInformation = person.getContactInformation();
		ModelAndView modelAndView = new ModelAndView("updateContact");
		modelAndView.addObject("contactInformation", contactInformation);
		return modelAndView;
	}

	public ModelAndView saveContact(ContactInformation contactInformation, long id) {
		if (contactInformation.getId() == 0) {
			Person person = personService.getPersonById(id);
			person.setContactInformation(contactInformation);
			personService.update(person);
		}
		else {
			Person person = contactInformation.getPerson();
			PersonService.update(person);
		}
		return new ModelAndView("redirect:/");
	}

	public ModelAndView deleteContact(long id) {
		Person person = personService.getPersonById(id);
		ContactInformation contactInformation = person.getContactInformation();
		personService.dao.delete("ContactInformation", id);
		person.setContactInformation(null);
		personService.updatePerson(person);
		return new ModelAndView("redirect:/");
	}
}