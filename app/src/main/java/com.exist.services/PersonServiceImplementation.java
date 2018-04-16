import java.util.List;

public class PersonServiceImplementation implements PersonService{

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}	

	@Override
	public void addPerson(Person person) {
		this.dao.create(person);
	}

	@Override
	public void updatePerson(Person person) {
		this.dao.update(person);
	}

	@Override
	public List<Person> listPersons() {
		return this.dao.getList("Person");
	}

	@Override
	public Person getPersonById(long id) {
		return (Person) this.dao.getById(id, "Person");
	}

	@Override
	public void deletePerson(long id) {
		this.dao.delete(id, "Person");
	}

	@Override
	public List<Person> listPersonsOrderBy(String orderType) {
		return this.dao.getOrderedList("Person", orderType);
	}
}