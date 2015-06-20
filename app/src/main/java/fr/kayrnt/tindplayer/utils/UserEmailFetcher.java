package fr.kayrnt.tindplayer.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class UserEmailFetcher
{
  private static Account getAccount(AccountManager paramAccountManager)
  {
    Account[] accounts = paramAccountManager.getAccountsByType("com.google");
    if (accounts.length > 0)
      return accounts[0];
    return null;
  }

  public static String getEmail(Context context)
  {
    Account account = getAccount(AccountManager.get(context));
    if (account == null)
      return null;
    return account.name;
  }
}