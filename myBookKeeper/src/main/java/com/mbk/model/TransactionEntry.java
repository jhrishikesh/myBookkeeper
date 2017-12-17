/**
 *
 */

package com.mbk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * A transaction entry.
 * </p>
 *
 * @author hrishi
 *
 */
@Entity
@Accessors(fluent = true)
public class TransactionEntry extends AbstractEntity {

  public static enum EntryType {
    DR, CR
  }

  private static final long serialVersionUID = 6364441958424177477L;

  private Transaction transaction;
  @Getter
  @Setter(value = AccessLevel.PACKAGE)
  private int index;
  @Getter
  @Column(insertable = true, updatable = false)
  private Long transactionId;

  @Getter
  @Setter(value = AccessLevel.PACKAGE)
  private int sequenceNumber;

  @Getter
  @Setter(value = AccessLevel.PACKAGE)
  private float amount;

  @Getter
  @Setter(value = AccessLevel.PACKAGE)
  private EntryType type;

  @Getter
  @Setter(value = AccessLevel.PACKAGE)
  private Account account;

  @Column(updatable = false)
  private Long accountId;

  TransactionEntry() {}

  TransactionEntry(final Transaction tx) {
    this.transaction = tx;
    this.transactionId = tx.getId();
  }
}
