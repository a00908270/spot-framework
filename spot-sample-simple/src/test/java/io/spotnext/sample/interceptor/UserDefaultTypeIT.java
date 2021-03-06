package io.spotnext.sample.interceptor;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.spotnext.core.CoreInit;
import io.spotnext.core.testing.AbstractIntegrationTest;
import io.spotnext.core.testing.IntegrationTest;
import io.spotnext.itemtype.core.user.User;
import io.spotnext.sample.SampleInit;

@IntegrationTest(initClass = SampleInit.class)
@SpringBootTest(classes = { SampleInit.class, CoreInit.class })
public class UserDefaultTypeIT extends AbstractIntegrationTest {

	@Override
	protected void prepareTest() {
	}

	@Override
	protected void teardownTest() {
	}

	/**
	 * If the database is initialized all sequences start with 0. Therefore the
	 * newly created user should be "user-0".
	 */
	@Test
	public void testUserPrepareInterceptor() {
		final User user = modelService.create(User.class);
		user.setShortName("test user");

		modelService.save(user);
		modelService.refresh(user);

		Assert.assertEquals("user-0", user.getId());
	}
}
