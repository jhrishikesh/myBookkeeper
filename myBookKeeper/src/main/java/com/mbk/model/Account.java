/**
 *
 */

package com.mbk.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NonNull;

/**
 * <p>
 * Entity representing an account.
 * </p>
 *
 * @author hrishi
 */
@Entity
public class Account extends AbstractEntity {

  private static final long serialVersionUID = -2065863539169863789L;

  @NonNull
  private String name;

  @Getter
  @Enumerated(EnumType.ORDINAL)
  private AccountType type;

  @Getter
  private float balance = 0f;

  // TODO: Enforce system defined accounts are only created from service only
  @Getter
  private boolean systemDefined;

  protected Account() {}

  public Account(@NonNull String nm, @NonNull final AccountType accType) {
    this.name = nm;
    this.type = accType;
  }

  public synchronized void credit(float amt) {
    balance = balance - amt;
  }

  public synchronized void debit(float amt) {
    balance = balance + amt;
  }
}
