/**
 *
 */

package com.mbk.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * <p>
 * Unit tests for the {@link Account} entity.
 * </p>
 *
 * @author hrishi
 *
 */
public class AccountTests {

  /**
   * <p>
   * Test when account is debit and credited.
   * </p>
   */
  @Test
  public void testDebitCredit() {
    Account a = new Account("Cash Account", AccountType.ASSET_ACCOUNT);
    float amt = 1000f;
    a.debit(amt);
    MatcherAssert.assertThat(a.getBalance(), Matchers.equalTo(amt));

    float creditedAmt = 500f;
    a.credit(creditedAmt);
    MatcherAssert.assertThat(a.getBalance(), Matchers.equalTo(amt - creditedAmt));
  }

}
