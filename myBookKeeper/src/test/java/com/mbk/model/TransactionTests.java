/**
 *
 */

package com.mbk.model;

import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

/**
 * <p>
 * Tests for transaction.
 * </p>
 *
 * @author hrishi
 *
 */
public class TransactionTests {

  @Test(expected = IllegalStateException.class)
  public void alreadyPostedTransaction() {
    Account acc1 = new Account("Test1", AccountType.ASSET_ACCOUNT);
    Account acc2 = new Account("Test2", AccountType.LIABILITY_ACCOUNT);
    Account acc3 = new Account("Test3", AccountType.EQUITY_ACCOUNT);
    Transaction tx = new Transaction()//
        .debit(acc2, 10f).credit(acc1, 100f).debit(acc3, 99f);
    tx.post();
  }

  @Test
  public void completeTransaction() {
    Account acc1 = new Account("Test1", AccountType.ASSET_ACCOUNT);
    Account acc2 = new Account("Test2", AccountType.LIABILITY_ACCOUNT);
    Account acc3 = new Account("Test3", AccountType.EQUITY_ACCOUNT);
    Transaction tx = new Transaction()//
        .debit(acc1, 100f)//
        .credit(acc2, 110f)//
        .debit(acc3, 10f);
    tx.post();
    assertThat("Transaction is not posted", tx.isPosted() && tx.getPostedDate() != null);
  }

  @Test(expected = IllegalStateException.class)
  public void incompleteTransaction() {
    Account acc1 = new Account("Test1", AccountType.ASSET_ACCOUNT);
    Account acc2 = new Account("Test2", AccountType.LIABILITY_ACCOUNT);
    Account acc3 = new Account("Test3", AccountType.EQUITY_ACCOUNT);
    Transaction tx = new Transaction()//
        .debit(acc1, 100f)//
        .credit(acc2, 10f)//
        .debit(acc3, 90f);
    tx.post();
    assertThat("Transaction is not posted", tx.isPosted() && tx.getPostedDate() != null);
    tx.post();
  }
}
