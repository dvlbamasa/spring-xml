public class RoleController {
	
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ModelAndView listRoles(ModelAndView modelAndView) throws IOException {
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("roles", roles);
		modelAndView.setViewName("listRoles");
		return modelAndView;
	}

	public ModelAndView addRole(ModelAndView modelAndView) {
		Role role = new Role();
		modelAndView.addObject("role", role);
		modelAndView.setViewName("addRole");
		return modelAndView
	}

	public ModelAndView saveRole(Role role) {
		if (role.getId() == 0) {
			roleService.addRole(role);	
		}
		else {
			roleService.updateRole(role);
		}
		return new ModelAndView("redirect:/");
	}

	public ModelAndView updateRole(long id) {
		Role role = roleService.getRoleById(id);
		ModelAndView modelAndView = new ModelAndView("updateRole");
		modelAndView.addObject("role", role);
		return modelAndView;
	}
}