package org.klaster.model.state.user;

import java.time.LocalDateTime;
import org.klaster.model.context.User;

/**
 * UnverifiedUserState
 *
 * @author Nikita Lepesevich
 */

public class UnverifiedUserState extends AbstractUserState {

  public UnverifiedUserState(User context) {
    super(context);
  }

  @Override
  public void authorizeUser(LocalDateTime authorizedAt) {
    getContext().getLoginInfo()
                .setLastAuthorizedAt(authorizedAt);
    final String message = String.format("User#%s was authorized at %s", getContext(), authorizedAt);
    logger.info(message);
  }
}
