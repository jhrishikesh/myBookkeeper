/**
 *
 */

package com.mbk.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import com.mbk.model.TransactionEntry.EntryType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * <p>
 * Entity representing a GL transaction.
 * </p>
 *
 * @author hrishi
 *
 */
@Entity
public class Transaction extends AbstractEntity {

  private static final long serialVersionUID = -5656560477479857472L;

  @Getter
  @Setter
  private String description;
  @Getter
  @OneToMany
  private List<TransactionEntry> entries = new ArrayList<>();
  @Getter
  private boolean posted;
  @Getter
  private Date postedDate;
  @Transient
  private int eIdx;

  public Transaction credit(@NonNull Account acc, float amount) {
    return newEntry(acc, amount, EntryType.CR);
  }

  public Transaction debit(@NonNull Account acc, float amount) {
    return newEntry(acc, amount, EntryType.DR);
  }

  private Transaction newEntry(@NonNull Account acc, float amount, @NonNull final EntryType type) {
    TransactionEntry te = new TransactionEntry(this)//
        .index(eIdx++)//
        .account(acc)//
        .amount(amount)//
        .type(type);
    this.entries.add(te);
    return this;
  }

  /**
   * <p>
   * Posts this transaction.
   * </p>
   *
   * @return the posted transaction
   */
  public Transaction post() throws IllegalStateException {
    checkState(!posted && postedDate == null, "The transaction is already posted");
    checkArgument(entries.size() > 0, "There are no entries in this transaction");

    Float debitSum = sumEntries(EntryType.DR);
    Float creditSum = sumEntries(EntryType.CR);
    checkState(debitSum.floatValue() - creditSum.floatValue() == 0,
        "Sum of all debit entries must equal all credit entries");

    this.posted = true;
    this.postedDate = new Date();
    return this;
  }

  private Float sumEntries(@NonNull EntryType et) {
    return entries.stream().filter(e -> e.type() == et).map(e -> e.amount()).reduce(0.0f,
        Float::sum);
  }
}
