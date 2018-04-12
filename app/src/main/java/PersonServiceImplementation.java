import java.util.List;

public class PersonServiceImplementation implements PersonService{

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}	

	public void addPerson(Person person) {
		this.dao.create(person);
	}

	public void updatePerson(Person person) {
		this.dao.update(person);
	}

	public List<Person> getPersons() {
		return this.dao.getList("Person");
	}

	public Person getPersonById(long id) {
		return (Person) this.dao.getById("Person", id);
	}

	public void deletePerson(long id) {
		this.dao.delete("Person", id);
	}
}