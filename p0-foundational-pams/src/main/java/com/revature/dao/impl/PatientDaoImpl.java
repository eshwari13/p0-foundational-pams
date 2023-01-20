package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.config.DatabaseConnection;
import com.revature.constants.Constant;
import com.revature.dao.PatientDao;
import com.revature.model.Patient;

public class PatientDaoImpl implements PatientDao{

public static final Logger logger =  Logger.getLogger(PatientDaoImpl.class);
	
	private static Connection con = DatabaseConnection.getConnection();
	Scanner scan = new Scanner(System.in);
	public static int count;
	@Override
	public int add() throws SQLException {
		Patient patient = new Patient();
		logger.info("Enter Patient loginId : ");
		patient.setLoginId(scan.nextLine());
		logger.info("Enter password : ");
		patient.setPassword(scan.nextLine());
		logger.info("Enter Patient Name : ");
		patient.setPatientName(scan.nextLine());
		logger.info("Enter Patient emailId : ");
		patient.setEmail(scan.nextLine());
		logger.info("Enter Patient PhoneNo. : ");
		patient.setPhoneNo(scan.nextLine());
		logger.info("Enter Patient BirthDate : ");
		patient.setBirthDate(scan.nextLine());
		
		String insertQuery = Constant.INSERT_QUERY;
		PreparedStatement ps = con.prepareStatement(insertQuery); 
		
		ps.setString(1, patient.getLoginId());
		ps.setString(2, patient.getPassword());
		ps.setString(3, patient.getPatientName());
		ps.setString(4, patient.getEmail());
		ps.setString(5, patient.getPhoneNo());
		ps.setString(6, patient.getBirthDate());
		ps.setInt(7, patient.getAge());
	
		logger.debug("I want to inspect PreparedStatement object: "+ps);
		int n = ps.executeUpdate();
		ps.close();
		logger.info("Added User ");
		return n;
	}

	@Override
	public int delete(String loginId) throws SQLException {
		// TODO Auto-generated method stub
		String deleQquery = Constant.DELETE_QUERY;
		PreparedStatement ps = con.prepareStatement(deleQquery);
		ps.setString(1, loginId);
		int n = ps.executeUpdate();
		return n;
	}

	@Override
	public List<Patient> getPatients() throws SQLException {
		// TODO Auto-generated method stub
		String selectAllQuery = Constant.SELECT_ALL_QUERY;
		PreparedStatement ps = con.prepareStatement(selectAllQuery);
		ResultSet rs = ps.executeQuery();
		List<Patient> patients = new ArrayList<Patient>();

		while (rs.next()) {
			count=0;
			Patient patient = new Patient();
			patient.setLoginId(rs.getString("LOGIN_ID"));
			patient.setPassword(rs.getString("PASSWORD"));
			patient.setPatientName(rs.getString("PATIENT_NAME"));
			patient.setEmail(rs.getString("email"));
			patient.setPhoneNo(rs.getString("phone_No"));
			patient.setBirthDate(rs.getString("birth_date"));
			patient.setAddress(rs.getString("address"));
			patient.setPhysicalDisability(rs.getString("physical_disability"));
			patient.setIdentityProof(rs.getString("identity_proof"));
			patient.setAge();
			patients.add(patient);
			count++;
		}
		return patients;
	}

	@Override
	public int update(Patient patient) throws SQLException {
		
		//`LOGIN_ID`,`PASSWORD`,`patient_name`,`email`,`phone_No`,`birth_date`,`address`,`physical_disability`,`identity_proof`,`age`
		logger.info("Enter password to update: ");
		patient.setPassword(scan.nextLine());
		logger.info("Enter Patient Name : ");
		patient.setPatientName(scan.nextLine());
		logger.info("Enter Patient emailId : ");
		patient.setEmail(scan.nextLine());
		logger.info("Enter Patient PhoneNo. : ");
		patient.setPhoneNo(scan.nextLine());
		logger.info("Enter Patient BirthDate : ");
		patient.setBirthDate(scan.nextLine());
		logger.info("Enter Patient address : ");
		patient.setAddress(scan.nextLine());
		logger.info("Enter Physical Disability : ");
		patient.setPhysicalDisability(scan.nextLine());
		logger.info("Enter identity Proof : ");
		patient.setIdentityProof(scan.nextLine());
		
		String updateQuery = Constant.UPDATE_QUERY;
		PreparedStatement ps = con.prepareStatement(updateQuery);
		
		ps.setString(1, patient.getPassword());
		ps.setString(2, patient.getPatientName());
		ps.setString(3, patient.getEmail());
		ps.setString(4, patient.getPhoneNo());
		ps.setString(5, patient.getBirthDate());
		ps.setString(6, patient.getAddress());
		ps.setString(7, patient.getPhysicalDisability());
		ps.setString(8, patient.getIdentityProof());
		ps.setInt(9, patient.getAge());
		ps.setString(10, patient.getLoginId());
		
		int n = ps.executeUpdate();
		return n;
	}

	
	
	@Override
	public Patient getPatientDetails(String loginId) throws SQLException {
		String selectSpecificQuery = Constant.SELECT_SPECIFIC_QUERY;
		PreparedStatement ps = con.prepareStatement(selectSpecificQuery);
		ps.setString(1, loginId);
		Patient patient = new Patient();
		ResultSet rs = ps.executeQuery();
		boolean found = false;
		while (rs.next()) {
			found = true;
			//`LOGIN_ID`,`PASSWORD`,`patient_name`,`email`,`phone_No`,`birth_date`,`address`,`physical_disability`,`identity_proof`,`age`
			patient.setLoginId(rs.getString("LOGIN_ID"));
			patient.setPassword(rs.getString("PASSWORD"));
			patient.setPatientName(rs.getString("PATIENT_NAME"));
			patient.setEmail(rs.getString("email"));
			patient.setPhoneNo(rs.getString("phone_No"));
			patient.setBirthDate(rs.getString("birth_date"));
			patient.setAddress(rs.getString("address"));
			patient.setPhysicalDisability(rs.getString("physical_disability"));
			patient.setIdentityProof(rs.getString("identity_proof"));
			patient.setAge();
		}
		if (found == true) {
			return patient;
		} else
			return null;
	}

	

	
}