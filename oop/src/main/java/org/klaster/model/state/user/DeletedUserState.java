package org.klaster.model.state.user;

import org.klaster.model.context.User;

/**
 * DeletedUserState
 *
 * @author Nikita Lepesevich
 */

public class DeletedUserState extends AbstractUserState {

  public DeletedUserState(User context) {
    super(context);
  }

}
