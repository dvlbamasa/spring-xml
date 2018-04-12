import java.util.List;

public interface PersonService {
	
	public void addPerson(Person person);
	public void updatePerson(Person person);
	public Person getPersonById(long id);
	public List<Person> listPersons();
	public void removePerson(long id);
}