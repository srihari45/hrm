package gov.hrm.forms;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("workScheduleForm")
@Scope("prototype")
public class WorkScheduleForm extends BaseForm {

	private String workScheduleId;
	private String scheduleCandidate;
	private String scheduleWork;
	private String scheduleDate;

	public String getWorkScheduleId() {
		return workScheduleId;
	}

	public void setWorkScheduleId(String workScheduleId) {
		this.workScheduleId = workScheduleId;
	}

	public String getScheduleCandidate() {
		return scheduleCandidate;
	}

	public void setScheduleCandidate(String scheduleCandidate) {
		this.scheduleCandidate = scheduleCandidate;
	}

	public String getScheduleWork() {
		return scheduleWork;
	}

	public void setScheduleWork(String scheduleWork) {
		this.scheduleWork = scheduleWork;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	@Override
	public String toString() {
		return "WorkScheduleForm [workScheduleId=" + workScheduleId + ", scheduleCandidate=" + scheduleCandidate
				+ ", scheduleWork=" + scheduleWork + ", scheduleDate=" + scheduleDate + "]";
	}

}
