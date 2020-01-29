package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("listPrinter")		// Bean의 이름을 부여함
public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter memberPrinter;
	
	public MemberListPrinter() {
		
	}
	public  MemberListPrinter(MemberDao memberDao, MemberPrinter memberPrinter) {
		this.memberDao = memberDao;
		this.memberPrinter = memberPrinter;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(	m -> memberPrinter.print(m));
	}
	
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	@Autowired
	@Qualifier("summaryPrinter")
	public void setMemberPrinter(MemberPrinter memberPrinter) {
		this.memberPrinter = memberPrinter;
	}
	
	@Autowired
	public void setMemberPrinter(MemberSummaryPrinter memberPrinter) {
		this.memberPrinter = memberPrinter;
	}
}
