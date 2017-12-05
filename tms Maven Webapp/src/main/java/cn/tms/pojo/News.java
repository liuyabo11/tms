package cn.tms.pojo;

/**
 * 新闻实体类
 */
import java.util.Date;

public class News {
	private String information_code;
	private String columntype;// 所属栏目 uuid
	private String title;
	private String titlecolor;
	private String outerlink;
	private Integer weight;
	private Date weightdate;
	private String image_url; // 相对路径
	private String primaryword; // 关键词
	private String summary;// 摘要
	private String content;
	private Date publish_time;
	private Date go_time;
	private Integer create_by;
	private Date create_date;
	private int update_by;
	private Date update_date;
	private Integer state;
	private String remark;// 备注

	// 新闻所属栏目 域属性 （自身属性）
	private Column column;
	// 新闻创建者 域属性 (自身属性)
	private User user;

	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(String information_code, String columntype, String title,
			String titlecolor, String outerlink, Integer weight,
			Date weightdate, String image_url, String primaryword,
			String summary, String content, Date publish_time, Date go_time,
			Integer create_by, Date create_date, int update_by,
			Date update_date, Integer state, String remark, Column column,
			User user) {
		super();
		this.information_code = information_code;
		this.columntype = columntype;
		this.title = title;
		this.titlecolor = titlecolor;
		this.outerlink = outerlink;
		this.weight = weight;
		this.weightdate = weightdate;
		this.image_url = image_url;
		this.primaryword = primaryword;
		this.summary = summary;
		this.content = content;
		this.publish_time = publish_time;
		this.go_time = go_time;
		this.create_by = create_by;
		this.create_date = create_date;
		this.update_by = update_by;
		this.update_date = update_date;
		this.state = state;
		this.remark = remark;
		this.column = column;
		this.user = user;
	}

	public String getInformation_code() {
		return information_code;
	}

	public void setInformation_code(String information_code) {
		this.information_code = information_code;
	}

	public String getColumntype() {
		return columntype;
	}

	public void setColumntype(String columntype) {
		this.columntype = columntype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlecolor() {
		return titlecolor;
	}

	public void setTitlecolor(String titlecolor) {
		this.titlecolor = titlecolor;
	}

	public String getOuterlink() {
		return outerlink;
	}

	public void setOuterlink(String outerlink) {
		this.outerlink = outerlink;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getWeightdate() {
		return weightdate;
	}

	public void setWeightdate(Date weightdate) {
		this.weightdate = weightdate;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getPrimaryword() {
		return primaryword;
	}

	public void setPrimaryword(String primaryword) {
		this.primaryword = primaryword;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public Date getGo_time() {
		return go_time;
	}

	public void setGo_time(Date go_time) {
		this.go_time = go_time;
	}

	public Integer getCreate_by() {
		return create_by;
	}

	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public int getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(int update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "News [information_code=" + information_code + ", columntype="
				+ columntype + ", title=" + title + ", titlecolor="
				+ titlecolor + ", outerlink=" + outerlink + ", weight="
				+ weight + ", weightdate=" + weightdate + ", image_url="
				+ image_url + ", primaryword=" + primaryword + ", summary="
				+ summary + ", content=" + content + ", publish_time="
				+ publish_time + ", go_time=" + go_time + ", create_by="
				+ create_by + ", create_date=" + create_date + ", update_by="
				+ update_by + ", update_date=" + update_date + ", state="
				+ state + ", remark=" + remark + ", column=" + column
				+ ", user=" + user + "]";
	}

}
