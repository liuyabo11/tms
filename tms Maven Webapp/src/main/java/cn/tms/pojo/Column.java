package cn.tms.pojo;

import java.util.ArrayList;
import java.util.List;

public class Column {
	private String syscode; // uuid
	private Integer columncode; // 栏目编号
	private String columnname; // 栏目名称
	private Integer sort;
	private String parentcode; // 栏目父编号
	private Integer status; // 栏目状态
	private String remark; // 栏目标记

	// 子栏目集合 tree控件进行绑定
	private List<Column> children = new ArrayList<Column>();

	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Column(String syscode, Integer columncode, String columnname,
			Integer sort, String parentcode, Integer status, String remark,
			List<Column> children) {
		super();
		this.syscode = syscode;
		this.columncode = columncode;
		this.columnname = columnname;
		this.sort = sort;
		this.parentcode = parentcode;
		this.status = status;
		this.remark = remark;
		this.children = children;
	}

	public String getSyscode() {
		return syscode;
	}

	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	public Integer getColumncode() {
		return columncode;
	}

	public void setColumncode(Integer columncode) {
		this.columncode = columncode;
	}

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Column> getChildren() {
		return children;
	}

	public void setChildren(List<Column> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Column [syscode=" + syscode + ", columncode=" + columncode
				+ ", columnname=" + columnname + ", sort=" + sort
				+ ", parentcode=" + parentcode + ", status=" + status
				+ ", remark=" + remark + ", children=" + children + "]";
	}

}
