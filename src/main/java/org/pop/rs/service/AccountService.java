package org.pop.rs.service;

import org.pop.rs.common.Utils;
import org.pop.rs.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

	private List<Account> accounts = new ArrayList<Account>();

	public AccountService() {
	}

	public AccountService(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account add(Account account) {
		String lastId = null;
		if(accounts.size() > 0) lastId = accounts.get(accounts.size()-1).getAid();
		String newId = Utils.generateId(lastId);
		
		account.setAid(newId);
		accounts.add(account);
		
		return account;
	}
	
	public boolean update(String cid,Account newAccount) {
		for (Account account : accounts) {
			if(account.getAid().equals(cid)) {
				account.setActive(newAccount.isActive());
				return true;
			}
		}
		return false;
	}
	
	public Account findById(String cid) {
		for (Account account : accounts) {
			if(account.getAid().equals(cid)) return account;
		}
		return null;
	}

	public void updateStatus(String aid){
		Account account = findById(aid);
		account.setActive(true);
	}

	public void delete(String aid){
		Account account = findById(aid);
		accounts.remove(account);
	}

	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}
	

}
