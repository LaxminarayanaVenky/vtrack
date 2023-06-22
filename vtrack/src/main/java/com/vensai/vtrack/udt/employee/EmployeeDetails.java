package com.vensai.vtrack.udt.employee;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class EmployeeDetails {
    private String empId;
    private String empFirstName;
    private String empLastName;
    private String empPassword;
    private String gender;
    private Date doj;
    private String email;
    private String managerId;
    private String currentDesignation;
    private String contactNumber;
    private String role;
    private String departmentId;
    private String status;
    private String theme;
    private String empMiddleName;
    private String location;
    private String branch;
    private String extnsn;
    private String projectName;
    private String modulesAccess;
    private String empPhoto;
    private String imageName;
    private byte[] image;
    private String imageId;
    private String empTimesheetRequest;
    private String mgrTimesheetRequest;
    private String acCode;

    // Constructors
    public EmployeeDetails() {
    }

    public EmployeeDetails(String empId, String empFirstName, String empLastName, String empPassword, String gender, Date doj,
                      String email, String managerId, String currentDesignation, String contactNumber, String role,
                      String departmentId, String status, String theme, String empMiddleName, String location,
                      String branch, String extnsn, String projectName, String modulesAccess, String empPhoto,
                      String imageName, byte[] image, String imageId, String empTimesheetRequest,
                      String mgrTimesheetRequest, String acCode) {
        this.empId = empId;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empPassword = empPassword;
        this.gender = gender;
        this.doj = doj;
        this.email = email;
        this.managerId = managerId;
        this.currentDesignation = currentDesignation;
        this.contactNumber = contactNumber;
        this.role = role;
        this.departmentId = departmentId;
        this.status = status;
        this.theme = theme;
        this.empMiddleName = empMiddleName;
        this.location = location;
        this.branch = branch;
        this.extnsn = extnsn;
        this.projectName = projectName;
        this.modulesAccess = modulesAccess;
        this.empPhoto = empPhoto;
        this.imageName = imageName;
        this.image = image;
        this.imageId = imageId;
        this.empTimesheetRequest = empTimesheetRequest;
        this.mgrTimesheetRequest = mgrTimesheetRequest;
        this.acCode = acCode;
    }

    // Getters and Setters
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEmpMiddleName() {
        return empMiddleName;
    }

    public void setEmpMiddleName(String empMiddleName) {
        this.empMiddleName = empMiddleName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getExtnsn() {
        return extnsn;
    }

    public void setExtnsn(String extnsn) {
        this.extnsn = extnsn;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModulesAccess() {
        return modulesAccess;
    }

    public void setModulesAccess(String modulesAccess) {
        this.modulesAccess = modulesAccess;
    }

    public String getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getEmpTimesheetRequest() {
        return empTimesheetRequest;
    }

    public void setEmpTimesheetRequest(String empTimesheetRequest) {
        this.empTimesheetRequest = empTimesheetRequest;
    }

    public String getMgrTimesheetRequest() {
        return mgrTimesheetRequest;
    }

    public void setMgrTimesheetRequest(String mgrTimesheetRequest) {
        this.mgrTimesheetRequest = mgrTimesheetRequest;
    }

    public String getAcCode() {
        return acCode;
    }

    public void setAcCode(String acCode) {
        this.acCode = acCode;
    }

	@Override
	public String toString() {
		return "EmployeeDetails [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empPassword=" + empPassword + ", gender=" + gender + ", doj=" + doj + ", email=" + email
				+ ", managerId=" + managerId + ", currentDesignation=" + currentDesignation + ", contactNumber="
				+ contactNumber + ", role=" + role + ", departmentId=" + departmentId + ", status=" + status
				+ ", theme=" + theme + ", empMiddleName=" + empMiddleName + ", location=" + location + ", branch="
				+ branch + ", extnsn=" + extnsn + ", projectName=" + projectName + ", modulesAccess=" + modulesAccess
				+ ", empPhoto=" + empPhoto + ", imageName=" + imageName + ", image=" + Arrays.toString(image)
				+ ", imageId=" + imageId + ", empTimesheetRequest=" + empTimesheetRequest + ", mgrTimesheetRequest="
				+ mgrTimesheetRequest + ", acCode=" + acCode + "]";
	}
}
