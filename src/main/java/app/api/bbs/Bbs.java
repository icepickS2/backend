package app.api.bbs;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import app.api.account.Account;

@Entity
public class Bbs {
  @Id
  @GeneratedValue
  Long idx;

  // @JoinColumn
  // Account account;
}