package com.revature.service;

import java.sql.SQLException;

public interface Service {
	public void authentication();

	public void login() throws SQLException;
}
