package test.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "process_dbfile")
public class ProcessDbFile {
	@Id
	@Column(name = "ID_")
	private String id;
	@Column(name = "NAME_")
	private String name;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CONTENT_", columnDefinition = "MEDIUMTEXT", nullable = true)
	private String content;
	@Column(name = "CREATE_DATE_")
	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
