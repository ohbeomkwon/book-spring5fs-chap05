package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.MemberDao;
import spring.MemberPrinter;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;
import spring2.ManualBean;
import spring2.NoProduct;

@Configuration
@ComponentScan(basePackages = {"spring", "spring2"},
//			excludeFilters = @Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao"))		// 정규식을 이용
//			excludeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao"))		// AspectJ 패턴을 이용
//			excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {NoProduct.class, ManualBean.class}))		// Annotation을 이용
//			excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class))		// 명시적으로 선택
			excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = MemberDao.class),
										@Filter(type = FilterType.REGEX, pattern = "spring2\\..*")})		// 여러 필터타입을 사용할 경우 배여을 이용한다.
public class AppCtxWithExclude {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	@Qualifier("printer")	// bean을 설정할 때 이름을 지정할 수 있다.
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
