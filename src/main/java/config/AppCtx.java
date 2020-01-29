package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
public class AppCtx {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService();
	}
	
//	@Bean
//	public MemberPrinter memberPrinter() {
//		return new MemberPrinter();
//	}
	
	@Bean
	@Qualifier("printer")	// bean을 설정할 때 이름을 지정할 수 있다.
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
//		MemberPrinter를 상속받았으므로 MemberPrinter자리에 들어갈 수 있다. 따라서 Qualifier 를 사용하여야 한다.
	}
	
// constructor를 이용한 Dependency Injection
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setPrinter(memberPrinter2());
		return infoPrinter;
//		MemberInfoPrinter 객체의 setter에 자동의존주입이 있지만, bean을 설정할 때 setter로 명시적 의존주입을 했다.
//		결과는 명시적 의존주입을 한 경우보다 자동의존주입에 따라 객체가 주입된다.
//		따라서 자동의존주입을 사용하는 것이 낫다.
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
