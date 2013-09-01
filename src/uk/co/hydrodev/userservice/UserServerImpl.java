package uk.co.hydrodev.userservice;

public class UserServerImpl implements UserService {

	private UserDAO userDAO;
	private SecurityService securityService;
	
	
	public UserServerImpl(UserDAO userDAO, SecurityService securityService) {
		this.userDAO = userDAO;
		this.securityService = securityService;	
	}

	@Override
	public void assignPassword(User user) {
		String passwordMd5 = securityService.md5(user.getPassword());
		user.setPassword(passwordMd5);
		userDAO.updateUser(user);
		
		
	}

}
