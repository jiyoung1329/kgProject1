package register;


import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginDTO;


public class RegisterService {
	
	public void regProc(Parent regForm) {
		TextField idField = (TextField) regForm.lookup("#regId"); // regForm���� regId�̸��� ã��
		PasswordField pwField = (PasswordField)regForm.lookup("#regPw"); // regForm���� regPw�̸��� ã��
		PasswordField confirmField = (PasswordField)regForm.lookup("#regConfirmPw"); // regForm���� regConfirmPw�̸��� ã��
		TextField mobileField = (TextField) regForm.lookup("#regMobile"); // regForm���� regMobile�̸��� ã��
		 
		String id = idField.getText(); // �ʵ忡�� ���� ������
		String pw = pwField.getText();
		String confirm = confirmField.getText();
		String mobile = mobileField.getText();
		
		if(pw.equals(confirm)) { // ��й�ȣ ���� ��й�ȣ Ȯ�� ���� ���� ���
			RegDAO regDao = new RegDAO();
			//�ߺ�Ȯ�� ��ư?
			LoginDTO login = regDao.selectId(id); //id������ �ش� ȸ���� �����ϴ��� �˻�
			if(login == null) { // id(pk)���� �������� ������� RegDTO �ڷ����� ���� ����
				RegDTO regDto = new RegDTO();
				regDto.setId(id);
				regDto.setPw(pw);
				regDto.setMobile(mobile);
				regDao.insert(regDto); // �ش� ������ RegDAO�� insert�޼ҵ� ����
				
				CommonService.msg(id + "ȸ���� ���ԵǾ����ϴ�.");
				CommonService.windowClose(regForm);
			}else { // id���� ������ ��� ����
				CommonService.msg(id +"�� �ߺ��� ���̵��Դϴ�.");
				return;
			}
		}else { // ��й�ȣ���� ��й�ȣ Ȯ�� ���� �ٸ� ��� ����
			CommonService.msg("�Է��� ��й�ȣ�� Ȯ���� ��й�ȣ�� �ٸ��ϴ�.");
			pwField.clear();confirmField.clear();
			pwField.requestFocus();
		}
	}




}
