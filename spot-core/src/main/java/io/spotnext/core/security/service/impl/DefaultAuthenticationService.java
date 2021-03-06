package io.spotnext.core.security.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spotnext.core.infrastructure.exception.ModelSaveException;
import io.spotnext.core.infrastructure.exception.ModelValidationException;
import io.spotnext.core.infrastructure.service.ModelService;
import io.spotnext.core.infrastructure.service.UserService;
import io.spotnext.core.infrastructure.service.impl.AbstractService;
import io.spotnext.core.persistence.exception.ModelNotUniqueException;
import io.spotnext.core.security.service.AuthenticationService;
import io.spotnext.core.security.strategy.PasswordEncryptionStrategy;
import io.spotnext.core.support.util.ValidationUtil;
import io.spotnext.itemtype.core.user.User;
import io.spotnext.itemtype.core.user.UserGroup;

@Service
public class DefaultAuthenticationService extends AbstractService implements AuthenticationService {

	@Autowired
	protected ModelService modelService;

	@Autowired
	protected PasswordEncryptionStrategy passwordEncryptionStrategy;

	@Autowired
	protected UserService<User, UserGroup> userService;

	@Override
	public User getAuthenticatedUser(final String name, final String password, final boolean isEncrypted) {
		ValidationUtil.validateNotEmpty("Password cannot be blank", password);

		String encryptedPassword = password;

		if (!isEncrypted) {
			encryptedPassword = encryptPassword(password);
		}

		final User user = userService.getUser(name);

		if (user != null && StringUtils.equals(user.getPassword(), encryptedPassword)) {
			return user;
		}

		return null;
	}

	@Override
	public void setPassword(final User user, final String plainPassword) throws ModelSaveException {
		user.setPassword(encryptPassword(plainPassword));

		try {
			modelService.save(user);
		} catch (ModelSaveException | ModelNotUniqueException | ModelValidationException e) {
			throw new ModelSaveException(e.getMessage(), e);
		}
	}

	@Override
	public String encryptPassword(final String plainPassword) {
		return passwordEncryptionStrategy.encryptPassword(plainPassword);
	}
}
