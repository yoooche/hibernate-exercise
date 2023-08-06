package web.member.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Entity;

import core.entity.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Core {
	private static final long serialVersionUID = 1062017833925367218L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String nickname;
	@Column(insertable = false)
	private Boolean pass;
	@Column(name = "role_id")
	private Integer roleId;
	@Column(insertable = false)
	private String creator;
	@Column(name = "created_date", insertable = false)
	private Timestamp createdDate;
	private String updater;
	@Column(name = "last_updated_date", insertable = false)
	private Timestamp lastUpdatedDate;
	
	public Member(String username, String nickname) {
		this.username = username;
		this.nickname = nickname;
	}
}
