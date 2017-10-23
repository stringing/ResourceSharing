package indiviudal.domain;

import java.util.List;

public class PageBean<T> {
	private int pc;
	private int tr;
	private int ps;
	/*private int tp;*/
	private List<T> beanList;
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public int getTp() {
		int tp = tr/ps;
		return tr%ps==0?tp:tp+1;
	}
	/*public void setTp(int tp) {
		this.tp = tp;
	}*/
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
