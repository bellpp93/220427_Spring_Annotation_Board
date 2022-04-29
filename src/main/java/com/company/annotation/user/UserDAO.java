package com.company.annotation.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.annotation.common.JDBCUtil;
import com.company.annotation.common.PasswordEncryptUtil;

public class UserDAO {
	private Connection			conn = null;
	private PreparedStatement	pstmt = null;
	private ResultSet			rs = null;
	
	private final String USER_GET = "select * from users where id=? and password=?";
	private final String USERS_INSERT = "insert into users values(?,?,?,?,?)";
	
	String pwEncrypt;  // pwEncrypt 전역변수 선언
	
	public UserDO getUser(UserDO userDO) {
		UserDO user = null;
		
		try {
			System.out.println("===> JDBC로 getUser() 처리");
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, userDO.getId());
			pstmt.setString(2, userDO.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
			
		} catch (Exception e) {
			System.out.println("===> getUser()" + e);
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}  // End getUser() 메소드 =======================================================
	
	// 회원가입 insert 처리 메소드
	public void insertUser(UserDO userDO) {
		System.out.println("===> JDBC로 insertUser() 처리");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USERS_INSERT);
			pstmt.setString(1, userDO.getId());
			pstmt.setString(2, userDO.getPassword());
			// 입력 받은 패스워드를 암호화 시켜 3번째 물음표 값으로 지정한다.
			String plainText = userDO.getPassword();
			pwEncrypt = PasswordEncryptUtil.encryptSHA256(plainText);
			pstmt.setString(3, pwEncrypt);
			pstmt.setString(4, userDO.getName());
			pstmt.setString(5, userDO.getRole());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
}
