package spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
//		5.1에 나오는 내용. 어떤 상황인지 아직은 헷갈린다.
	}
	
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf("회원정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n", 
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime() );
		}
		else {
			System.out.printf("회원정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n", 
					member.getId(), member.getEmail(), member.getName(), 
					dateTimeFormatter.format(member.getRegisterDateTime()) );
		}
	}
	
//	@Autowired(required = false)		// bean이 없으면 자동주입을 수행하지 않는다.
//	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}

//	두번째 방법
//	@Autowired
//	public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
//		if(formatterOpt.isPresent()) {
//			this.dateTimeFormatter = formatterOpt.get();
//		}
//		else {
//			this.dateTimeFormatter = null;
//		}
//	}
//	세번째 방법
	@Autowired
	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
