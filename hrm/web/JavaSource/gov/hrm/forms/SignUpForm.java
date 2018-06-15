package gov.hrm.forms;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("signUpForm")
@Scope("prototype")
public class SignUpForm extends BaseForm {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String refEmail;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRefEmail() {
		return refEmail;
	}

	public void setRefEmail(String refEmail) {
		this.refEmail = refEmail;
	}

}
