import java.util.List;

public class RoleServiceImplementation implements RoleService {

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public void addRole(Role role) {
		this.dao.create(role);
	}

	@Override
	public void updateRole(Role role) {
		this.dao.update(role);
	}

	@Override
	public Role getRoleById(long id) {
		return (Role) this.dao.getById(id, "Role");
	}

	@Override
	public List<Role> listRoles() {
		return (List<Role>) this.dao.getList("Role");
	}
}