package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {
	private CustomerDAO customerDao = new CustomerDAOJdbc();
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		CustomerBean bean = customerService.login("Babe", "BBB");
		System.out.println(bean);
		
		customerService.changPassword("Ellen", "E", "ABC");
		
	}
	public boolean changPassword(
			String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			byte[] temp = newPassword.getBytes();	//�ϥΪ̿�J
			return customerDao.update(
					temp, bean.getEmail(), bean.getBirth(), username);
		}
		return false;
	}
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if(bean!=null) {
			if(password!=null && password.length()!=0) {
				byte[] pass = bean.getPassword();	//��Ʈw��X
				byte[] temp = password.getBytes();	//�ϥΪ̿�J
				if(Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
}
