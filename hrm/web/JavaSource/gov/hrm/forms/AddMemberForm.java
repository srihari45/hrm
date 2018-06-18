package gov.hrm.forms;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("addMemberForm")
@Scope("prototype")
public class AddMemberForm extends BaseForm {

	private String memberId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String refEmail;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

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

	public String getRefEmail() {
		return refEmail;
	}

	public void setRefEmail(String refEmail) {
		this.refEmail = refEmail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "AddMemberForm [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phone=" + phone + ", refEmail=" + refEmail + "]";
	}

}
