package uk.co.hydrodev.userservice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.*;

import uk.co.hydrodev.userservice.*;

public class TestUserService {
   
	
	private final static String PASSWORD_MD5 = "xxxxxxx";
	private User user1;
	private UserDAO userDAO;
	private SecurityService securityService;
	private UserService userService;
	
	
	@Before
	public void setUp() throws Exception {
		userDAO = mock(UserDAO.class);
		securityService = mock(SecurityService.class);
		user1 = mock(User.class);
		//when(user1.getPassword()).thenReturn("password");
		when(securityService.md5(user1.getPassword())).thenReturn(PASSWORD_MD5);
		userService = new UserServerImpl(userDAO, securityService);
	}

	@Test
	public void test() {
		userService.assignPassword(user1);
		verify(user1).setPassword(PASSWORD_MD5);
		verify(userDAO).updateUser(user1);
	}

}
